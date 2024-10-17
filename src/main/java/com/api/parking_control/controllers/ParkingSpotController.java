package com.api.parking_control.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parking_control.dtos.ParkingSpotDto;
import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.services.ParkingSpotService;

import jakarta.validation.Valid;

import java.time.ZoneId;
import java.time.LocalDateTime;

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
    // Cria uma nova instância da classe ParkingSpotModel
    var parkingSpotModel = new ParkingSpotModel();
    
    // Copia as propriedades do DTO (ParkingSpotDto) para o Model (ParkingSpotModel)
    BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
    
    // Define a data de registro como a data e hora atual no fuso horário UTC
    parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    
    // Retorna uma resposta com o status de "Criado" (201) e o objeto salvo
    return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
}


    

}
