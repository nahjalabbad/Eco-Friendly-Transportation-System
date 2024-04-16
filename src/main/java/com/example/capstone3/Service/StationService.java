package com.example.capstone3.Service;

import com.example.capstone3.API.ApiException;
import com.example.capstone3.Model.Station;
import com.example.capstone3.Repository.StationRepository;
import com.example.capstone3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;
    private final UserRepository userRepository;

    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    public void addStation(Station station) {
        stationRepository.save(station);
    }

    public void updateStation(Integer stationId, Station station) {
        Station station1 = stationRepository.findStationByStationId(stationId);
        if (station1 == null) {
            throw new ApiException("rentalId not found");
        }
        station1.setPickUpStation(station.getPickUpStation());
        station1.setDropOffStation(station.getDropOffStation());
        station1.setHaveChargingStation(station.getHaveChargingStation());
        station1.setCapacity(station.getCapacity());
        station1.setStatus(station.getStatus());

        stationRepository.save(station1);

    }

    public void deleteStation(Integer stationId) {
        Station station = stationRepository.findStationByStationId(stationId);
        if (station == null) {
            throw new ApiException("station id not found");
        }
        stationRepository.delete(station);
    }

}
