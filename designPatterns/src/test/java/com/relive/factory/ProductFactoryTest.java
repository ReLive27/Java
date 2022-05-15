package com.relive.factory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: ReLive
 * @date: 2022/5/15 8:42 下午
 */
public class ProductFactoryTest {

    @Test
    public void factoryTest() {
        ProductFactory factory = new ConcreteProductFactory();
        Assert.assertTrue(factory.createProduct(ConcreteProduct1.class) instanceof ConcreteProduct1);
    }
}
