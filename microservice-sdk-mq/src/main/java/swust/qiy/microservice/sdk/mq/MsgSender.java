package swust.qiy.microservice.sdk.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author qiying
 * @create 2019/4/20
 */
public class MsgSender {

  private DefaultMQProducer defaultMQProducer;

  public MsgSender(DefaultMQProducer defaultMQProducer) {
    this.defaultMQProducer = defaultMQProducer;
  }

  public void sendMsg(BaseMessage message)
      throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    defaultMQProducer.send(
        new Message(message.getTopic(), message.getTag(), message.getKey(), message.getBody()));
  }

}
