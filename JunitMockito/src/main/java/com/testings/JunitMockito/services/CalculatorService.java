package com.testings.JunitMockito.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public static int add(int a, int b) {
        return a + b;
    }
    public static int subtract(int a, int b) {
        return a - b;

    }
    public static int multiply(int a, int b) {
        return a * b;

    }
    public static int divide(int a, int b) {
        return a / b;
    }

    public  static  int sumAnyNumber(int... arr){

        int sum = 0;
        for(int i : arr){
            sum += i;
        }
        return sum;
    }

}
