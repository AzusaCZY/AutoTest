package com.course.testng.parameter;

import com.beust.jcommander.Parameter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {
    @Test
    @Parameters({"name","age"})
    public void dog(String name, int age){
        System.out.println("name:"+name+" age:"+age);
    }

    @Test
    public void random(){
        //随机生成五个1-28的数字，并去重
            int arr = (int)(Math.random()*23+1);
            System.out.println(arr);
    }
}
