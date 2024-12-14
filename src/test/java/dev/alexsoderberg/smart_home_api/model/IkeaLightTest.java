package dev.alexsoderberg.smart_home_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.alexsoderberg.smart_home_api.config.TradfriConfig;

public class IkeaLightTest {

  private IkeaLight ikeaLight;
  private TradfriConfig config;

  @BeforeEach
  void setUp() {
    config = mock(TradfriConfig.class);
    when(config.getGatewayUrl()).thenReturn("http://mock-gateway");
    when(config.getApiKey()).thenReturn("mock-api-key");

    ikeaLight = new IkeaLight("Living Room Light", 123);
  }

  @Test
  void testLightId() {
    assertEquals(123, ikeaLight.getLightId());
  }
}
