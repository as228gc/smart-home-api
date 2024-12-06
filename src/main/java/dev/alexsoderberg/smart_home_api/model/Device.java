package dev.alexsoderberg.smart_home_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "devices")
public abstract class Device implements ControllableDevice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DeviceType type;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private DeviceStatus status;

  public Device(String name, DeviceType type) throws IllegalArgumentException {
    validateName(name);
    validateType(type);

    this.name = name;
    this.type = type;
    this.status = DeviceStatus.OFF;
  }

  private void validateName(String name) throws IllegalArgumentException {
    if (name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("The name can not be empty or blank.");
    }
  }

  public Long getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }

  public void setName(String newName) throws IllegalArgumentException {
    validateName(newName);
    this.name = newName;
  }

	public DeviceType getType() {
		return this.type;
	}

  public void setType(DeviceType newType) {
    validateType(newType);
    this.type = newType;
  }

  private void validateType(DeviceType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException();
    }
  }

  public DeviceStatus getStatus() {
    return this.status;
  }

  public void turnOn() {
    handleTurnOn();
    this.status = DeviceStatus.ON;
  }

  public void turnOff() {
    handleTurnOff();
    this.status = DeviceStatus.OFF;
  }

  protected abstract void handleTurnOn();
  protected abstract void handleTurnOff();
}
