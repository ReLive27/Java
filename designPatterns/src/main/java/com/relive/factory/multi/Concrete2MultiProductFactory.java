package com.relive.factory.multi;

import com.relive.factory.ConcreteProduct1;
import com.relive.factory.ConcreteProduct2;
import com.relive.factory.Product;

/**
 * @author: ReLive
 * @date: 2022/5/15 10:11 下午
 */
public class Concrete2MultiProductFactory implements MultiProductFactory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct2();
    }
}
