package com.selenium;

import org.testng.annotations.Test;
@Test
public class TestSelenium4 {

    public void testLogin1(){
        TestSelenium2 testSelenium2 = new TestSelenium2();
        testSelenium2.Login("byhy", "88888888");
    }

    public void testLogin2(){
        TestSelenium2 testSelenium2 = new TestSelenium2();
        testSelenium2.Login("", "88888888");
    }

    public void testLogin3(){
        TestSelenium2 testSelenium2 = new TestSelenium2();
        testSelenium2.Login("byhy","");
    }

    public void testLogin4(){
        TestSelenium2 testSelenium2 = new TestSelenium2();
        testSelenium2.Login("byhy","12345678");
    }

    public void testLogin5(){
        TestSelenium2 testSelenium2 = new TestSelenium2();
        testSelenium2.Login("ygfn", "88888888");
    }
}
