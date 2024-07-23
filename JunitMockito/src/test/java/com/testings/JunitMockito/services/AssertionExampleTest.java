package com.testings.JunitMockito.services;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssertionExampleTest {

//    Assertion - validating actual result with expected result

    @Test
    public void test1(){
        System.out.println("Hello Testing some methode...");
//        should be true
//        boolean flag = 2<3;
//        Assertions.assertTrue(flag,"assert true method");
//        Assertions.assertFalse(flag,"assert false method");

//        should array match
//        Integer [] arr1 ={1,2,3};
//        Integer [] arr2 ={1,2,3};
//        Assertions.assertArrayEquals(arr1,arr2);

        String a = "rahul";
        String b = "rahul";
        Assertions.assertSame(a,b);

        List<Integer> list1= new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2= new ArrayList<>();
        list2.add(1);
        list2.add(2);



        Assertions.assertIterableEquals(list1,list2);

    }
}
