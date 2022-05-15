package com.relive.factory.multi;

import com.relive.factory.ConcreteProduct1;
import com.relive.factory.Product;

/**
 * @author: ReLive
 * @date: 2022/5/15 10:10 下午
 */
public class Concrete1MultiProductFactory implements MultiProductFactory{
    @Override
    public Product createProduct() {
        return new ConcreteProduct1();
    }
}
