package swust.qiy.microservice.sdk.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import swust.qiy.microservice.sdk.core.ApplicationProperties;
import swust.qiy.microservice.sdk.core.constant.ApplicationProperticeConstant;

/**
 * @author qiying
 * @create 2019/3/19
 */
@Configuration
public class CoreAutoConfiguration {

  @Autowired
  private Environment environment;

  @Bean
  @ConfigurationProperties(ApplicationProperticeConstant.ROOT_PROPERTY)
  public ApplicationProperties applicationProperties() {
    ApplicationProperties appProperties = ApplicationProperties.INSTANCE;
    // 从yml文件中获取
    appProperties.setServiceCode(environment.getProperty("spring.application.name"));
    appProperties.setAppCode(environment.getProperty("qiy.app.name"));
    appProperties.setSystemCode(environment.getProperty("qiy.system.name"));
    String appType = environment.getProperty("qiy.application-type");
    appProperties.setApplicationType(StringUtils.isEmpty(appType) ? "microservice" : appType);
    appProperties.setSvcVersion(
        environment.getProperty(ApplicationProperticeConstant.EUREKA_SERVICE_VERSION));
    // 获取实例id
    appProperties.setServiceInstanceId(IdUtils.getDefaultInstanceId(environment));
    return appProperties;
  }


}
