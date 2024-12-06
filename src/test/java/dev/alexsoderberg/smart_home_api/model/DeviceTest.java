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
    sut = new TestDevice(
      "Light bulb",
      DeviceType.LIGHT 
    );
  }

  @Test
  void deviceShouldBeCreatedWhenEnteredWithValidValues() {
    Device device = new TestDevice(
      "Light bulb",
      DeviceType.LIGHT
    );

    assertNotNull(device);
  }

  @Test
  void constructorShouldThrowExceptionWhenEnteredWithEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TestDevice("", DeviceType.LIGHT);
    });
  }

  @Test
  void constructorShouldThrowExceptionWhenEnteredWithBlankName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TestDevice("   ", DeviceType.CAMERA);
    });
  }

  @Test
  void getNameShouldReturnName() {
    String expected = "Light bulb";
    String actual = sut.getName();
    assertEquals(expected, actual);
  }

  @Test
  void setNameShouldSetNewName() {
    String expected = "Not a light bulb";
    sut.setName(expected);
    String actual = sut.getName();
    assertEquals(expected, actual);
  }
  
  @Test
  void setNameShouldThrowErrorWhenEnteredWithEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> {
      sut.setName("");
    });
  }

  @Test
  void setNameShouldThrowErrorWhenEnteredWithBlankName() {
    assertThrows(IllegalArgumentException.class, () -> {
      sut.setName("   ");
    });
  }

  @Test
  void getTypeShouldReturnType() {
    DeviceType expected = DeviceType.LIGHT;
    DeviceType actual = sut.getType();
    assertEquals(expected, actual);
  }

  @Test
  void setTypeShouldSetNewType() {
    DeviceType expected = DeviceType.CAMERA;
    sut.setType(expected);
    DeviceType actual = sut.getType();
    assertEquals(expected, actual);
  }

  @Test
  void getStatusShouldReturnCurrentStatus() {
    DeviceStatus expected = DeviceStatus.OFF;
    DeviceStatus actual = sut.getStatus();
    assertEquals(expected, actual);
  }
  
  @Test
  void turnOnShouldSetStatusToOn() {
    DeviceStatus current = sut.getStatus();
    assertEquals(DeviceStatus.OFF, current);
    sut.turnOn();
    DeviceStatus expected = DeviceStatus.ON;
    DeviceStatus actual = sut.getStatus();
    assertEquals(expected, actual);
  }

  @Test
  void turnOffShouldSetStatusToOff() {
    sut.turnOn();
    assertEquals(DeviceStatus.ON, sut.getStatus());

    sut.turnOff();
    DeviceStatus expected = DeviceStatus.OFF;
    DeviceStatus actual = sut.getStatus();
    assertEquals(expected, actual);
  }
}
