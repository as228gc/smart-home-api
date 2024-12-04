package dev.alexsoderberg.smart_home_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeviceTest {
  private Device sut;

  @BeforeEach
  void setUp() {
    sut = new Device(
      1L,
      "Light bulb",
      DeviceType.LIGHT 
    );
  }

  @Test
  void deviceShouldBeCreatedWhenEnteredWithValidValues() {
    Device device = new Device(
      1L,
      "Light bulb",
      DeviceType.LIGHT
    );

    assertNotNull(device);
    assertEquals(device.getId(), 1L);
    assertEquals(device.getName(), "Light bulb");
    assertEquals(device.getType(), DeviceType.LIGHT);
    assertEquals(device.getStatus(), DeviceStatus.OFF);
  }

  @Test
  void constructorShouldThrowExceptionWhenEnteredWithEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Device(1L, "", DeviceType.LIGHT);
    });
  }

  @Test
  void constructorShouldThrowExceptionWhenEnteredWithBlankName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Device(1L, "   ", DeviceType.CAMERA);
    });
  }
}
