package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.DTO.RentalHistoryDTO;
import com.example.capstone3.Service.RentalHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rentalhistory")
@RequiredArgsConstructor
public class RentalHistoryController {
    private final RentalHistoryService rentalHistoryService;

    @GetMapping("/get")
    public ResponseEntity getRentalHistory(){
        return ResponseEntity.status(200).body(rentalHistoryService.getRentalHistory());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity addRentalHistory(@PathVariable Integer userId, @RequestBody @Valid RentalHistoryDTO rentalHistoryDTO ){
        rentalHistoryService.addRentalHistory(userId,rentalHistoryDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Rental history Added and Assigned "));
    }

    @PutMapping("/update/{rentalId}")
    public ResponseEntity updateRentalHistory( @PathVariable Integer rentalId, @RequestBody @Valid RentalHistoryDTO rentalHistoryDTO ){
        rentalHistoryService.updateRentalHistory(rentalId,rentalHistoryDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Rental history Updated"));
    }

    @DeleteMapping("/delete/{rentalId}")
    public ResponseEntity deleteRentalHistory(@PathVariable Integer rentalId ){
        rentalHistoryService.deleteRentalHistory(rentalId);
        return ResponseEntity.status(200).body(new ApiResponse("Rental history Deleted"));
    }
}
