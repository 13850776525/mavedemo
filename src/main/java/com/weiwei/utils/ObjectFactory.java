package com.weiwei.utils;

public class ObjectFactory {

    public static Object getObject(String className){
        Object obj=null;
        try
        {
            System.out.println(className);
            Class classz = Class.forName(className);
            obj = classz.newInstance();
            System.out.println("工厂内的对象：" +obj);
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }
}
