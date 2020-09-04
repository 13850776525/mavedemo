package com.weiwei.aop;

public class Student implements SleepAble{
    @Override
    public void sleep() {
        System.out.println("一定要早睡！！晚安");
        String a = "sad";
        int b = Integer.valueOf(a);
    }
}
