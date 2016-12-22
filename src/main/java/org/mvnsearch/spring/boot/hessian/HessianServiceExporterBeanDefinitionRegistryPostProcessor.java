package org.mvnsearch.spring.boot.hessian;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.remoting.caucho.HessianServiceExporter;

import java.util.Map;

/**
 * hessian service exporter bean definition registry post processor
 *
 * @author linux_china
 */
public class HessianServiceExporterBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
    private Map<String, Object> hessianServiceBeans;

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (Map.Entry<String, Object> entry : hessianServiceBeans.entrySet()) {
            BeanDefinition beanDefinition = createHessianServiceExporterBeanDefinition(entry.getValue());
            registry.registerBeanDefinition("/" + entry.getKey(), beanDefinition);
        }
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.hessianServiceBeans = applicationContext.getBeansWithAnnotation(HessianService.class);
    }

    private BeanDefinition createHessianServiceExporterBeanDefinition(Object hessianServiceBean) {
        HessianService[] annotationsByType = hessianServiceBean.getClass().getAnnotationsByType(HessianService.class);
        return BeanDefinitionBuilder.rootBeanDefinition(HessianServiceExporter.class)
                .addPropertyValue("service", hessianServiceBean)
                .addPropertyValue("serviceInterface", annotationsByType[0].serviceInterface())
                .getBeanDefinition();
    }
}
