package org.example.Services;

import org.example.Entities.*;
import org.example.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public Driver updateDriver(Driver driver) {
        if (driverRepository.existsById(driver.getId())) {
            return driverRepository.save(driver);
        } else {
            throw new RuntimeException("Driver with ID " + driver.getId() + " not found");
        }
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
