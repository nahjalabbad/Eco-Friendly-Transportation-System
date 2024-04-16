package com.example.capstone3.Controller;

import com.example.capstone3.Model.Bicycle;
import com.example.capstone3.Service.BicycleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bicycle")
@AllArgsConstructor
public class BicycleController {

    private final BicycleService bicycleService;

    @GetMapping("/get")
    public ResponseEntity getAllBicycle(){
        return ResponseEntity.status(200).body(bicycleService.getAllBicycles());
    }

    @PostMapping("/add/{companyId}")

    public ResponseEntity addBicycle(@PathVariable Integer companyId, @RequestBody @Valid Bicycle bicycle){
        bicycleService.addBicycle(companyId,bicycle);
        return ResponseEntity.status(200).body("Bicycle added");
    }

    @PutMapping("/update/{bicycleId}")
    public ResponseEntity updateBicycle(@PathVariable Integer bicycleId, @RequestBody @Valid Bicycle bicycle){
        bicycleService.updateBicycle(bicycleId,bicycle);
        return ResponseEntity.status(200).body("Bicycle updated!");
    }

    @PutMapping("/delete/{bicycleId}")
    public ResponseEntity deleteBicycle(@PathVariable Integer bicycleId){
        bicycleService.deleteBicycle(bicycleId);
        return ResponseEntity.status(200).body("Bicycle deleted!");
    }

    @PutMapping("/assign/{companyId}/{bicycleId}")
    public ResponseEntity assignCarsToStation(@PathVariable Integer companyId,@PathVariable Integer bicycleId){
        bicycleService.assignBicycleToStation(companyId,bicycleId);
        return ResponseEntity.status(200).body("ِِAssigend Successfully");
    }




}
