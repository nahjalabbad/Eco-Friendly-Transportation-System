package com.example.capstone3.Controller;

import com.example.capstone3.API.ApiResponse;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/get")
    public ResponseEntity getCompany(){
        return ResponseEntity.status(200).body(companyService.getCompany());
    }
    @PostMapping("/add")
    public ResponseEntity addCompany(@RequestBody @Valid Company company){
        companyService.addCompany(company);
        return ResponseEntity.status(200).body(new ApiResponse("Company added"));
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity updateCompany(@PathVariable Integer companyId , @RequestBody @Valid Company company ){
        companyService.updateCompany(companyId , company);
        return ResponseEntity.status(200).body(new ApiResponse("Company updated"));
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteCompany(@PathVariable Integer companyId ){
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(200).body(new ApiResponse("Company deleted"));
    }
}
