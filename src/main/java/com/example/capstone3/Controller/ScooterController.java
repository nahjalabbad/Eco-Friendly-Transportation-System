package com.example.capstone3.Controller;

import com.example.capstone3.Model.Scooter;
import com.example.capstone3.Service.ScooterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/Scooter")
@AllArgsConstructor
public class ScooterController {
    private final ScooterService scooterService;


    @GetMapping("/get")
    public ResponseEntity getAllScooters(){
        return ResponseEntity.status(200).body(scooterService.getAllScooters());
    }

    @PostMapping("/add/{companyId}")
    public ResponseEntity addScooters(@PathVariable Integer companyId, @RequestBody @Valid Scooter scooter){
        scooterService.addScooter(companyId,scooter);
        return ResponseEntity.status(200).body("Scooter added");
    }
    @PutMapping("/update/{scooterId}")
    public ResponseEntity updateScooters(@PathVariable Integer scooterId,@RequestBody @Valid Scooter scooter){
        scooterService.addScooter(scooterId,scooter);
        return ResponseEntity.status(200).body("Scooter updated!");
    }

    @DeleteMapping("/delete/{scooterId}")
    public ResponseEntity deleteScooter(@PathVariable Integer scooterId){
        scooterService.deleteScooter(scooterId);
        return ResponseEntity.status(200).body("Scooter deleted!");
    }

    @PutMapping("/assign/{companyId}/{scooterId}")
    public ResponseEntity assignScooterToStation(@PathVariable Integer companyId,@PathVariable Integer scooterId){
        scooterService.assignScooterToStation(companyId,scooterId);
        return ResponseEntity.status(200).body("ِِAssigend Successfully");
    }


}
