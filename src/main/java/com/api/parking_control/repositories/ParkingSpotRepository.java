package com.api.parking_control.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parking_control.model.ParkingSpotModel;


@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{ //sempre coloca qual é o model que esta usando   ---- so de eu extender ele ja seria a mesma coisa se eu anotar repository
    //verifica no banco se existe com o metodo atraves do que foi solicitado no controller
    boolean existsByLicensePlateCar(String licensePlateCar);
boolean existsByParkingSpotNumber(String parkingSpotNumber);
boolean existsByApartmentAndBlock(String apartment, String block);
}

