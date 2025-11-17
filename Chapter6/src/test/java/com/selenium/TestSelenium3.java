package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Scanner;

public class TestSelenium3 {
    public static void main(String[] args) {
        // 创建WebDriver对象
        WebDriver wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 调用get方法打开指定网址
        wd.get("https://www.byhy.net/cdn2/files/selenium/test3.html");

        WebElement element = wd.findElement(By.id("input1"));
        element.clear();
        element.sendKeys("羊宫妃那");
        //sleep(1000);

        // 创建Scanner对象等待用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("等待回车键结束程序");
        scanner.next();

        wd.quit();
    }
}
