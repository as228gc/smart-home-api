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
      "Light bulb",
      DeviceType.LIGHT 
    );
  }

  @Test
  void deviceShouldBeCreatedWhenEnteredWithValidValues() {
    Device device = new Device(
      "Light bulb",
      DeviceType.LIGHT
    );

    assertNotNull(device);
  }

  @Test
  void constructorShouldThrowExceptionWhenEnteredWithEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Device("", DeviceType.LIGHT);
    });
  }

  @Test
  void constructorShouldThrowExceptionWhenEnteredWithBlankName() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Device("   ", DeviceType.CAMERA);
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
    String actual = sut.setName(expected);
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
}
