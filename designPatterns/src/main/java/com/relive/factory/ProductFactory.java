package com.relive.factory;

/**
 * @author: ReLive
 * @date: 2022/5/15 8:39 下午
 */
public interface ProductFactory {

    <T extends Product> T createProduct(Class<T> clazz);
}
