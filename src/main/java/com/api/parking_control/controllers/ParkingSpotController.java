package com.api.parking_control.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import com.api.parking_control.dtos.ParkingSpotDto;
import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.services.ParkingSpotService;

import jakarta.validation.Valid;

import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //permite q seja acessado de qualquer fonte
@RequestMapping("/parking-spot")

public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;
    //faz um ponto de injeção dentro da classe 

    public ParkingSpotController(ParkingSpotService parkingSpotService){
        this.parkingSpotService = parkingSpotService;
    }

@PostMapping
public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        //faz validaçao se ja nao foi gravado no banco  e retorna com a mensagem

    if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use");
    }
    if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use");
    }
    if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registred in use");
    }






        // Cria uma nova instância da classe ParkingSpotModel
    var parkingSpotModel = new ParkingSpotModel();
    
    // Copia as propriedades do DTO (ParkingSpotDto) para o Model (ParkingSpotModel)
    BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
    
    // Define a data de registro como a data e hora atual no fuso horário UTC
    parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    
    // Retorna uma resposta com o status de "Criado" (201) e o objeto salvo
    return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
}

    @GetMapping
    //obtem todos os registros no banco

    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }
    

}
