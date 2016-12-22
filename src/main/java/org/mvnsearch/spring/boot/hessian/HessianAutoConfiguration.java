package org.mvnsearch.spring.boot.hessian;

import com.caucho.hessian.io.SerializerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * hessian auto configuration
 *
 * @author linux_china
 */
@Configuration
@ConditionalOnClass({HessianServiceExporter.class, SerializerFactory.class})
public class HessianAutoConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public HessianServiceExporterBeanDefinitionRegistryPostProcessor hessianServiceExporterBeanDefinitionRegistryPostProcessor() {
        return new HessianServiceExporterBeanDefinitionRegistryPostProcessor();
    }

    @Bean
    public HessianEndpoint hessianEndpoint() {
        return new HessianEndpoint();
    }

}
