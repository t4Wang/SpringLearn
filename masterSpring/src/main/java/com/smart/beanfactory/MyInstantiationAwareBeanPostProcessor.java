package com.smart.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    // 接口方法，在实例化Bean前调用
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName)
            throws BeansException {
        // 进队容器中的car Bean处理
        if ("car".equals(beanName)) {
            System.out.println("InstantiationAware BeanPostProcessor." +
                    "postProcessBeforeInstantiation()");
        }
        return null;
    }

    // 接口方法：在实例化Bean后调用
    public boolean postProcessAfterInstantiation(Object bean, String beanName)
            throws BeansException {
        // 仅对容器中的car Bean进行处理
        if ("car".equals(beanName)) {
            System.out.println("InstantiationAware BeanPostProcessor." +
                    "postProcessAfterInstantiation()");
        }
        return true;
    }

    // 接口方法：在设置某个属性时调用
    public PropertyValues postProcessPropertyValues(
            PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        // 仅对容器中的car Bean进行处理，还可以通过pdst入参进行过滤，
        // 仅对car的某个特定属性值进行处理
        if ("car".equals(beanName)) {
            System.out.println("Instantiation AwareBeanPostProcessor." +
                    "postProcessPropertyValues");
        }
        return pvs;
    }
}
