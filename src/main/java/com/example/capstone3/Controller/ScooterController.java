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
        scooterService.updateScooter(scooterId,scooter);
        return ResponseEntity.status(200).body("Scooter updated!");
    }

    @DeleteMapping("/delete/{scooterId}")
    public ResponseEntity deleteScooter(@PathVariable Integer scooterId){
        scooterService.deleteScooter(scooterId);
        return ResponseEntity.status(200).body("Scooter deleted!");
    }
    //              EXTRA

    @PutMapping("/assign/{stationId}/{scooterId}")
    public ResponseEntity assignScooterToStation(@PathVariable Integer stationId,@PathVariable Integer scooterId){
        scooterService.assignScooterToStation(stationId,scooterId);
        return ResponseEntity.status(200).body("ِِAssigend Successfully");
    }


    @GetMapping("/by_model/{model}")
    public ResponseEntity getAllByModel(@PathVariable Integer model){
        return ResponseEntity.status(200).body(scooterService.byModel(model));
    }

    @PutMapping("/set_lock/{compnayId}/{scooterId}/{pinNumber}/{transName}")
    public ResponseEntity setLock(Integer compnayId,Integer scooterId,Integer pinNumber , String transName){
        scooterService.setLock(compnayId,scooterId,pinNumber,transName);
        return ResponseEntity.status(200).body("Lock set Successfully");
    }

    //viewAllScooterAvalibale

    @GetMapping("/viewall/")
    public ResponseEntity getAllByModel(){
        return ResponseEntity.status(200).body(scooterService.viewAvalibleScooter());
    }

}
