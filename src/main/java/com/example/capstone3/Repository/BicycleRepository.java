package com.example.capstone3.Repository;

import com.example.capstone3.Model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle,Integer> {
    Bicycle findBicycleByBicycleId(Integer id);
}
