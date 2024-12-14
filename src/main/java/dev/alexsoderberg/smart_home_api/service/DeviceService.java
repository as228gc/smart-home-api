package dev.alexsoderberg.smart_home_api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.alexsoderberg.smart_home_api.model.Device;
import dev.alexsoderberg.smart_home_api.repository.DeviceRepository;

@Service
public class DeviceService {
  private DeviceRepository repository;

  @Autowired
  public DeviceService(DeviceRepository deviceRepository) {
    this.repository = deviceRepository;
  }

  public List<Device> getAllDevices() {
    return repository.findAll();
  }

  public Device getDeviceById(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new NoSuchElementException("Resource not found."));
  }

  public void saveDevice(Device device) {
    repository.save(device);
  }

  public void removeDevice(Device device) {
    repository.delete(device);
  }
}
