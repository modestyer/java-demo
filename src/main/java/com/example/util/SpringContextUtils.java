package com.example.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author liuf
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/3/13
 **/
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }


    public static ApplicationContext getContext() {
        return context;
    }

    public static void autowireBean(Object bean) {
        context.getAutowireCapableBeanFactory().autowireBean(bean);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(bean.getClass());
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        context.getAutowireCapableBeanFactory().applyBeanPostProcessorsAfterInitialization(beanDefinition, bean.getClass().getName());
        beanFactory.registerBeanDefinition(bean.getClass().getName(), beanDefinition);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
