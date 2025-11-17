
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;



public class TestSelenium {
    public static void main(String[] args) {
        // 创建WebDriver对象
        WebDriver wd = new ChromeDriver();

        // 调用get方法打开指定网址
        wd.get("https://www.byhy.net/cdn2/files/selenium/sample1.html");

        List<WebElement> element = wd.findElements(By.tagName("div"));

        //element.sendKeys("selenium");
        //输出element的每个元素
        for (WebElement e : element) {
            System.out.println(e.getText());
        }


        // 创建Scanner对象等待用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("等待回车键结束程序");
        scanner.next();

        // 关闭浏览器
        wd.quit();
    }
}
