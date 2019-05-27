package swust.qiy.microservice.sdk.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiying
 * @create 2019/5/6
 */
@Configuration
public class ApolloClientAutoConfiguration implements ConfigChangeListener, InitializingBean {

  @Autowired
  private RefreshScope refreshScope;

  Logger log = LoggerFactory.getLogger(ApolloClientAutoConfiguration.class);
  @Bean
  @ConditionalOnProperty("app.id")
  public Config apolloConfig() {
    Config config = ConfigService.getConfig("V1");
    config.addChangeListener(this);//设置监听
    return config;
  }


  @Override
  public void onChange(ConfigChangeEvent changeEvent) {
    log.info("[changeHandler]Changes for namespace {}", changeEvent.getNamespace());
    for (String key : changeEvent.changedKeys()) {
      ConfigChange change = changeEvent.getChange(key);
      log.info("[changeHandler]Change - key: {}, oldValue: {}, newValue: {}, changeType: {}",
          change.getPropertyName(), change.getOldValue(), change.getNewValue(),
          change.getChangeType());
    }

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    refreshScope.refreshAll();
  }
}
