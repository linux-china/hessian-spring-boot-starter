package org.mvnsearch.spring.boot.hessian;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * hessian endpoint
 *
 * @author linux_china
 */
@ConfigurationProperties(prefix = "endpoints.hessian", ignoreUnknownFields = false)
public class HessianEndpoint extends AbstractEndpoint<List<String>> implements ApplicationContextAware {
    private Map<String, Object> hessianServiceBeans;

    public HessianEndpoint() {
        super("hessian");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.hessianServiceBeans = applicationContext.getBeansWithAnnotation(HessianService.class);
    }

    public List<String> invoke() {
        List<String> interfaces = new ArrayList<>();
        for (Object hessianServiceBean : hessianServiceBeans.values()) {
            HessianService[] annotationsByType = hessianServiceBean.getClass().getAnnotationsByType(HessianService.class);
            interfaces.add(annotationsByType[0].serviceInterface().toString());
        }
        return interfaces;
    }
}
