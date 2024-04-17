package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BicycleService {
    private  final BicycleRepository bicycleRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;
    private final RentRepository rentRepository;
    private final RentalHistoryRepository rentalHistoryRepository;



    public List<Bicycle> getAllBicycles(){
        return bicycleRepository.findAll();
    }

    public void addBicycle(Integer companyId,Bicycle bicycle){
        Company company= companyRepository.findCompanyByCompanyId(companyId);
        Rent rent= rentRepository.findRentByTransportName(bicycle.getBicycleName());
        if(company==null){
            throw new ApiException("Company id not found!");
        }
        if (rent==null){
            throw new ApiException("change bname");
        }
        bicycle.setRentStatus("not Rented");
        rent.setRentStatus("not Rented");
        rent.setTransportName(bicycle.getBicycleName());
        rentRepository.save(rent);
        bicycleRepository.save(bicycle);
    }

    public void updateBicycle(Integer bicycleId,Bicycle newBicycle){
        Bicycle bicycle1=bicycleRepository.findBicycleByBicycleId(bicycleId);
        if(bicycle1==null){
            throw new ApiException("Bicycle id not found!");
        }
        bicycle1.setFeatures(newBicycle.getFeatures());
        bicycle1.setLocation(newBicycle.getLocation());
        bicycle1.setModel(newBicycle.getModel());
        bicycle1.setPinNumber(newBicycle.getPinNumber());
        bicycle1.setNumOfWheels(newBicycle.getNumOfWheels());
        bicycleRepository.save(bicycle1);
    }

    public void deleteBicycle(Integer bicycleId){
        Bicycle bicycle=bicycleRepository.findBicycleByBicycleId(bicycleId);
        if(bicycle==null){
            throw new ApiException("Bicycle id not found!");
        }
        bicycleRepository.delete(bicycle);
    }

    public void assignBicycleToStation(Integer bicycleId,Integer stationId){
        Bicycle bicycle=bicycleRepository.findBicycleByBicycleId(bicycleId);
        Station station= stationRepository.findStationByStationId(stationId);
        if(bicycle==null||station==null){
            throw new ApiException("Can't Assigned");
        }
        bicycle.getStations().add(station);
        station.getBicycles().add(bicycle);
        bicycleRepository.save(bicycle);
        stationRepository.save(station);
    }


    public void setLock(Integer compnayId,Integer bicycleId,Integer pinNumber , String transName){
        Company company = companyRepository.findCompanyByCompanyId(compnayId);
        Bicycle bicycle=bicycleRepository.findBicycleByBicycleId(bicycleId);
        Rent rent = rentRepository.findRentByTransportName(transName);
        if (bicycle == null || company == null) {
            throw new ApiException("Can't setLock");
        } else if (bicycle.getPinNumber().equals(pinNumber)) {
            throw new ApiException("set Pin number correctly");
        }
        rent.setPinNumber(bicycle.getPinNumber());
    }

    public List<Bicycle>byTypeWeels(Integer numberOfWeels){
        List<Bicycle> bicycles=bicycleRepository.findBicycleByNumOfWheels(numberOfWeels);
        if ((bicycles.isEmpty())){
            throw new ApiException("not Avalabile");
        }
        return bicycles;
    }

    public List<Bicycle>byModel(Integer model){
        List<Bicycle> bicycles=bicycleRepository.findBicycleByModel(model);
        if ((bicycles.isEmpty())){
            throw new ApiException("not Avalabile");
        }
        return bicycles;
    }

    public List<Bicycle>viewAValibalAllBick(){
        List<Bicycle> bicycles=bicycleRepository.findBicycleByRentStatus("Not Rented");
        if(bicycles.isEmpty()){
            throw new ApiException("No Bick Avalabile");
        }
        return bicycles;
    }



    public String getSpecificDetails(String bicycleName){
        Bicycle bicycle=bicycleRepository.findBicycleByBicycleName(bicycleName);
        if(bicycle==null){
            throw new ApiException("Car name not found!");
        }
        return  " Bicycle Details: " + bicycle.getBicycleName() + " " + bicycle.getModel() +" "+ bicycle.getReturnStatus() + " " + bicycle.getFeatures() + "  " + bicycle.getLocation() + "  " + bicycle.getNumOfWheels() ;
    }


    public Double getAvgRating(String bickName) {
     RentalHistory r = rentalHistoryRepository.findRentalHistoriesByTransportName(bickName);
    List <RentalHistory> rentalHistory=rentalHistoryRepository.findAll();
        double sum = 0;
        int count = 0;
        boolean found = false;
            for (RentalHistory rh:rentalHistory){
                if (r.getTransportName().equalsIgnoreCase("bickName")) {
                    sum += r.getRating();
                    count++;
                    found = true;
                }
            }
        if (!found || count == 0) {
            throw new ApiException("no bicycle with this name found");
        }
        return sum / count;
    }


}
