package dev.alexsoderberg.smart_home_api.controller;

import dev.alexsoderberg.smart_home_api.dto.IkeaLightRequest;
import dev.alexsoderberg.smart_home_api.model.Device;
import dev.alexsoderberg.smart_home_api.model.IkeaLight;
import dev.alexsoderberg.smart_home_api.service.DeviceService;
import dev.alexsoderberg.smart_home_api.service.TradfriGatewayService;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

  private final DeviceService deviceService;
  private final TradfriGatewayService gatewayService;

  @Autowired
  public DeviceController(DeviceService deviceService, TradfriGatewayService gatewayService) {
    this.deviceService = deviceService;
    this.gatewayService = gatewayService;
  }

  @PostMapping("/{id}/remove")
  public ResponseEntity<String> removeDevice(@PathVariable Long id) {
    try {
      Device device = deviceService.getDeviceById(id);
      deviceService.removeDevice(device);
      return ResponseEntity.ok("Device " + id + " successfully removed.");
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to remove device " + id + ".");
    }
  }

  @PostMapping("/{id}/turn-on")
  public ResponseEntity<String> turnOnDevice(@PathVariable Long id) {
    Device device = deviceService.getDeviceById(id); // Fetch device by database ID

    if (device instanceof IkeaLight ikeaLight) {
      boolean success = gatewayService.turnOnLight(ikeaLight);
      if (success) {
        return ResponseEntity.ok("IKEA Light turned ON successfully.");
      } else {
        return ResponseEntity.status(500).body("Failed to turn ON IKEA Light.");
      }
    }

    return ResponseEntity.badRequest().body("Device type is not supported for this operation.");
  }

  @PostMapping("/{id}/turn-off")
  public ResponseEntity<String> turnOffDevice(@PathVariable Long id) {
    Device device = deviceService.getDeviceById(id); // Fetch device by database ID

    if (device instanceof IkeaLight ikeaLight) {
      boolean success = gatewayService.turnOffLight(ikeaLight);
      if (success) {
        return ResponseEntity.ok("IKEA Light turned OFF successfully.");
      } else {
        return ResponseEntity.status(500).body("Failed to turn OFF IKEA Light.");
      }
    }

    return ResponseEntity.badRequest().body("Device type is not supported for this operation.");
  }

  @PostMapping("/ikea-light/add")
  public ResponseEntity<String> createIkeaLight(@RequestBody IkeaLightRequest request) {
    IkeaLight light = new IkeaLight(request.getName(), request.getLightId());
    deviceService.saveDevice(light); // Save the IkeaLight entity to the database
    return ResponseEntity.ok("IKEA Light created successfully with ID: " + light.getId());
  }
}
