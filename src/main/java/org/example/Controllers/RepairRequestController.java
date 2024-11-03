package org.example.Controllers;

import org.example.Entities.Driver;
import org.example.Entities.RepairRequest;
import org.example.Entities.Vehicle;
import org.example.Services.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repair-requests")
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    @Autowired
    public RepairRequestController(RepairRequestService repairRequestService) {
        this.repairRequestService = repairRequestService;
    }

    @GetMapping
    public List<RepairRequest> getAllRepairRequests() {
        return repairRequestService.getAllRepairRequests();
    }

    @PostMapping
    public RepairRequest createRepairRequest(@RequestBody RepairRequest repairRequest) {
        return repairRequestService.createRepairRequest(repairRequest);
    }

    @GetMapping("/{id}")
    public RepairRequest getRepairRequestById(@PathVariable Long id) {
        return repairRequestService.getRepairRequestById(id);
    }

    @PutMapping("/{id}")
    public RepairRequest updateRepairRequest(@PathVariable Long id, @RequestBody RepairRequest updatedRequest) {
        return repairRequestService.updateRepairRequest(id, updatedRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRepairRequest(@PathVariable Long id) {
        repairRequestService.deleteRepairRequest(id);
    }

    @GetMapping("/vehicle/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return repairRequestService.getVehicleById(id);
    }

    @GetMapping("/driver/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return repairRequestService.getDriverById(id);
    }
}
