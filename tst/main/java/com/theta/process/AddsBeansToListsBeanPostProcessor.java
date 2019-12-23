//package com.theta.process;
//
//import java.util.Collection;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//
//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Multimap;
//
//public class AddsBeansToListsBeanPostProcessor implements BeanPostProcessor
//{
//
//    private Multimap<String, Object> toAdd = ArrayListMultimap.create();
//
//    public void addBeanToList(String list,
//                              Object bean)
//    {
//        toAdd.put(list, bean);
//    }
//
//    public boolean isEmpty()
//    {
//        return toAdd.isEmpty();
//    }
//
//    @Override
//    public Object
//        postProcessBeforeInitialization(Object bean,
//                                        String beanName) throws BeansException
//    {
//        return addBeanToObject(bean, beanName);
//    }
//
//    /**
//     * Allow the bean to be initialized first, so it can be a FactoryBean
//     * that returns a List, e.g., the ListFactoryBean that <util:list> gives
//     * you.
//     */
//    @Override
//    public Object
//        postProcessAfterInitialization(Object bean,
//                                       String beanName) throws BeansException
//    {
//        return addBeanToObject(bean, beanName);
//    }
//    
//    
//    private Object addBeanToObject(Object bean, String beanName)
//    {
//        if (!(bean instanceof Collection))
//        {
//            return bean;
//        }
//
//        Collection<Object> items = (Collection<Object>)bean;
//        Collection<Object> additions = toAdd.get(beanName);
//        items.addAll(additions);
//        
//        return bean;
//    }
//    
//}
//
