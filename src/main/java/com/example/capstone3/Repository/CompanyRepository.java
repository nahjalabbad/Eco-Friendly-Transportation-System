package com.example.capstone3.Repository;

import com.example.capstone3.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findCompanyByCompanyId(Integer id);
}
