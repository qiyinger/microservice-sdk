package swust.qiy.microservice.sdk;

import static swust.qiy.microservice.sdk.core.constant.ApplicationProperticeConstant.APPLICATION_MICROSERVICE_TYPE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import swust.qiy.microservice.sdk.core.ApplicationProperties;
import swust.qiy.microservice.sdk.core.constant.DatasourceConfigConstant;

/**
 * @author qiying
 * @create 2019/3/21
 */
public class StarterPreparedEventListener implements
    ApplicationListener<ApplicationPreparedEvent> {

  ApplicationProperties applicationProperties;

  public StarterPreparedEventListener(
      ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  @Override
  public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
    try {
      Class.forName(DatasourceConfigConstant.DRIVER);
      Connection connection = DriverManager.getConnection(DatasourceConfigConstant.DB_URL,
          DatasourceConfigConstant.USERNAME, DatasourceConfigConstant.PASSWORD);
      PreparedStatement ps = null;
      if (applicationProperties.getApplicationType().equals(APPLICATION_MICROSERVICE_TYPE)) {
        String serviceName = applicationProperties.getServiceCode();
        String version = applicationProperties.getSvcVersion();
        String sql = "SELECT id FROM microservice_version WHERE VERSION = ? and MICROSERVICE_ID = (SELECT ID FROM microservice WHERE code = ?);";
        ps = connection.prepareStatement(sql);
        ps.setString(1, version);
        ps.setString(2, serviceName);
      } else if (applicationProperties.getApplicationType().equals("gateway")) {
        String serviceName =
            applicationProperties.getSystemCode() + "-" + applicationProperties.getServiceCode();
        String sql = "SELECT id FROM gateway WHERE all_code = ? and status = 1;";
        ps = connection.prepareStatement(sql);
        ps.setString(1, serviceName);
      } else {
        throw new Exception(
            "you must be config application-type as microservice or gateway in config file");
      }
      ResultSet resultSet = ps.executeQuery();
      if (!resultSet.next()) {
        throw new Exception("you must be config service and version in management web");
      }
      applicationProperties.setThisId(resultSet.getInt(1));
      resultSet.close();
      connection.close();
    } catch (Exception e) {
      throw new ApplicationContextException(e.getMessage());
    }

  }
}
