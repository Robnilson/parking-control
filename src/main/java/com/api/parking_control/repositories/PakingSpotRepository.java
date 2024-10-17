package com.api.parking_control.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parking_control.model.ParkingSpotModel;


@Repository
public interface PakingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{ //sempre coloca qual é o model que esta usando   ---- so de eu extender ele ja seria a mesma coisa se eu anotar repository


}
