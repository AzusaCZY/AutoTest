package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class TestSelenium2 {
    public void Login(String userName, String passWord){
        // 创建WebDriver对象
        WebDriver wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 调用get方法打开指定网址
        wd.get("http://127.0.0.1/mgr/sign.html");
        WebElement username = wd.findElement(By.id("username"));
        username.sendKeys(userName);
        WebElement password = wd.findElement(By.id("password"));
        password.sendKeys(passWord);
        WebElement login = wd.findElement(By.className("btn-primary"));
        login.click();

//        List<WebElement> element = wd.findElements(By.className("sidebar-menu"));
//
//        for (WebElement e : element) {
//            System.out.println(e.getText());
//        }


        // 关闭浏览器
        //wd.quit();
    }
}
