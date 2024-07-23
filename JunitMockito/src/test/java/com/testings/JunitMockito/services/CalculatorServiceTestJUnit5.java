package com.testings.JunitMockito.services;


import org.junit.jupiter.api.*;

public class CalculatorServiceTestJUnit5 {

//   @Tag  : used for tagging used for filter
//    @Nested : used for nested inner classes
//    TestFactory

   int counter = 0;

    @BeforeAll
    public static void init() {
//    connection or any other code here
        System.out.println("init method calling....");
    }

    @BeforeEach
    public void beforeEachTest() {

        System.out.println("you can write any code inside to run it before each test");
        System.out.println("Counter is " + counter);
    }

    //    testing add method
//    timeout used for check method should give result before time
    @Test
    @DisplayName("This is addition test")
    public void addTest() {
        for (int i = 0; i < 5; i++) {
            counter += i;
        }
        System.out.println("add method calling....");
        int expectedResult = 3;
        int result = CalculatorService.add(1, 2);
        Assertions.assertEquals(expectedResult, result, "Test failed of addition");
    }


    @Test
    @Disabled
    public void subtractTest() {
        System.out.println("subtract method calling....");
        int expectedResult = -1;
        int result = CalculatorService.subtract(1, 2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void multiplyTest() {
        counter++;
        System.out.println("multiply method calling....");
        int expectedResult = 2;
        int result = CalculatorService.multiply(1, 2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void divisionTest() {
        System.out.println("division method calling....");
        int expectedResult = 2;
        int result = CalculatorService.divide(4, 2);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void sumAnyNumbersTest() {
        System.out.println("sumAnyNumbers method calling....");
        int expectedResult = 15;
        int result = CalculatorService.sumAnyNumber(1, 2, 3, 4, 5);
        Assertions.assertEquals(expectedResult, result);
    }

    //    after each test
    @AfterEach
    public void afterEachTest() {
        System.out.println("you can write any code inside to run it after each test");
    }

    //
//    //    cleanup code
    @AfterAll
    public static void cleanup() {
//    any code to close a file or like codes
        System.out.println("cleanup method calling....");
    }
}
