package com.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TestAppium1 {
    public static void main(String[] args) throws MalformedURLException {
        // 配置DesiredCapabilities
        DesiredCapabilities desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCaps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14");
        desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, "xxx");
        desiredCaps.setCapability("appPackage", "cn.damai");
        desiredCaps.setCapability("appActivity", ".launcher.splash.SplashMainActivity");
        desiredCaps.setCapability("unicodeKeyboard", true);
        desiredCaps.setCapability("resetKeyboard", true);
        desiredCaps.setCapability("noReset", true);
        desiredCaps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 6000);
        desiredCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

        // 在创建 desiredCaps 后添加
        desiredCaps.setCapability("androidSdkPath", "D:\\Appium\\androidsdk\\androidsdk");

        // 连接Appium Server，初始化自动化环境
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCaps);

        // 设置缺省等待时间
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

//        // 如果有`青少年保护`界面，点击`我知道了`
//        List<WebElement> iknowElements = driver.findElements(By.id("text3"));
//        if (!iknowElements.isEmpty()) {
//            iknowElements.getFirst().click();
//        }

        try {
            // 根据id定位搜索位置框，点击
            driver.findElement(By.id("expand_search")).click();

            // 根据id定位搜索输入框，点击
            WebElement searchBox = driver.findElement(By.id("search_src_text"));
            searchBox.sendKeys("白月黑羽");

            // 输入回车键，确定搜索
            driver.pressKey(new KeyEvent(AndroidKey.ENTER));

            // 选择（定位）所有视频标题
            List<WebElement> titleElements = driver.findElements(By.id("title"));

            for (WebElement element : titleElements) {
                // 打印标题
                System.out.println(element.getText());
            }

            System.out.println("**** Press to quit..");
            System.in.read(); // 等待用户输入

            driver.quit();
        } catch (IOException e) {
            System.out.println("有异常");
            throw new RuntimeException(e);
        }
    }
}
