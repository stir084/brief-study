package com.example.java.mvcgrammers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MvcServiceTest2 {

    @InjectMocks
    private MVCService mvcService;

    @Mock
    private MVCRespository mvcRespository;

    // Repository를 Mock으로 만들어서 테스트
    @Test
    void testTest() {
        when(mvcRespository.save()).thenReturn("zzzz");

        MVCDto mvcDto = mvcService.test2();
        assertEquals("zzzz", mvcDto.getName()); // jupitor - junit 기본 제공, 초단순검사
        assertThat(mvcDto.getName(), is("zzzz")); // hamcrest쓰지마
        org.assertj.core.api.Assertions.assertThat(mvcDto.getName()).isEqualTo("zzzz"); //assertJ가 더 좋음
        verify(mvcRespository).save(); // mock만 되네?
        //verify - Mockito에서 쓰임, 얼마나 많이 호출 됐는지 메소드 호출 순서는 어떤지에 대한 것을 검사함.
    }
    @Test
    void testTest2() {
        when(mvcRespository.save()).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> mvcService.test2()); // 주피터
        assertThatThrownBy(() -> mvcService.test2()) //assertJ
                .isInstanceOf(RuntimeException.class);
    }

    // 아래 코드는 hamcrest 최악의 코드
    @Test
    void testStringMatchers() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);

        // Check if the collection contains specific elements
        assertThat(numbers, hasItem(6));
        assertThat(numbers, contains(2, 4, 6, 8, 10));
        assertThat(numbers, hasSize(5));

        // Check if all elements meet a certain condition
        assertThat(numbers, everyItem(greaterThan(1)));
        assertThat(numbers, everyItem(lessThanOrEqualTo(10)));

        String text = "Hello, World!";

        // Check if a string starts or ends with a specific substring
        assertThat(text, startsWith("Hello"));
        assertThat(text, endsWith("World!"));

        // Check if a string contains a specific substring
        assertThat(text, containsString("Hello"));
        assertThat(text, containsString("World"));

        // Check if a string matches a regular expression
        assertThat(text, matchesPattern("^[A-Za-z,\\s!]+$"));
    }

    // assertJ가 hamcrest 보다 나음
    @Test
    void testStringMatchers2() {
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);

        org.assertj.core.api.Assertions.assertThat(numbers).containsExactly(2, 4, 6, 8, 10);
        org.assertj.core.api.Assertions. assertThat(numbers).hasSize(5);

        // Check if all elements meet a certain condition
        org.assertj.core.api.Assertions.assertThat(numbers).allMatch(n -> n > 1);
        org.assertj.core.api.Assertions.assertThat(numbers).allMatch(n -> n <= 10);

        String text = "Hello, World!";

        // Check if a string starts or ends with a specific substring
        org.assertj.core.api.Assertions.assertThat(text).startsWith("Hello");
        org.assertj.core.api.Assertions.assertThat(text).endsWith("World!");

        // Check if a string contains a specific substring
        org.assertj.core.api.Assertions.assertThat(text).contains("Hello");
        org.assertj.core.api.Assertions.assertThat(text).contains("World");

        // Check if a string matches a regular expression
        org.assertj.core.api.Assertions.assertThat(text).matches("^[A-Za-z,\\s!]+$");


    }

}
