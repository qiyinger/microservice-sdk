package swust.qiy.microservice.sdk.eureka;

import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.ApplicationContextException;
import swust.qiy.microservice.sdk.core.ApplicationProperties;
import swust.qiy.microservice.sdk.core.constant.ApplicationProperticeConstant;

/**
 * @author qiying
 * @create 2019/3/26
 */
public class EurekaInstanceInfoPostProcessor implements BeanPostProcessor {
  private ApplicationProperties appProperties;

  public EurekaInstanceInfoPostProcessor(ApplicationProperties appProperties) {
    this.appProperties = appProperties;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof EurekaInstanceConfigBean) {
      try {
        EurekaInstanceConfigBean instanceConfigBean = (EurekaInstanceConfigBean) bean;
        if ("microservice".equals(appProperties.getApplicationType())) {
          instanceConfigBean
              .setAppname(appProperties.getServiceCode() + "-" + appProperties.getSvcVersion());
        }
        instanceConfigBean.setInstanceId(appProperties.getServiceInstanceId());
        // 在eureka实例初始化时设置metadata信息
        Map<String, String> map = instanceConfigBean.getMetadataMap();
        putMetadata(map, ApplicationProperticeConstant.EUREKA_SERVICE_VERSION,
            appProperties.getSvcVersion());
        putMetadata(map, ApplicationProperticeConstant.EUREKA_SERVICE_APP,
            appProperties.getAppCode());
        putMetadata(map, ApplicationProperticeConstant.EUREKA_SERVICE_SYSTEM,
            appProperties.getSystemCode());
      } catch (Throwable e) {
        throw new ApplicationContextException(e.getMessage());
      }
    }
    return bean;
  }

  private void putMetadata(Map<String, String> metadata, String key, String value) {
    if (!metadata.containsKey(key) && !StringUtils.isBlank(value)) {
      metadata.put(key, value);
    }
  }

}
