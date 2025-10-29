package com.course.testng.suite;

import org.testng.annotations.Test;

@Test(groups = "login")
public class LoginTest {

    @Test
    public void loginTest(){
        System.out.println("这是登录测试");
    }
}
