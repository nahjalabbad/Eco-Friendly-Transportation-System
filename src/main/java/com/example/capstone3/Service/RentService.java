package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Rent;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.RentRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    public List<Rent> getRent() {
        return rentRepository.findAll();
    }

    public void addRent( Integer userId ,Rent rent){
        User u = userRepository.findUserByUserId(userId);
        if(u==null){
            throw new ApiException("User id doesn't exist");
        }
        rent.setUser(u);
        rentRepository.save(rent);
    }

    public void updateRent(Integer rentId , Rent rent ){
        Rent r = rentRepository.findRentByRentId(rentId);
        if(r==null){
            throw new ApiException("Rent not found");
        }
        r.setDropOffLocation(rent.getDropOffLocation());
        r.setPickUpLocation(rent.getPickUpLocation());
        r.setTransportType(rent.getTransportType());
        r.setRentStatus(rent.getRentStatus());
        r.setReturnStatus(rent.getReturnStatus());
        r.setStartDate(rent.getStartDate());
        r.setEndDate(rent.getEndDate());
        r.setDuration(rent.getDuration());
        rentRepository.save(r);
    }
    public void deleteRent(Integer rentId){
        Rent r = rentRepository.findRentByRentId(rentId);
        if(r==null){
            throw new ApiException("Rent not found");
        }
        rentRepository.delete(r);
    }










}
