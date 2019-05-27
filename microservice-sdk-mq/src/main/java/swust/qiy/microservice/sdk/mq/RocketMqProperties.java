package swust.qiy.microservice.sdk.mq;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qiying
 * @create 2019/4/10
 */
@ConfigurationProperties(RocketMqProperties.PREFIX)
@Data
public class RocketMqProperties {
  public static final String PREFIX = "qiy.rocketmq";
  private String namesrvAddr;
  private String producerGroupName;
  private String transactionProducerGroupName;
  private String consumerGroupName;
  private String producerInstanceName;
  private String consumerInstanceName;
  private String producerTranInstanceName;
  private int consumerBatchMaxSize;
  private boolean consumerBroadcasting;
  private boolean enableHisConsumer;
  private boolean enableOrderConsumer;
  private List subscribe = new ArrayList<>();
}
