package swust.qiy.microservice.sdk.core.util;

import org.springframework.core.env.PropertyResolver;
import org.springframework.util.StringUtils;
import swust.qiy.microservice.sdk.core.ApplicationProperties;

/**
 * @author qiying
 * @create 2019/5/24
 */
public class IdUtil {

  public static String combineParts(String firstPart, String separator, String secondPart) {
    String combined = null;
    if (firstPart != null && secondPart != null) {
      combined = firstPart + separator + secondPart;
    } else if (firstPart != null) {
      combined = firstPart;
    } else if (secondPart != null) {
      combined = secondPart;
    }

    return combined;
  }

}
