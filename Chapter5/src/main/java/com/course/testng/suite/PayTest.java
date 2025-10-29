package com.course.testng.suite;

import org.testng.annotations.Test;

public class PayTest {

    @Test
    public void paySuccess(){
        System.out.println("支付成功");
    }

    @Test(dependsOnMethods = {"paySuccess"})
    public void payTest(){
        System.out.println("测试支付功能");
    }
}
