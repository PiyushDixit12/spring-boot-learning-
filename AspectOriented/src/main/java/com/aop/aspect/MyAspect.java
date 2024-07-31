package com.aop.aspect;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    /**
     *       **** wildCard
     * 1. you can pass as public string com.aop.service.PaymentServiceImp.makePayment() if specific
     * 2. you can also as * com.aop.service.PaymentServiceImpl.makePayment() for same purpose with different return type
     * 3. you can also as * com.aop.service.PaymentServiceImpl.*(String) for specific parameter that execute after this code
     * 4. you can also as public string com.aop.service.PaymentServiceImpl.makePayment(*) you can use if you have multiple overloaded methods but not 0 argument
     * 5. you can also as public string com.aop.service.PaymentServiceImpl.makePayment(..) use you have not any argument or have any arguments
     * 6. you can also as public string com.aop..makePayment(..) use you have any method in particular package it can nested
     * 7. you can also as  public string com.aop..*() use inside this nested package have any method name
     *
     *
     * */
    @Before("execution(* com.aop.service.PaymentServiceImpl.makePayment())")
    public void printBefore(){
        System.out.println("running before payment");
    }

    @After("execution(* com.aop.service.PaymentServiceImpl.makePayment())")
    public void printAfter(){
        System.out.println("running after payment");
    }

//    you can also pass  if any annotation is applied on which method you want
    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping )")
    public void printBeforeAnnotation(){
        System.out.println("running before annotation");
    }

//creating a point cut only not executing
    @Pointcut("execution(* com.aop.service.PaymentServiceImpl.makePayment())")
    public void servicePointcut(){
        System.out.println("running pointcut it will run for service method which have arguments");
    }

    @After("servicePointcut()")
    public void printAfterReturning(){
        System.out.println("running after returning payment");
    }

}
