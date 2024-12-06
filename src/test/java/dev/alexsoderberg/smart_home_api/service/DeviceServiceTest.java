package dev.alexsoderberg.smart_home_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.alexsoderberg.smart_home_api.model.Device;
import dev.alexsoderberg.smart_home_api.model.DeviceType;
import dev.alexsoderberg.smart_home_api.model.TestDevice;
import dev.alexsoderberg.smart_home_api.repository.DeviceRepository;

public class DeviceServiceTest {

  private DeviceService sut;
  private DeviceRepository repository;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(DeviceRepository.class);

    Device light = new TestDevice("Light bulb", DeviceType.LIGHT);
    Device camera = new TestDevice("Camera", DeviceType.CAMERA);

    when(repository.findAll()).thenReturn(List.of(light, camera));

    sut = new DeviceService(repository);
  }

  @Test
  void getAllDevicesShouldReturnAllDevices() {
    List<Device> actual = sut.getAllDevices();

    assertEquals(2, actual.size());
    assertEquals("Light bulb", actual.get(0).getName());
    verify(repository, times(1)).findAll();
  }

}
