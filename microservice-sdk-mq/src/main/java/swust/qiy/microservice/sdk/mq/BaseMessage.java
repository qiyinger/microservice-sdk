package swust.qiy.microservice.sdk.mq;

/**
 * @author qiying
 * @create 2019/4/19
 */
public abstract class BaseMessage {

  private String topic;

  private String tag;

  private String key;

  private byte[] body;

  public BaseMessage(String topic, String tag, String key, String body) {
    this(topic, tag, key, body.getBytes());
  }

  public BaseMessage(String topic, String tag, String key, byte[] body) {
    this.topic = topic;
    this.tag = tag;
    this.key = key;
    this.body = body;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public byte[] getBody() {
    return body;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  public void setBody(String body) {
    this.body = body.getBytes();
  }
}
