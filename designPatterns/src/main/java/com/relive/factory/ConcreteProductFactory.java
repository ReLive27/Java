package com.relive.factory;

/**
 * @author: ReLive
 * @date: 2022/5/15 8:40 下午
 */
public class ConcreteProductFactory implements ProductFactory {
    @Override
    public <T extends Product> T createProduct(Class<T> clazz) {
        Product product = null;
        try {
            product = (Product) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            //异常处理
        }
        return (T) product;
    }
}
