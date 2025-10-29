package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
    @Test
    public void test1(){
        System.out.println(1);
        System.out.printf("Thread Id : %s%n", Thread.currentThread().threadId());
    }

    @Test
    public void test2(){
        System.out.println(2);
        System.out.printf("Thread Id : %s%n", Thread.currentThread().threadId());
    }

    @Test
    public void test3(){
        System.out.println(3);
        System.out.printf("Thread Id : %s%n", Thread.currentThread().threadId());
    }
}
