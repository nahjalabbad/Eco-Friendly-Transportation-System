package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Bicycle;
import com.example.capstone3.Model.Cars;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Station;
import com.example.capstone3.Repository.BicycleRepository;
import com.example.capstone3.Repository.CompanyRepository;
import com.example.capstone3.Repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BicycleService {
    private  final BicycleRepository bicycleRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;

    public List<Bicycle> getAllBicycles(){
        return bicycleRepository.findAll();
    }

    public void addBicycle(Integer companyId,Bicycle bicycle){
        Company company= companyRepository.findCompanyByCompanyId(companyId);
        if(company==null){
            throw new ApiException("Company id not found!");
        }
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

}
