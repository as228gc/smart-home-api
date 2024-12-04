package dev.alexsoderberg.smart_home_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.alexsoderberg.smart_home_api.model.Device;
import dev.alexsoderberg.smart_home_api.repository.DeviceRepository;

@Service
public class DeviceService {
  private DeviceRepository repository;

  public DeviceService(DeviceRepository deviceRepository) {
    this.repository = deviceRepository;
  }

  public List<Device> getAllDevices() {
    return null;
  }
}
