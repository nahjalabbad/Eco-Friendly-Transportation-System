package com.example.capstone3.Repository;

import com.example.capstone3.Model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter,Integer> {
    Scooter findScooterByScooterId(Integer id);
}
