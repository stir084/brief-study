package com.example.java.streampracticing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionTest {
    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    static class StudentDto {
        private String name;
        private int age;

        public StudentDto(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    //Function 인터페이스는 하나의 입력을 받아서 다른 타입으로 변환하는 메서드를 정의합니다.
    // 보통 map에 추가 된다.
    @Test
    void function_test(){
        List<Student> students = new ArrayList<>();
        Student temp = new Student("stir", 32);
        students.add(temp);

        Function<Student, StudentDto> studentToDto = student ->
                new StudentDto(student.getName(), student.getAge());

        List<StudentDto> studentDtos = students.stream()
                .map(studentToDto)
                .collect(Collectors.toList());

        studentDtos.forEach(dto ->
                System.out.println("Name: " + dto.getName() + ", Age: " + dto.getAge()));
    }

    //Consumer 인터페이스는 하나의 입력을 받아서 그 입력을 소비하는 메서드를 정의합니다.
    // 보통 forEach에 추가 된다.
    @Test
    void consumer_test(){
        List<Student> students = new ArrayList<>();
        Student temp = new Student("stir", 32);
        students.add(temp);

        Consumer<Student> printStudent = student ->
                System.out.println("Name: " + student.getName() + ", Age: " + student.getAge());

        students.forEach(printStudent);
    }

    //Predicate 인터페이스는 하나의 입력을 받아서 조건을 검사하는 메서드를 정의합니다.
    // 보통 filter에 추가 된다.
    @Test
    void predicate_test(){
        // 문자열의 길이가 5보다 큰지 검사하는 Predicate
        List<Student> students = new ArrayList<>();
        Student temp = new Student("stir", 32);
        students.add(temp);

        Predicate<Student> isAdult = student -> student.getAge() >= 18;

        List<Student> adults = students.stream()
                .filter(isAdult)
                .collect(Collectors.toList());

        adults.forEach(student ->
                System.out.println("Adult Name: " + student.getName() + ", Age: " + student.getAge()));
    }
}
