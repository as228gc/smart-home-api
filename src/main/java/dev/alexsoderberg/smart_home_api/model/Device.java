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
public class Device {
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
  private DeviceStatus status = DeviceStatus.OFF;

  public Device(String name, DeviceType type) throws IllegalArgumentException {
    validateName(name);

    this.name = name;
    this.type = type;
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

  public String setName(String newName) throws IllegalArgumentException {
    validateName(newName);

    this.name = newName;
    return this.name;
  }

	public DeviceType getType() {
		return this.type;
	}
}
