package com.example.capstone3.Controller;
import com.example.capstone3.Model.Cars;
import com.example.capstone3.Service.CarsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@AllArgsConstructor
public class CarController {
    private final CarsService carsService;


    @GetMapping("/get")
    public ResponseEntity getAllCars(){
        return ResponseEntity.status(200).body(carsService.getAllCars());
    }

    @PostMapping("/add/{companyId}")
    public ResponseEntity addCars(@PathVariable Integer companyId,@RequestBody @Valid Cars car){
        carsService.addCars(companyId,car);
        return ResponseEntity.status(200).body("Car added");
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity updateCars(@PathVariable Integer carId,@RequestBody @Valid Cars car){
        carsService.updateCars(carId,car);
        return ResponseEntity.status(200).body("Car updated!");
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity deleteCars(@PathVariable Integer carId){
        carsService.deleteCar(carId);
        return ResponseEntity.status(200).body("Car deleted!");
    }

    @PutMapping("/assign/{companyId}/{carId}")
    public ResponseEntity assignCarsToStation(@PathVariable Integer companyId,@PathVariable Integer carId){
        carsService.assignCarsToStation(companyId,carId);
        return ResponseEntity.status(200).body("ِِAssigend Successfully");
    }

    //              EXTRA

    @PutMapping("/setlock/{compnayId}/{carId}/{pinNumber}/{transName}")
    public ResponseEntity setLock(Integer compnayId,Integer carId,Integer pinNumber , String transName){
        carsService.setLock(compnayId,carId,pinNumber,transName);
        return ResponseEntity.status(200).body("Lock set Successfully");
    }

    @GetMapping("/details/{nameCar}")
    public ResponseEntity getSpecificDetails(String nameCar){
        return ResponseEntity.status(200).body(carsService.getSpecificDetails(nameCar));
    }


}
