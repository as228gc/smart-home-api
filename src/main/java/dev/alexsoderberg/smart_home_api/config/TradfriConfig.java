package dev.alexsoderberg.smart_home_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tradfri")
public class TradfriConfig {
  
  private String gatewayUrl;
  private String apiKey;

  public String getGatewayUrl() {
    return this.gatewayUrl;
  }

  public void setGatewayUrl(String url) {
    this.gatewayUrl = url;
  }

  public String getApiKey() {
    return this.apiKey;
  }

  public void setApiKey(String key) {
    this.apiKey = key;
  }
}
