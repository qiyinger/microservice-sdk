package swust.qiy.microservice.sdk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import swust.qiy.microservice.sdk.StarterPreparedEventListener;
import swust.qiy.microservice.sdk.core.ApplicationProperties;
import swust.qiy.microservice.sdk.core.constant.ApplicationProperticeConstant;
import swust.qiy.microservice.sdk.eureka.EurekaInstanceInfoPostProcessor;

/**
 * @author qiying
 * @create 2019/3/19
 */
@ConditionalOnProperty(name = ApplicationProperticeConstant.ROOT_PROPERTY, havingValue = ApplicationProperticeConstant.ENABLE)
@PropertySource("classpath:micro-endpoint.properties")
@PropertySource("classpath:micro-eureka.properties")
@Configuration
public class MiceroAutoConfiguration {

  @Bean
  @DependsOn("applicationProperties")
  StarterPreparedEventListener serviceStarterAppPreparedEventListener(
      ApplicationProperties applicationProperties) {
    return new StarterPreparedEventListener(applicationProperties);
  }

  @Bean
  @DependsOn("applicationProperties")
  EurekaInstanceInfoPostProcessor eurekaInstanceInfoPostProcessor(
      ApplicationProperties applicationProperties) {
    return new EurekaInstanceInfoPostProcessor(applicationProperties);
  }

}
