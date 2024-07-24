package com.testings.JunitMockito.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public static int add(int a, int b) {
        logger.info("Adding {} and {}", a, b);
        return a + b;
    }
    public static int subtract(int a, int b) {
        logger.info("Subtracting {} and {}", a, b);
        return a - b;

    }
    public static int multiply(int a, int b) {
        logger.info("Multiplying {} and {}", a, b);
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
