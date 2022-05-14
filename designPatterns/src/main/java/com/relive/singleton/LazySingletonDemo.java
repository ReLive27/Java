package com.relive.singleton;

/**
 * @author: ReLive
 * @date: 2022/5/12 1:12 下午
 */
public class LazySingletonDemo {
    private static LazySingletonDemo singleton = null;

    //限制产生多个对象
    private LazySingletonDemo() {
    }

    public static LazySingletonDemo getSingleton() {
        if (singleton == null) {
            singleton = new LazySingletonDemo();
        }
        return singleton;
    }
}
