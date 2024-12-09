package dev.alexsoderberg.smart_home_api.model;

public class TestDevice extends Device {
  public TestDevice(String name, DeviceType type) {
      super(name, type);
  }

  @Override
  public void turnOn() {
      // Test-specific implementation (empty for now)
  }

  @Override
  public void turnOff() {
      // Test-specific implementation (empty for now)
  }
}

