package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.RentalHistoryDTO;
import com.example.capstone3.Model.Rent;
import com.example.capstone3.Model.RentalHistory;
import com.example.capstone3.Model.User;
import com.example.capstone3.Repository.RentRepository;
import com.example.capstone3.Repository.RentalHistoryRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalHistoryService {
    private final RentalHistoryRepository rentalHistoryRepository;
    private final RentRepository rentRepository;

    public List<RentalHistory> getRentalHistory() {
        return rentalHistoryRepository.findAll();
    }

    public void addRentalHistory( Integer rentId ,RentalHistoryDTO rentalHistoryDTo) {
        Rent rent = rentRepository.findRentByRentId(rentId);

        if (rent == null) {
            throw new ApiException("rent not found or user id not found");
        }

        RentalHistory rentalHistory = new RentalHistory(
        null, rentalHistoryDTo.getTransportName(),rentalHistoryDTo.getPickUpStation(),rentalHistoryDTo.getDropOffStation(),rentalHistoryDTo.getStartTime()
        ,rentalHistoryDTo.getEndTime(),rentalHistoryDTo.getDuration(),rentalHistoryDTo.getStatus(),
        rentalHistoryDTo.getFuelLevel(),rentalHistoryDTo.getRating(),rentalHistoryDTo.getComment(), rent);
        rentalHistory.setRent(rent);
        rentalHistoryRepository.save(rentalHistory);
    }

    public void updateRentalHistory(Integer rentalId, RentalHistoryDTO rentalHistoryDTo) {
        RentalHistory rentalHistory1 = rentalHistoryRepository.findRentalHistoryByRentalId(rentalId);
        if (rentalHistory1 == null) {
            throw new ApiException("rentalId not found");
        }
        rentalHistory1.setPickUpStation(rentalHistoryDTo.getPickUpStation());
        rentalHistory1.setDropOffStation(rentalHistoryDTo.getDropOffStation());
        rentalHistory1.setStartTime(rentalHistoryDTo.getStartTime());
        rentalHistory1.setEndTime(rentalHistoryDTo.getEndTime());
        rentalHistory1.setDuration(rentalHistoryDTo.getDuration());
        rentalHistory1.setStatus(rentalHistoryDTo.getStatus());
        rentalHistory1.setFuelLevel(rentalHistoryDTo.getFuelLevel());

        rentalHistoryRepository.save(rentalHistory1);

    }

    public void deleteRentalHistory(Integer rentalId) {
        RentalHistory rentalHistory = rentalHistoryRepository.findRentalHistoryByRentalId(rentalId);
        if (rentalHistory == null) {
            throw new ApiException("rentalId not found");
        }
        rentalHistoryRepository.delete(rentalHistory);
    }



    //              EXTRA

    public List<RentalHistory> getAllHistory(Integer userId){
        List<RentalHistory> userRent=rentalHistoryRepository.findRentalHistoriesByUserId(userId);
        if (userRent.isEmpty()){
            throw new ApiException("This user does not have any rental history");
        }
        return userRent;
    }

    public List<RentalHistory> getHistoryByType(Integer userId, String transportType){
        List<RentalHistory> userRent=rentalHistoryRepository.findRentalHistoriesByUserIdaAndTransportType(userId,transportType);
        Rent rent=rentRepository.findRentByUserUserId(userId);
        if (userRent.isEmpty()){
            throw new ApiException("This user does not have any rental history");
        }
        if (!rent.getTransportType().equalsIgnoreCase(transportType)){
            throw new ApiException("this transport type is not available in our website");
        }

        return userRent;
    }

    public List<RentalHistory> getByStatus(String status){
        List<RentalHistory> userRent=rentalHistoryRepository.findRentalHistoriesByStatus(status);
        if (userRent.isEmpty()){
            throw new ApiException("This user does not have any rental history");
        }
        return userRent;
    }


}
