package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Cars;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Station;
import com.example.capstone3.Repository.CarsRepository;
import com.example.capstone3.Repository.CompanyRepository;
import com.example.capstone3.Repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsService {
private final CarsRepository carsRepositry;
private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;

public List<Cars>getAllCars(){
    return carsRepositry.findAll();
}

public void addCars(Integer companyId,Cars car){
    Company company= companyRepository.findCompanyByCompanyId(companyId);
    if(company==null||!company.getTransportType().equalsIgnoreCase("Cars")){
        throw new ApiException("Company id not found!");
    }
    car.setCompany(company);
    carsRepositry.save(car);
}

public  void updateCars(Integer carId,Cars NewCar){
    Cars car= carsRepositry.findCarsByCarId(carId);
    if(car==null){
        throw new ApiException("Car id not found!");
    }
    car.setCarModel(NewCar.getCarModel());
    car.setLocation(NewCar.getLocation());
    car.setFuelPercentage(NewCar.getFuelPercentage());
    car.setNumSeats(NewCar.getNumSeats());
    car.setPinNumber(NewCar.getPinNumber());
    car.setCarType(NewCar.getCarType());
    carsRepositry.save(car);
}


public void deleteCar(Integer carId){
    Cars car=carsRepositry.findCarsByCarId(carId);
    if(car==null){
        throw new ApiException("Car id not found!");
    }
    carsRepositry.delete(car);
}


public void assignCarsToStation(Integer carId,Integer stationId){
    Cars car= carsRepositry.findCarsByCarId(carId);
    Station station= stationRepository.findStationByStationId(stationId);
    if(car==null||station==null){
        throw new ApiException("Can't Assigned");
    }
    car.getStations().add(station);
    station.getCars().add(car);
    carsRepositry.save(car);
    stationRepository.save(station);
}

}
