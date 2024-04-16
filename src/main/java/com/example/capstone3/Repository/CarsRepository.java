package com.example.capstone3.Repository;

import com.example.capstone3.Model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Cars,Integer> {
    Cars findCarsByCarId(Integer carId);
}
