package org.example.Services;

import org.example.Entities.RepairRequest;
import org.example.Entities.Vehicle;
import org.example.Entities.Driver;
import org.example.Repositories.VehicleRepository;
import org.example.Repositories.DriverRepository;
import org.example.Repositories.RepairRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public RepairRequestService(RepairRequestRepository repairRequestRepository,
                                VehicleRepository vehicleRepository,
                                DriverRepository driverRepository) {
        this.repairRequestRepository = repairRequestRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    public List<RepairRequest> getAllRepairRequests() {
        return repairRequestRepository.findAll();
    }

    public RepairRequest createRepairRequest(RepairRequest repairRequest) {
        return repairRequestRepository.save(repairRequest);
    }

    public RepairRequest getRepairRequestById(Long id) {
        return repairRequestRepository.findById(id).orElse(null);
    }

    public RepairRequest updateRepairRequest(Long id, RepairRequest updatedRequest) {
        return repairRequestRepository.findById(id)
                .map(existingRequest -> {
                    existingRequest.setVehicle(updatedRequest.getVehicle());
                    existingRequest.setDriver(updatedRequest.getDriver());
                    existingRequest.setDescription(updatedRequest.getDescription());
                    existingRequest.setRequestDate(updatedRequest.getRequestDate());
                    return repairRequestRepository.save(existingRequest);
                })
                .orElse(null);
    }

    public void deleteRepairRequest(Long id) {
        repairRequestRepository.deleteById(id);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }
}
