package swust.qiy.microservice.sdk.core;

/**
 * @author qiying
 * @create 2019/3/19
 */
public class ApplicationProperties {
  public static final ApplicationProperties INSTANCE = new ApplicationProperties();

  /**
   * 服务编码.
   */
  private String serviceCode;
  /**
   * 服务实例id.
   */
  private String serviceInstanceId;
  /**
   * 服务版本.
   */
  private String svcVersion;
  /**
   * 应用编码.
   */
  private String appCode;
  /**
   * 系统群编码.
   */
  private String systemCode;

  private Integer thisId;
  /**
   * 兼容的服务版本, 如"1.0, 1.1"
   */
  private String compatibleSvcVersions;

  private String applicationType;

  public String getServiceCode() {
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public String getServiceInstanceId() {
    return serviceInstanceId;
  }

  public void setServiceInstanceId(String serviceInstanceId) {
    this.serviceInstanceId = serviceInstanceId;
  }

  public String getSvcVersion() {
    return svcVersion;
  }

  public void setSvcVersion(String svcVersion) {
    this.svcVersion = svcVersion;
  }

  public String getAppCode() {
    return appCode;
  }

  public void setAppCode(String appCode) {
    this.appCode = appCode;
  }

  public String getSystemCode() {
    return systemCode;
  }

  public void setSystemCode(String systemCode) {
    this.systemCode = systemCode;
  }

  public String getCompatibleSvcVersions() {
    return compatibleSvcVersions;
  }

  public void setCompatibleSvcVersions(String compatibleSvcVersions) {
    this.compatibleSvcVersions = compatibleSvcVersions;
  }

  public String getApplicationType() {
    return applicationType;
  }

  public void setApplicationType(String applicationType) {
    this.applicationType = applicationType;
  }

  public Integer getThisId() {
    return thisId;
  }

  public void setThisId(Integer thisId) {
    this.thisId = thisId;
  }
}
