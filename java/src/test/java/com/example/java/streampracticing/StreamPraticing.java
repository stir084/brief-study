package com.example.java.streampracticing;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamPraticing {
    private List<StreamDto> streamDtoList;
    private Map<Long, String> streamDtoMap;
    @BeforeEach
    void init(){
        StreamDto streamDto1 = new StreamDto(1L, "철수");
        StreamDto streamDto2 = new StreamDto(2L, "짱구");
        StreamDto streamDto3 = new StreamDto(3L, "철수");
        streamDtoList = new ArrayList<>();
        streamDtoList.add(streamDto1);
        streamDtoList.add(streamDto2);
        streamDtoList.add(streamDto3);

        streamDtoMap = new HashMap<>();
        streamDtoMap.put(1L, "철수");
        streamDtoMap.put(2L, "짱구");
        streamDtoMap.put(3L, "철수");

    }

    //List Stream filter
    @Test
    void test(){
        long 철수몇명 = streamDtoList.stream()
                .filter(a -> a.getName().equals("철수"))
                .count();
        Assertions.assertThat(철수몇명).isEqualTo(2) ;

        Long 첫번째철수아이디 = streamDtoList.stream()
                .filter(a -> a.getName().equals("철수"))
                .findFirst().get()
                .getId();
        Assertions.assertThat(첫번째철수아이디).isEqualTo(1L);
    }

    //Map Stream filter
    @Test
    void test2(){
        long 철수몇명 = streamDtoMap.entrySet().stream()
                .filter(streamDto -> streamDto.getValue().equals("철수"))
                .count();
        Assertions.assertThat(철수몇명).isEqualTo(2) ;

        Long 첫번째철수아이디 = streamDtoMap.entrySet().stream()
                .filter(streamDto -> streamDto.getValue().equals("철수"))
                .findFirst().get()
                .getKey();
        Assertions.assertThat(첫번째철수아이디).isEqualTo(1L);
    }

    // Worst Stream Using
    @Test
    void test3(){
        Map<String, Integer> names = new HashMap<>();
        names.put("stir", 1);
        names.entrySet().stream()
                .map(s -> names.put("stir", 2));

        //stir에 2 삽입을 했지만 최종 연산을 넣어주지 않아 실행하지 않음.
    }

    @Test
    void 위코드리팩토링하기1단계(){
        // 간단한건 forEach쓰기
        Map<String, Integer> names = new HashMap<>();
        names.put("stir", 1);
        names.entrySet()
                .forEach(s -> names.put("stir", 2));

    }
    @Test
    void 위코드리팩토링하기2단계(){
        // 외부변수 수정은 람다 사용하지 않기
        // 변수를 자체를 수정하면 불변성을 해치고
        // 객체 안에 요소를 수정하면 순수성을 해친다.
        Map<String, Integer> names = new HashMap<>();
        for (var entry : names.entrySet()) {
            if (entry.getKey().equals("stir")) {
                entry.setValue(2);
            }
        }
    }

    // Best Case - forEach_올바르게_사용하기
    // 단순한 경우 stream()보단 forEach 사용하기
    // 외부변수가 아닌 내부 변수만 수정하기
    @Test //이거 Bad Case임.
    void forEach_올바르게_사용하기(){
        streamDtoList.forEach(streamDto -> {
            streamDto.setName("유리");
        });
        streamDtoList.forEach(streamDto -> {
            assertEquals("유리", streamDto.getName());
        });
    }

    // Best Case - map 올바르게 사용하기
    // map은 말 그대로 mapper 기능일 때 사용하기
    @Test
    void map_올바르게_사용하기(){
        List<StreamDto> list1 = streamDtoMap.entrySet().stream()
                .map(streamDto -> new StreamDto(streamDto.getKey(), streamDto.getValue()))
                .collect(Collectors.toList());
        assertEquals(list1.size(), 3);

        //혹은 정적 팩토리 메소드 + 메소드 레퍼런스(::) 사용하기
        List<StreamDto> list2 = streamDtoMap.entrySet().stream()
                .map(StreamDto::createStreamDto2)
                .toList(); // 자바 16부터 나온 새로운 기능이다. 좀 더 단순해짐.
        assertEquals(list2.size(), 3);
    }

    // stream 올바르게 사용하기
    // 복잡할 땐 forEach보단 stream 사용하기
    @Test
    void stream_올바르게_사용하기(){
        StreamDto streamDto1 = new StreamDto(1L, "철수");
        StreamDto streamDto2 = new StreamDto(2L, "짱구");
        StreamDto streamDto3 = new StreamDto(3L, "철수");
        List<StreamDto> streamDtos = new LinkedList<>();
        streamDtos.add(streamDto1);
        streamDtos.add(streamDto2);
        streamDtos.add(streamDto3);
        // foreach
        List<StreamDto> filteredAndReversed1 = new LinkedList<>();
        for (StreamDto streamDto : streamDtos) {
            if ("철수".equals(streamDto.getName())) {
                filteredAndReversed1.add(streamDto);
            }
        }
        filteredAndReversed1.sort(Comparator.comparingLong(StreamDto::getId).reversed());


        // stream
        List<StreamDto> filteredAndReversed2 = streamDtos.stream()
                .filter(streamDto -> "철수".equals(streamDto.getName()))
                .sorted(Comparator.comparingLong(StreamDto::getId).reversed())
                .toList();

        assertEquals(filteredAndReversed1.get(0).getId(), 3L);
        assertEquals(filteredAndReversed2.get(0).getId(), 3L);


    }
    //noneMatch와 anyMatch 사용하기
    @Test
    void testnoneMatch(){
        //하나도 일치하지 않으면 true
        boolean noneLongerThanTen = streamDtoList.stream()
                .noneMatch(streamDto -> streamDto.getName().equals("철수"));
        //하나라도 일치하면 true
        boolean anyLongerThanTen = streamDtoList.stream()
                .anyMatch(streamDto -> streamDto.getName().equals("철수"));

        assertEquals(noneLongerThanTen, false);
        assertEquals(anyLongerThanTen, true);
    }


    //flatMap 사용하기
    //ipAddressPairs안에서 또 List를 불러오는 경우 사용. 즉 리스트 안의 리스트
    @Test
    void flatMap(){
        List<IpAddressPair> ipAddressPairs = new ArrayList<>();
        ipAddressPairs.add(new IpAddressPair("192.168.1.1", "192.168.1.2"));
        ipAddressPairs.add(new IpAddressPair("10.0.0.1", "10.0.0.2"));

        List<String> flattenedAddresses = ipAddressPairs.stream()
                .flatMap(ipAddressPair -> ipAddressPair.flatten().stream())
                .collect(Collectors.toList());
        //flatMap은 내부 요소가 List인 경우 사용한다
        //flatMap의 요소는 중간연산까지만 들어간다. stream() 혹은 map() 등

        System.out.println(flattenedAddresses);
    }
    public static class IpAddressPair {
        private String address1;
        private String address2;

        public IpAddressPair(String address1, String address2) {
            this.address1 = address1;
            this.address2 = address2;
        }

        public List<String> flatten() {
            List<String> addresses = new ArrayList<>();
            addresses.add(address1);
            addresses.add(address2);
            return addresses;
        }
    }
    //flatMap 사용하기
    // Stream of로 2개의 리스트를 합칠 때 사용
    @Test
    void flatMaptest(){
        StreamDto streamDto1 = new StreamDto(1L, "철수");
        StreamDto streamDto2 = new StreamDto(2L, "짱구");
        StreamDto streamDto3 = new StreamDto(3L, "철수");
        List<StreamDto> streamDtos = new LinkedList<>();
        streamDtos.add(streamDto1);
        streamDtos.add(streamDto2);
        streamDtos.add(streamDto3);

        List<StreamDto> streamDtos2 = new LinkedList<>();
        streamDtos2.add(streamDto1);
        streamDtos2.add(streamDto2);
        streamDtos2.add(streamDto3);
        List<StreamDto> list = Stream.of(streamDtos, streamDtos2).flatMap(List::stream).toList();
        System.out.println(list);
    }

    @Test
    void peekTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        boolean anyMatchWithPeek = numbers.stream()
                .peek(number -> System.out.println("Peek: " + number))
                .anyMatch(number -> number == 2);

        boolean anyMatchWithMap = numbers.stream()
                .map(number -> {
                    System.out.println("Map: " + number);
                    return number;
                })
                .anyMatch(number -> number > 2);
    }

    @Test
    void findAny(){
        List<String> names = Arrays.asList("Booby", "Bob", "Carol", "David");
        Optional<String> anyName = names.stream()
                .filter(name -> name.startsWith("B")) // 'B'로 시작하는 이름 필터링
                .findAny(); // 아무 이름이나 반환
        System.out.println(anyName.get()); //Booby나 Bob가 리턴됨
    }
}
