package com.example.capstone3.Repository;

import com.example.capstone3.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station,Integer> {
    Station findStationByStationId(Integer stationId);
}
