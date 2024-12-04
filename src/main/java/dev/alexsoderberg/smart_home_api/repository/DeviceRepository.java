package dev.alexsoderberg.smart_home_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alexsoderberg.smart_home_api.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
