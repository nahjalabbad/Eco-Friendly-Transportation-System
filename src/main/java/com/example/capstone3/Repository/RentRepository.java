package com.example.capstone3.Repository;

import com.example.capstone3.Model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository  extends JpaRepository<Rent,Integer> {

    Rent findRentByRentId(Integer rentId);

    Rent findRentByUserUserId(Integer userId);

    Rent getRentByPrice(Integer price);

    Rent findRentByTransportName(String transportName);

    List<Rent>findRentByTransportType(String transportTyp );



}
