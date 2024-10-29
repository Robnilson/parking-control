package com.api.parking_control.controllers;

import com.api.parking_control.dtos.ParkingSpotDto;
import com.api.parking_control.dtos.UserParkingSpotDTO;
import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.model.UserModel;
import com.api.parking_control.services.UserParkingSpotService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;


@RestController
@RequestMapping("/parking-spot")

public class UserParkingSpotController {
    final UserParkingSpotService userParkingSpotService;

    public UserParkingSpotController(UserParkingSpotService userParkingSpotService) {
        this.userParkingSpotService = userParkingSpotService;
    }
    @PermitAll
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/admin")
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid UserParkingSpotDTO userparkingSpotDTO){

        if(userParkingSpotService.existsByUsername(userparkingSpotDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: User Name is already in use");
        }
        if(userParkingSpotService.existsByPassword(userparkingSpotDTO.getPassword())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Password is already in use");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userparkingSpotDTO, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userParkingSpotService.save(userModel));
    }

}
/*


    // Cria uma nova instância da classe ParkingSpotModel
    var parkingSpotModel = new ParkingSpotModel();

    // Copia as propriedades do DTO (ParkingSpotDto) para o Model (ParkingSpotModel)
    BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);

    // Define a data de registro como a data e hora atual no fuso horário UTC
    parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

    // Retorna uma resposta com o status de "Criado" (201) e o objeto salvo
    return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
}*/