package dev.alexsoderberg.smart_home_api.model;

public class TestDevice extends Device {
  public TestDevice(String name, DeviceType type) {
      super(name, type);
  }

  @Override
  protected void handleTurnOn() {
      // Test-specific implementation (empty for now)
  }

  @Override
  protected void handleTurnOff() {
      // Test-specific implementation (empty for now)
  }
}

