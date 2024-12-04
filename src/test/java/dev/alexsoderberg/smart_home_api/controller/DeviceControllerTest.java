package dev.alexsoderberg.smart_home_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import dev.alexsoderberg.smart_home_api.model.Device;
import dev.alexsoderberg.smart_home_api.model.DeviceType;
import dev.alexsoderberg.smart_home_api.service.DeviceService;

public class DeviceControllerTest {

  private DeviceController sut;
  private DeviceService service;

  @BeforeEach
  void setUp() {
    List<Device> devices = List.of(
      new Device("Light bulb", DeviceType.LIGHT),
      new Device("Camera", DeviceType.CAMERA),
      new Device("TV", DeviceType.TV)
    );

    service = Mockito.mock(DeviceService.class);
    when(service.getAllDevices()).thenReturn(devices);

    sut = new DeviceController(service);
  }

  @Test
  void getAllDevicesShouldReturnAllDevices() {
    ResponseEntity<List<Device>> response = sut.getAllDevices();
    List<Device> retrievedDevices = response.getBody();

    assertEquals("Camera", retrievedDevices.get(1).getName());
    assertEquals(DeviceType.TV, retrievedDevices.get(2).getType());
    verify(service, times(1)).getAllDevices();
  }
}
