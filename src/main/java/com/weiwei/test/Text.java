package com.weiwei.test;

import com.weiwei.aop.SleepAble;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Text {
    /**
     * 测试类
     */
    public static void main(String args[]){
        String config = "aopDemo01.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config);
        SleepAble student = (SleepAble) context.getBean("student");
        student.sleep();

    }


}
