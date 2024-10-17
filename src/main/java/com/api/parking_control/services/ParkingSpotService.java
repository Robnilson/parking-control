package com.api.parking_control.services;

import org.springframework.stereotype.Service;

import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.repositories.PakingSpotRepository;

import jakarta.transaction.Transactional;

@Service


public class ParkingSpotService {

    final PakingSpotRepository pakingSpotRepository;

    
    public ParkingSpotService(PakingSpotRepository pakingSpotRepository){
        this.pakingSpotRepository = pakingSpotRepository;
    }

    @Transactional //Garante que o método seja executado dentro de uma transação. Se houver qualquer erro, as alterações serão revertidas.
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel){
        // Salva o objeto parkingSpotModel no banco de dados usando o repositório
        return pakingSpotRepository.save(parkingSpotModel);
}
    

}
