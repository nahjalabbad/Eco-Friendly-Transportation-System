package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Company;
import com.example.capstone3.Model.Scooter;
import com.example.capstone3.Model.Station;
import com.example.capstone3.Repository.CompanyRepository;
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

    public List<Scooter>getAllScooters(){
return scooterRepository.findAll();
    }
    public void addScooter(Integer companyId,Scooter scooter){
        Company company= companyRepository.findCompanyByCompanyId(companyId);
        if(company==null){
            throw new ApiException("Company id not found!");
        }
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
}
