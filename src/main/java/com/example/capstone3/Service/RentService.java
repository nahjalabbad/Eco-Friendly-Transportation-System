package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.DTO.RentalHistoryDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;
    private final RentalHistoryRepository rentalHistoryRepository;


    public List<Rent> getRent() {
        return rentRepository.findAll();
    }

    public String addRent(Integer userId, String companyName, String transName, Rent rent) {
        User user = userRepository.findUserByUserId(userId);
        rent = rentRepository.findRentByTransportName(transName);
        Company company = companyRepository.findCompanyByCompanyName(companyName);

        Station station = stationRepository.getStationByStationName(rent.getPickUpLocation());

        if (user == null || company == null) {
            throw new ApiException("Can't Rent");
        }
        if (!rent.getTransportName().equalsIgnoreCase(transName)) {
            throw new ApiException("Transport name is not found in our system");
        }
        company.setQuantity(company.getQuantity() - rent.getQuantity());

        rent.setRentStatus("Rented");

        user.setRide(user.getRide() + 1);

        station.setCapacity(station.getCapacity() - rent.getQuantity());

        RentalHistoryDTO rentalHistoryDTO=new RentalHistoryDTO(null,rent.getTransportName(),rent.getPickUpLocation(),rent.getDropOffLocation()
                ,rent.getStartDate(),rent.getEndDate(),rent.getDuration(),null,rent.getFuelPercentage(),null,null);
        companyRepository.save(company);
        rentRepository.save(rent);
        userRepository.save(user);
        stationRepository.save(station);

        return "thank you for renting " + rent.getPinNumber();
    }


    public void updateRent(Integer rentId, Rent rent) {
        Rent r = rentRepository.findRentByRentId(rentId);
        if (r == null) {
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

    public void deleteRent(Integer rentId) {
        Rent r = rentRepository.findRentByRentId(rentId);
        if (r == null) {
            throw new ApiException("Rent not found");
        }
        rentRepository.delete(r);
    }


    public void returnRent(Integer userId, String companyName, String transName) {
        User user = userRepository.findUserByUserId(userId);
        Rent rent = rentRepository.findRentByTransportName(transName);
        Company company = companyRepository.findCompanyByCompanyName(companyName);
        Station station = stationRepository.getStationByStationName(rent.getDropOffLocation());

        if (user == null || company == null) {
            throw new ApiException("Can't Rent");
        }
        if (!rent.getTransportName().equalsIgnoreCase(transName)) {
            throw new ApiException("Transport name is not found in our system");
        }
        company.setQuantity(company.getQuantity() + rent.getQuantity());

        rent.setRentStatus("Not Rented");
        rent.setFuelPercentage(rent.getFuelPercentage()-20);

        station.setCapacity(station.getCapacity() + rent.getQuantity());

        companyRepository.save(company);
        rentRepository.save(rent);

    }

    public List<Rent>byAvalablity(){
        List<Rent> avalabiles = rentRepository.findRentByTransportType("available");
        if (avalabiles.isEmpty()){
            throw new ApiException("not available");
        }
        return avalabiles;
    }
}









