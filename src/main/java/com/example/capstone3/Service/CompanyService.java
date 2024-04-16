package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CompanyService {
    private  final CompanyRepository companyRepository;

    public List<Company> getCompany(){
        return companyRepository.findAll();
    }

    public void addCompany(Company company){
        companyRepository.save(company);
    }

    public void updateCompany(Integer companyId , Company company){
        Company c = companyRepository.findCompanyByCompanyId(companyId);
        if(c==null){
            throw new ApiException("Company not found");
        }
        c.setCompanyName(company.getCompanyName());
        c.setTransportType(company.getTransportType());
        c.setQuantity(company.getQuantity());
        companyRepository.save(c);
    }
    public void deleteCompany(Integer companyId){
        Company c = companyRepository.findCompanyByCompanyId(companyId);
        if(c==null){
            throw new ApiException("Company not found");
        }
        companyRepository.delete(c);
    }

    public void addTransportationType(Integer companyId , String transportationType){
        Company c = companyRepository.findCompanyByCompanyId(companyId);
        if(c==null){
            throw new ApiException("Company not found");
        }
        if (c.getTransportType().equalsIgnoreCase(transportationType)) {

            throw new ApiException("already registered this type");
        }
        c.setTransportType(transportationType);
        companyRepository.save(c);
    }
}
