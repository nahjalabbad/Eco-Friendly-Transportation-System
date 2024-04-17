package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.CompanyRepository;
import com.example.capstone3.Repository.RentRepository;
import com.example.capstone3.Repository.ScooterRepository;
import com.example.capstone3.Repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepository;
    private final CompanyRepository companyRepository;
    private final StationRepository stationRepository;
    private final RentRepository rentRepository;

    public List<Scooter>getAllScooters(){
return scooterRepository.findAll();
    }
    public void addScooter(Integer companyId, Scooter scooter) {
        Company company = companyRepository.findCompanyByCompanyId(companyId);
        if (company == null) {
            throw new ApiException("Company id not found!");
        }
        if (!company.getTransportType().equalsIgnoreCase("Scooter")){
            throw new ApiException("Company transport type is not set to bicycle");
        }
        scooter.setRentStatus("Not Rented");
        scooter.setCompany(company);

        scooterRepository.save(scooter);
    }

    public void updateScooter(Integer scooterId,Scooter newScooter){
        Scooter scooter=scooterRepository.findScooterByScooterId(scooterId);
        if(scooter==null){
            throw new ApiException("Scooter id not found!");
        }
        scooter.setLocation(newScooter.getLocation());
        scooter.setPinNumber(newScooter.getPinNumber());
        scooter.setFeatures(newScooter.getFeatures());
        scooter.setChargePercentage(newScooter.getChargePercentage());
        scooter.setMaxSpeed(newScooter.getMaxSpeed());
        scooter.setModel(newScooter.getModel());
        scooterRepository.save(scooter);
    }

    public void deleteScooter(Integer scooterId){
        Scooter scooter=scooterRepository.findScooterByScooterId(scooterId);
        if(scooter==null){
            throw new ApiException("Scooter id not found!");
        }
        scooterRepository.delete(scooter);
    }

    public void assignScooterToStation(Integer scooterId,Integer stationId){
        Scooter scooter=scooterRepository.findScooterByScooterId(scooterId);
        Station station= stationRepository.findStationByStationId(stationId);
        if(scooterId==null||station==null){
            throw new ApiException("Can't Assigned");
        }
        scooter.getStations().add(station);
       station.getScooters().add(scooter);
        scooterRepository.save(scooter);
        stationRepository.save(station);
    }

    public String setLock(Integer compnayId,Integer scooterId,Integer pinNumber ,String transName){
        Company company = companyRepository.findCompanyByCompanyId(compnayId);
        Scooter scooter=scooterRepository.findScooterByScooterId(scooterId);
        Rent rent = rentRepository.findRentByTransportName(transName);
        if (scooterId == null || company == null) {
            throw new ApiException("Can't setLock");
        } else if (scooter.getPinNumber().equals(pinNumber)) {
            throw new ApiException("set Pin number correctly");
        }

        rent.setPinNumber(scooter.getPinNumber());
        return "Lock set Successfully";
    }


    public List<Scooter>byModel(Integer model){
        List<Scooter> scooters=scooterRepository.findScooterByModel(model);
        if ((scooters.isEmpty())){
            throw new ApiException("not Avalabile");
        }
        return scooters;
    }


    public List<Scooter>viewAvalibleScooter(){
            List<Scooter> scooters=scooterRepository.findScooterByRentStatus("Not Rented");
        if(scooters.isEmpty()){
            throw new ApiException("No Soocter Avalabile");
        }
        return scooters;
    }
}
