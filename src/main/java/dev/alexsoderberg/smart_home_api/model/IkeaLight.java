package dev.alexsoderberg.smart_home_api.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dev.alexsoderberg.smart_home_api.config.TradfriConfig;

@Component
public class IkeaLight extends Device {

  private TradfriConfig config;
  private int lightId;

  public IkeaLight(String name, int lightId, TradfriConfig config) {
    super(name, DeviceType.LIGHT);
    this.lightId = lightId;
    this.config = config;
  }

  public int getLightId() {
    return this.lightId;
  }

  @Override
  protected void handleTurnOn() {
    sendCommandToGateway("on");
  }

  @Override
  protected void handleTurnOff() {
    sendCommandToGateway("off");
  }

  private void sendCommandToGateway(String action) {
    String url = String.format("%s/lights/%d/state", config.getGatewayUrl(), lightId);

    // Create the request body for the light command
    String body = String.format("{\"status\":\"%s\"}", action);

    // Set up the HTTP headers
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + config.getApiKey());
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Make the API call
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> request = new HttpEntity<>(body, headers);

    try {
      restTemplate.postForObject(url, request, String.class);
      System.out.printf("Sent '%s' command to IKEA Light with ID %d%n", action, lightId);
    } catch (Exception e) {
      System.err.printf("Failed to send '%s' command to IKEA Light with ID %d: %s%n", action, lightId, e.getMessage());
    }
  }
}
