package com.relive.singleton;

/**
 * @author: ReLive
 * @date: 2022/5/12 1:08 下午
 */
public class DCLSingletonDemo {
    private static volatile DCLSingletonDemo singleton = null;

    //限制产生多个对象
    private DCLSingletonDemo() {
    }

    //双重检锁
    public static DCLSingletonDemo getSingleton() {
        if (singleton == null) {
            synchronized (DCLSingletonDemo.class) {
                if (singleton == null) {
                    singleton = new DCLSingletonDemo();
                }
            }
        }
        return singleton;
    }
}