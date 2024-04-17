package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsService {
private final CarsRepository carsRepositry;
private final CompanyRepository companyRepository;
private final StationRepository stationRepository;
    private final RentRepository rentRepository;

public List<Cars>getAllCars(){
    return carsRepositry.findAll();
}

    public void addCars(Integer companyId, Cars car) {
        Company company = companyRepository.findCompanyByCompanyId(companyId);
        if (company == null) {
            throw new ApiException("Company id not found!");
        }
        if (!company.getTransportType().equalsIgnoreCase("Car")) {
            throw new ApiException("Company transport type does not match");
        }

        // Ensure that the required properties of the Car object are set
        if (car.getCarName() == null || car.getFuelPercentage() == null) {
            throw new ApiException("Car name and fuel percentage must be provided");
        }

        // Create a new Rent object
        Rent rent = new Rent();
        rent.setRentStatus("Not Rented");
        rent.setTransportName(car.getCarName());
        rent.setFuelPercentage(car.getFuelPercentage());
        rent.setQuantity(1); // Assuming quantity is 1 for each car
        // Set the transportType property of Rent if necessary
        rent.setTransportType("Car");

        // Set the company for the car
        car.setCompany(company);

        // Save the Rent object
        rentRepository.save(rent);

        // Save the Car object
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

public List<Cars> viewCarsByType(String carType){
    List<Cars> c =carsRepositry.findCarsByCarType(carType);
    if(c==null){
        throw new ApiException("Car id not found!");
    }
    return c;
}

public List<Cars> viewAllCars(){
        List<Cars> c =carsRepositry.findCarsByRentStatus("not rented");
        if(c==null){
            throw new ApiException("Car id not found!");
        }
        return c;
    }

public String getSpecificDetails(String carName){
    Cars car=carsRepositry.findCarsByCarName(carName);
    if(car==null){
        throw new ApiException("Car name not found!");
    }
    return  " Car Details: " + car.getCarName() + " - "
            + car.getCarType() + " - "
            + car.getCarModel() + " - "
            + car.getNumSeats() + " - "
            + car.getCompany() + " - "
            + car.getFuelPercentage() + " - "
            + car.getLocation();
}

    public String setLock(Integer compnayId,Integer carId,Integer pinNumber , String transName){
        Company company = companyRepository.findCompanyByCompanyId(compnayId);
        Cars car= carsRepositry.findCarsByCarId(carId);
        Rent rent = rentRepository.findRentByTransportName(transName);
        if (car == null || company == null) {
            throw new ApiException("Can't setLock");
        } else if (car.getPinNumber().equals(pinNumber)) {
            throw new ApiException("set Pin number correctly");
        }
        rent.setPinNumber(car.getPinNumber());

        return "Lock set Successfully";
    }


//public Double getAverageRating( String carName ) {
//    Cars car=carsRepositry.findCarsByCarName(carName);
//    if(car==null){
//        throw new ApiException("Car name not found!");
//    }
//    double sum = 0;
//    int count = 0;
//    boolean found = false;
//
//
//
//
//}
//
//    public Double getAvgRating(String pName) {
//        double sum = 0;
//        int count = 0;
//        boolean found = false;
//        for (ProductReview p : reviews) {
//            for (Product product : productService.products) {
//                if (product.getName().equalsIgnoreCase(pName)) {
//                    sum += p.getRating();
//                    count++;
//                    found = true;
//                }
//            }
//        }
//        if (!found || count == 0) {
//            return null;
//        }
//        return sum / count;
//    }
}
