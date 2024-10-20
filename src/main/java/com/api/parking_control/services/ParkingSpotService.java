package com.api.parking_control.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service


public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository){
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional //Garante que o método seja executado dentro de uma transação. Se houver qualquer erro, as alterações serão revertidas.
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel){
        // Salva o objeto parkingSpotModel no banco de dados usando o repositório
        return parkingSpotRepository.save(parkingSpotModel);
}
    //criado o metodo em

    public boolean existsByLicensePlateCar(@NotBlank @Size(max = 7) String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(@NotBlank String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }


    public boolean existsByApartmentAndBlock(@NotBlank String apartment, @NotBlank String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public List<ParkingSpotModel> findAll() {
        //chama o metodo findAll na classe repository
        return parkingSpotRepository.findAll();
    }


    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }
}

