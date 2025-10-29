package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

public class DateProviderTest {
    @Test(dataProvider = "data")
    public void test1(String name, int age){
        System.out.println("name:"+name+" age:"+age);
    }

    @Test(dataProvider = "data")
    public void test2(String name, int age){
        System.out.println("name:"+name+" age:"+age);
    }

    @DataProvider(name = "data")
    public Object[][] data(Method method){
        if(method.getName().equals("test1")){
            return new Object[][]{
                    {"张三",10},
                    {"lisi",20},
                    {"wangwu",30}
            };
        }
        else if(method.getName().equals("test2")){
            return new Object[][]{
                    {"ygfn",20},
                    {"qmyc",20},
                    {"hgcn",30}
            };
        }
        return new Object[][]{
                {"何意味",10},
        };
    }
}
