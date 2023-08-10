package com.example.java;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class JavaApplicationTests {

	@Test
	void contextLoads() {
	}

	// putIfAbsent는 키를 기준으로 판단한다. 키가 없을 때만 키와 값을 삽입.
	// computeIfAbsent는 값을 기준으로 판단한다. 조회한 키에 대한 값이 없을 경우 키와 값을 삽입한다.

	// 사실 putIbAbsent는 맵의 값이 String 같이 단일 타입일 때 쓰는 거라고 보면 된다.
	// 사실 computeIfAbsent는 List나 객체 타입의 계산이 필요할 때 주로 쓰인다고 보면된다.

	// containsKey가 쓰인 곳이 있다면 위에 둘중 하나 문법으로 대체할 수 있다.
	@Test
	void putIfAbsent() {
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 1);
		map.putIfAbsent("A", 2); // 이미 "A" 키가 존재하므로 값이 변경되지 않음
		map.putIfAbsent("B", 3); // "B" 키가 존재하지 않아서 값이 추가됨

		System.out.println(map);
	}
	@Test
	void badAlternativePutIfAbsent() {
		// putIfAbsent() 대신 if 문을 사용한 결과
		// 결론 - containsKey를 쓸 바에는 putIfAbsent 이걸 쓰자.
		Map<String, Integer> map = new HashMap<>();
		map.put("A", 1);

		if (!map.containsKey("A")) {
			map.put("A", 2);
		}

		if (!map.containsKey("B")) {
			map.put("B", 3);
		}

		System.out.println(map); // 출력: {A=1, B=3}
	}
	@Test
	void computeIfAbsent(){
		Map<String, List<String>> map1 = new HashMap<>();
		map1.computeIfAbsent("A", k -> new ArrayList<>()).add("Value 1"); // "A" 키가 존재하지 않아서 새 리스트를 생성하고 값 추가
		map1.computeIfAbsent("A", k -> new ArrayList<>()).add("Value 2"); // "A" 키가 이미 존재하므로 기존 리스트에 값 추가

		System.out.println(map1); // 출력: {A=[Value 1, Value 2]}

		Map<String, String> map2 = new HashMap<>();
		map2.computeIfAbsent("A", k -> "New Value"); // "A" 키에 대한 값이 없을 때 "New Value"를 추가
		map2.computeIfAbsent("A", k -> "Another New Value"); // "A" 키에 대한 값이 이미 존재하므로 변경되지 않음

		System.out.println(map2); // 출력: {A=New Value}
	}
	@Test
	void badAlternativeComputeIfAbsent() {
		// computeIfAbsent() 대신 if 문을 사용한 결과
		// 결론 - containsKey를 쓸 바에는 computeIfAbsent 이걸 쓰자.
		// map1
		Map<String, List<String>> map1 = new HashMap<>();
		if (!map1.containsKey("A")) {
			map1.put("A", new ArrayList<>());
		}
		map1.get("A").add("Value 1");

		if (!map1.containsKey("A")) {
			map1.put("A", new ArrayList<>());
		}
		map1.get("A").add("Value 2");

		System.out.println(map1); // 출력: {A=[Value 1, Value 2]}

		// map2
		Map<String, String> map2 = new HashMap<>();
		if (!map2.containsKey("A")) {
			map2.put("A", "New Value");
		}

		if (!map2.containsKey("A")) {
			map2.put("A", "Another New Value");
		}

		System.out.println(map2); // 출력: {A=New Value}
	}
}
