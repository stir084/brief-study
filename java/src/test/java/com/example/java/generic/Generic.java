package com.example.java.generic;

import com.example.java.streampracticing.StreamDto;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Generic {
    /**
     * 일급 컬렉션 만들기
     */
    @Test
    void tsetst(){
        Test1<Test3> test1 = new Test1<>();
        //Test1<Test4> test4 = new Test1<>(); 이건 불가능
    }

    public static class Test1<T extends Test2>{
        private Collection<T> deviceRemoveRequestCollection;
    }

    public interface Test2{

    }
    public static class Test3 implements Test2{

    }
    public static class Test4 {

    }

    /**
     제네릭 빡세게 이용하기
     1. 제네릭은 클래스 단위에서 사용하며 하위에 사용하는 요소들을 일반화시킨다.
     */
    public static class AnotherClass<T extends Test2> {
        private T field;

        public void setField(T value) {
            field = value;
        }

        public T getField() {
            return field;
        }
    }
    @Test
    void testtt(){
        AnotherClass<Test3> anotherClass = new AnotherClass<>();
        anotherClass.setField(new Test3());
        System.out.println(anotherClass.field.getClass());
    }

    /**
     * 와일드카드는 클래스 단위가 아닌 메서드 매개변수 또는 반환 타입에서 사용한다.
     * @param list
     */
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    @Test
    void setst(){
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");

        System.out.println("Printing integer list:");
        printList(intList);

        System.out.println("Printing string list:");
        printList(stringList);
    }

    @Test
    void ttttt() throws Exception {


        // Checked Exception
        // 예외 처리됨
       /* try {
            checkedException();
        } catch (ArithmeticException e) {
            System.out.println("zzzz");
            //e.printStackTrace();
        }*/

        // Unchecked Exception
        // 예외 처리되지 않음
        try {
            uncheckedException();
        } catch (NullPointerException e) {
            System.out.println("Zzz");
            //e.printStackTrace();
        }

    }
    public void checkedException() throws ArithmeticException {
        // Checked Exception
        // 예외 처리됨
        int x = 10;
        int y = 0;
       // System.out.println(x / y);
        int x1 = x/y ;
    }

    public void uncheckedException() {
        // Unchecked Exception
        // 예외 처리되지 않음
        String str = null;
        System.out.println(str.length());
    }

    @Test
    void tetwtwet(){
        try {
            processInput("invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public static void processInput(String input) throws IllegalArgumentException {
        if (input.equals("valid")) {
            // 유효한 입력 처리
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    @Test
    void NullPointerException(){
        try {
            processInput2(null);
        } catch (NullPointerException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public static void processInput2(String input) {
        if (input.length() > 0) { // 이 부분에서 NullPointerException 발생 가능
            // 입력 처리
        }
    }




}
