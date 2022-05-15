package com.relive.factory.lazy;

import com.relive.factory.ConcreteProduct1;
import com.relive.factory.ConcreteProduct2;
import com.relive.factory.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ReLive
 * @date: 2022/5/15 10:24 下午
 */
public class LazyProductFactory {
    private static final Map<String, Product> map = new HashMap<>();

    public static synchronized Product createProduct(String type) {
        Product product;
        if (map.containsKey(type)) {
            product = map.get(type);
        } else {
            if ("product1".equals(type)) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
            map.put(type, product);
        }
        return product;
    }
}
