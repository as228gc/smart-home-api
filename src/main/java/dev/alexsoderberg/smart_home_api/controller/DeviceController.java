package dev.alexsoderberg.smart_home_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.alexsoderberg.smart_home_api.model.Device;
import dev.alexsoderberg.smart_home_api.service.DeviceService;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

  private DeviceService service;

  public DeviceController(DeviceService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Device>> getAllDevices() {
    List<Device> devices = service.getAllDevices();
    return ResponseEntity.ok(devices);
  }
}
