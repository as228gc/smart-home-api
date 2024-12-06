package dev.alexsoderberg.smart_home_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import dev.alexsoderberg.smart_home_api.config.TradfriConfig;

public class IkeaLightTest {

  private IkeaLight ikeaLight;
  private TradfriConfig config;

  @BeforeEach
  void setUp() {
    config = mock(TradfriConfig.class);
    when(config.getGatewayUrl()).thenReturn("http://mock-gateway");
    when(config.getApiKey()).thenReturn("mock-api-key");

    ikeaLight = new IkeaLight("Living Room Light", 123, config);
  }

  @Test
  void turnOnShouldSetDeviceStatusToOn() {
    // Mock RestTemplate behavior
    RestTemplate restTemplate = mock(RestTemplate.class);

    // Mock configuration
    TradfriConfig config = mock(TradfriConfig.class);
    when(config.getGatewayUrl()).thenReturn("http://mock-gateway");
    when(config.getApiKey()).thenReturn("mock-api-key");

    // Create an IkeaLight instance
    IkeaLight ikeaLight = new IkeaLight("Living Room Light", 123, config);

    // Simulate turning on the device
    ikeaLight.turnOn();

    // Verify that the device status is updated to ON
    DeviceStatus expected = DeviceStatus.ON;
    DeviceStatus actual = ikeaLight.getStatus();
    assertEquals(expected, actual, "Device status should be set to ON after calling turnOn");

    // Verify that the RestTemplate sent the correct request
    String expectedUrl = "http://mock-gateway/lights/123/state";
    verify(restTemplate).postForObject(
        eq(expectedUrl),
        argThat(request -> {
          if (request instanceof HttpEntity) {
            HttpEntity<?> entity = (HttpEntity<?>) request;
            String body = (String) entity.getBody();
            return body != null && body.contains("\"status\":\"on\"");
          }
          return false;
        }),
        eq(String.class));
  }

  @Test
  void turnOffShouldSetDeviceStatusToOff() {
    ikeaLight.turnOn();
    assertEquals(DeviceStatus.ON, ikeaLight.getStatus());
    ikeaLight.turnOff();
    assertEquals(DeviceStatus.OFF, ikeaLight.getStatus());
    // Verify behavior (e.g., logging, API call mock, etc.)
  }

  @Test
  void testLightId() {
    assertEquals(123, ikeaLight.getLightId());
  }
}
