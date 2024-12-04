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
import dev.alexsoderberg.smart_home_api.repository.DeviceRepository;

public class DeviceServiceTest {

  private DeviceService sut;
  private DeviceRepository repository;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(DeviceRepository.class);
    List<Device> devices = List.of(
        new Device("Light bulb", DeviceType.LIGHT),
        new Device("Camera", DeviceType.CAMERA),
        new Device("TV", DeviceType.TV));

    when(repository.findAll()).thenReturn(devices);

    sut = new DeviceService(repository);
  }

  @Test
  void getAllDevicesShouldReturnAllDevices() {
    List<Device> actual = sut.getAllDevices();

    assertEquals(3, actual.size(), "The number of devices should match");
    assertEquals("Light bulb", actual.get(0).getName());
    assertEquals(DeviceType.CAMERA, actual.get(1).getType());
    assertEquals("TV", actual.get(2).getName());

    verify(repository, times(1)).findAll();
  }
}
