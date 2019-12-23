package com.theta.process;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/** 
 *  Simple little ApplicationContext builder that
 *  lets you override beans by name.
 * 
 *  Example usage:
 *  <pre><code>
 *      ApplicationContext ctx = 
 *          new ContextBuilder().withFiles("verify.pubsub.xml",
 *                                         "verify.wfa.xml",
 *                                         "verify-context.xml")
 *                              .andObject("decider",mock(Decider.class))
 *                              .andObject("verify.filter", mock(Filter.class));
 *                              .buildContext();
 *      Dispatcher d = ctx.getBean("dispatcher",Dispatcher.class);
 *  </code></pre>
 *  
 *  And another using static imports:
 *  
 *  <pre><code>
 *      import static ContextBuilder.buildFrom;
 *  
 *      public class MyTestCase {
 *          String[] contexts = {
 *              "pubsub.xml", "verify.xml", "verify-context.xml"
 *          };
 *      
 *          @Test public void testSendsMessage() {
 *              Dispatcher d = mock(Dispatcher.class);
 *          
 *              ApplicationContext c = 
 *                  buildFrom(contexts).withBean("dispatcher",d).build();
 *          
 *              ...
 *          
 *              verify(d).sendMessage(any(IObjectMessage.class));
 *          }
 *      }
 *  </code></pre>
 */
public class ContextBuilder 
{

    protected ApplicationContext parent;
    
    protected GenericApplicationContext context;

    protected boolean built = false;

    protected void throwIfBuilt() 
    {
        if ( built ) 
        {
            throw new IllegalStateException("already built");
        }
    }

    public ContextBuilder() 
    {
        
    }
    
    protected final List<String> contexts = new LinkedList<String>();

    public ContextBuilder withFile(String f) 
    {
        throwIfBuilt();
        contexts.add(f);
        return this;
    }
    public ContextBuilder andFile(String f)
    {
        return withFile(f);
    }
    
    public ContextBuilder withFiles(String... fs) 
    {
        throwIfBuilt();
        for(String f : fs) 
        {
            this.withFile(f);
        }
        return this;
    }
    public ContextBuilder andFiles(String... fs)
    {
        return withFiles(fs);
    }
    
    public ContextBuilder(String... files) 
    {
        this();
        withFiles(files);
    }
    
    public static ContextBuilder builder() 
    {
        return new ContextBuilder();
    }
    public static ContextBuilder build(String... files)
    {
        return new ContextBuilder(files);
    }
    public static ContextBuilder builder(String... files)
    {
        return build(files);
    }
    public static ContextBuilder buildFrom(String... files) 
    {
        return build(files);
    }

    protected Map<String,Object> overrides = 
        new HashMap<String,Object>();
    
    public ContextBuilder withObject(String name, Object bean) 
    {
        throwIfBuilt();
        overrides.put(name, bean);
        return this;
    }
    // <Synonyms>
    public ContextBuilder withBean(String name, Object bean)
    {
        return withObject(name,bean);
    }
    public ContextBuilder andObject(String name, Object bean)
    {
        return withObject(name,bean);
    }
    public ContextBuilder andBean(String name, Object bean)
    {
        return withObject(name,bean);
    }
    // </Synonyms>
    
    public ContextBuilder withObjects(Object... args) 
    {
        throwIfBuilt();
        if ( args.length % 2 != 0 ) {
            throw new IllegalArgumentException("Need even number of args");
        }
        for(int i=0; i <= args.length-1; i+=2) {
            String name = args[i].toString();
            Object bean = args[i+1];
            this.withObject(name, bean);
        }
        return this;
    }
    
    // <Synonyms>
    public ContextBuilder withBeans(Object... args) 
    {
        return withObjects(args);
    }    
    public ContextBuilder andObjects(Object... args) 
    {
        return withObjects(args);
    }
    public ContextBuilder andBeans(Object... args) 
    {
        return withObjects(args);
    }
    // </Synonyms>

    public ContextBuilder withoutBean(String bean)
    {
        return withBean(bean,null);
    }
    
    // <Synonyms>
    public ContextBuilder withoutObject(String bean) 
    {
        return withoutBean(bean);
    }
    public ContextBuilder andNotBean(String bean)
    {
        return withoutBean(bean);
    }
    public ContextBuilder andNotObject(String bean)
    {
        return withoutBean(bean);
    }
    // </Synonyms>
    
    
    

    public ContextBuilder withParentContext(ApplicationContext parent) 
    {
        throwIfBuilt();
        this.parent = parent;
        return this;
    }
    public ContextBuilder withParent(ApplicationContext parent) 
    {
        return withParentContext(parent);
    }
    public ContextBuilder andParent(ApplicationContext parent)
    {
        return withParentContext(parent);
    }
    
    // Adding to lists
    
    //AddsBeansToListsBeanPostProcessor addsBeans = 
    //    new AddsBeansToListsBeanPostProcessor();
    
    /**
     * Convenience hook to integrate with the {@link AddsBeansToListsBeanPostProcessor}.
     * Allows you to add a bean to the end of a list once the list bean itself is
     * initialized.
     * 
     * @param list name of the list to add to
     * @param bean bean to add to the end of the list
     * @return this
     */
    public ContextBuilder withAdditionalBeanInList(String list, Object bean)
    {
        //addsBeans.addBeanToList(list, bean);
        return this;
    }
    
    public ContextBuilder andAdditionalBeanInList(String list, Object bean)
    {
        return withAdditionalBeanInList(list,bean);
    }
    
    
    
    
    Set<String[]> aliasPairs = new HashSet<String[]>();
    /**
     * @param alias alias to define
     * @param forBean bean behind the alias
     */
    public ContextBuilder withAlias(String alias, String forBean)
    {
        aliasPairs.add(new String[]{ alias, forBean });
        return this;
    }
    /**
     * @param alias alias to define
     * @param forBean bean behind the alias
     */
    public ContextBuilder andAlias(String alias, String forBean)
    {
        return withAlias(alias,forBean);
    }
    /*
    Don't feel like writing unit tests for this right now, but
    maybe in the future
    public ContextBuilder withAliases(String...args)
    {
        for(int i=0; i<args.length; i+=2)
        {
            String alias    = args[i];
            String forBean  = args[i+1];
            withAlias(alias,forBean);
        }
        return this;
    }
    */
    
    protected ResourceLoader loader;
    public ContextBuilder withResourceLoader(ResourceLoader loader)
    {
        throwIfBuilt();
        this.loader = loader;
        return this;
    }
    public ContextBuilder withDefaultResourceLoader()
    {
        return withResourceLoader(null);
    }
//    public ContextBuilder withBrazilBootstrapResourceLoader()
//    {
//        return withResourceLoader(new BrazilBootstrapResourceLoader());
//    }
//    public ContextBuilder withBrazilPathResourceLoader()
//    {
//        return withResourceLoader(new BrazilPathResourceLoader());
//    }
    
    protected Properties placeHolderProps = new Properties();
    /**
     * Resolve a ${placeholder} with the given values.
     */
    public ContextBuilder withPlaceHolder(String key, String value)
    {
        throwIfBuilt();
        placeHolderProps.put(key, value);      
        return this;
    }
    /**
     * Alias for {@link withPlaceHolder()}.
     */
    public ContextBuilder andPlaceholder(String key, String value)
    {
        return withPlaceHolder(key,value);
    }
    
    private PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() 
    {
        if ( placeHolderProps.isEmpty() ) 
        {
            return null;
        }
        
        PropertyPlaceholderConfigurer proper = new PropertyPlaceholderConfigurer();
        proper.setIgnoreUnresolvablePlaceholders(true);
        proper.setSearchSystemEnvironment(false);
        proper.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_NEVER);
        proper.setOrder(Ordered.LOWEST_PRECEDENCE);
        
        proper.setProperties(placeHolderProps);
        
        return proper;
    }

    public GenericApplicationContext buildContext() 
    {
        if ( built ) 
            return context;
        
        this.context = this.parent == null ? new GenericApplicationContext()
                                           : new GenericApplicationContext(this.parent);
        
        final XmlBeanDefinitionReader xReader =
            new XmlBeanDefinitionReader(this.context);
        
        ResourceLoader loader = this.loader == null ? this.context : this.loader;
        
        for (String c : contexts) 
        {
            xReader.loadBeanDefinitions(loader.getResource(c));            
        }
        
        PropertyPlaceholderConfigurer proper = getPropertyPlaceholderConfigurer();
        if ( proper != null )
            this.addBeanToContext("__contextBuilderProperties", proper);
        
        for (Map.Entry<String, Object> e : overrides.entrySet())
        {
            final String name = e.getKey();
            final Object bean = e.getValue();
            
            addBeanToContext(name, bean);
        }
        
        // Set aliases
        for(String[] pair : aliasPairs)
        {
            String alias   = pair[0];
            String forBean = pair[1];
            context.registerAlias(forBean, alias);
        }

        //if ( !addsBeans.isEmpty() )
        //{
        //    addBeanToContext("__contextBuilderAddsBeansToList", addsBeans);
        //}
        
        try 
        {
            context.refresh();
        }
        catch(RuntimeException x)
        {
            x.printStackTrace(System.err);
            throw x;
        }

        built = true;
        
        return context;
    }

    private void addBeanToContext(final String name,
                                  final Object bean)
    {
        // We need to special-case BeanFactoryPostProcessor
        // since a BFPP acts on BeanDefinitions and not on
        // constructed beans.  I.e., it acts earlier in the
        // lifecycle.  So a FactoryBean that produces a
        // BFPP is already too late since the production
        // within the BeanFactory has already started
        if ( bean instanceof BeanFactoryPostProcessor ) 
        {
            context.addBeanFactoryPostProcessor((BeanFactoryPostProcessor)bean);
        }
        if ( bean instanceof ApplicationListener<?> )
        {
            context.addApplicationListener((ApplicationListener<?>)bean);
        }
        
        BeanDefinition def = createBeanDefinition(bean);
        context.registerBeanDefinition(name, def);
    }
    
    private BeanDefinition createBeanDefinition(final Object obj) 
    {
        if ( obj instanceof BeanDefinition ) 
            return (BeanDefinition)obj;
        
        // else create a new BeanDefintion 
        BeanDefinition def = new GenericBeanDefinition()
        {
            private static final long 
                serialVersionUID = 7954289493731107292L;
            
            {
                super.setBeanClass(SimpleFactory.class);
                ConstructorArgumentValues cargs = new ConstructorArgumentValues();
                cargs.addIndexedArgumentValue(0, new ValueHolder(obj));
                super.setConstructorArgumentValues(cargs);
            }
        };
        
        return def;
    }
    
    
    // <Synonyms>
    public GenericApplicationContext build() 
    {
        return buildContext();
    }
    public GenericApplicationContext getContext() 
    {
        return buildContext();
    }
    public GenericApplicationContext context() 
    {
        return buildContext();
    }
    // </Synonyms>
    
    
    
    /**
     * Simple helper class that delegates to pre-created POJOs.
     * 
     * I've added examples of how this class can be extended to
     * allow for overriding beans to implement {@link InitializingBean} and
     * {@link DisposableBean} as proof-of-concept. This could
     * be extended to allow for overriding beans to hook into other
     * parts of the bean lifecycle (e.g., 
     * {@link ApplicationEventPublisherAware}),
     * but I don't anticipate this being a useful (or advisable)
     * thing to do for the sort of "quick" unit-tests that
     * {@link ContextBuilder} is designed to support. Those sorts of
     * cases should probably use the traditional {@link SpringJUnit4ClassRunner}
     * with XML-based definitions.
     * 
     * @author rtimmons
     */
    protected static class SimpleFactory<T> implements FactoryBean<T>,
                                                       DisposableBean,
                                                       InitializingBean,
                                                       ApplicationContextAware,
                                                       BeanPostProcessor
    {
        private final T obj;
        private final Class<?> clazz;

        public SimpleFactory(T obj)
        {
            this.obj = obj;
            this.clazz = obj != null ? obj.getClass() : null;
        }

        @Override
        public T getObject() throws Exception
        {
            return obj;
        }

        @Override
        public Class<? extends T> getObjectType()
        {
            return (Class<? extends T>) clazz;
        }

        @Override
        public boolean isSingleton()
        {
            return false;
        }

        @Override
        public void destroy() throws Exception
        {
            if ( obj instanceof DisposableBean ) 
            {
                ((DisposableBean)obj).destroy();
            }
        }

        @Override
        public void afterPropertiesSet() throws Exception
        {
            if ( obj instanceof InitializingBean )
            {
                ((InitializingBean)obj).afterPropertiesSet();
            }
        }
        
        // Called after the afterPropertiesSet methods are called
        @Override
        public void setApplicationContext(ApplicationContext ctx)
            throws BeansException
        {
            if ( obj instanceof ApplicationContextAware )
            {
                ((ApplicationContextAware)obj).setApplicationContext(ctx);
            }
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException
        {
            if ( obj instanceof BeanPostProcessor )
            {
                return ((BeanPostProcessor)obj).postProcessBeforeInitialization(bean, beanName);
            }
            return bean;
        }
        
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException
        {
            if ( obj instanceof BeanPostProcessor )
            {
                return ((BeanPostProcessor)obj).postProcessAfterInitialization(bean, beanName);
            }
            return bean;
        }
        
    }
}

