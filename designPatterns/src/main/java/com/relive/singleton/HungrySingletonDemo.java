package com.relive.singleton;

/**
 * @author: ReLive
 * @date: 2022/5/12 8:31 下午
 */
public class HungrySingletonDemo {

    private static final HungrySingletonDemo singleton = new HungrySingletonDemo();

    //限制多个对象
    private HungrySingletonDemo() {
    }

    //通过该方法获得实例对象
    public static HungrySingletonDemo getSingleton() {
        return singleton;
    }

    //在类中其他方法，尽量是static
    public static void doSomething() {

    }
}
