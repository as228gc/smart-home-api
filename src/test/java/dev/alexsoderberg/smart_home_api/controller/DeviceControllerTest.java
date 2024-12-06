package dev.alexsoderberg.smart_home_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    Device mockLight = Mockito.mock(Device.class);
    when(mockLight.getName()).thenReturn("Light bulb");
    when(mockLight.getType()).thenReturn(DeviceType.LIGHT);

    Device mockCamera = Mockito.mock(Device.class);
    when(mockCamera.getName()).thenReturn("Camera");
    when(mockCamera.getType()).thenReturn(DeviceType.CAMERA);

    service = Mockito.mock(DeviceService.class);
    when(service.getAllDevices()).thenReturn(List.of(mockLight, mockCamera));

    sut = new DeviceController(service);
  }

  @Test
  void getAllDevicesShouldReturnAllDevices() {
    ResponseEntity<List<Device>> response = sut.getAllDevices();
    List<Device> retrievedDevices = response.getBody();

    assertNotNull(retrievedDevices);
    assertEquals("Camera", retrievedDevices.get(1).getName());
    verify(service, times(1)).getAllDevices();
  }

}
