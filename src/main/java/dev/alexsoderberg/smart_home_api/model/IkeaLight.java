package dev.alexsoderberg.smart_home_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ikea_lights")
public class IkeaLight extends Device {

  @Column(name = "gateway_light_id", nullable = false, unique = true)
  private int lightId;

  public IkeaLight(String name, int lightId) {
    super(name, DeviceType.LIGHT);
    this.lightId = lightId;
  }

  public IkeaLight() {
    this.lightId = 0;
  }

  public int getLightId() {
    return lightId;
  }

  @Override
  public void turnOn() {

  }

  @Override
  public void turnOff() {
  }
}
