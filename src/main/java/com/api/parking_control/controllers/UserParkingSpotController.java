package com.api.parking_control.controllers;

import com.api.parking_control.dtos.ParkingSpotDto;
import com.api.parking_control.dtos.UserParkingSpotDTO;
import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.model.UserModel;
import com.api.parking_control.services.UserParkingSpotService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;


@RestController
@RequestMapping("/parking-spot")

public class UserParkingSpotController {
    final UserParkingSpotService userParkingSpotService;
    private final PasswordEncoder passwordEncoder;

    public UserParkingSpotController(UserParkingSpotService userParkingSpotService, PasswordEncoder passwordEncoder) {
        this.userParkingSpotService = userParkingSpotService;
        this.passwordEncoder = passwordEncoder;
    }

    //@PermitAll
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid UserParkingSpotDTO userparkingSpotDTO) {

        if (userParkingSpotService.existsByUsername(userparkingSpotDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: User Name is already in use");
        }

//        if (userParkingSpotService.existsByPassword(userparkingSpotDTO.getPassword())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Password is already in use");
//        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userparkingSpotDTO, userModel);

//        if (passwordEncoder.upgradeEncoding(userModel.getPassword()));
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        if ("admin".equals(userparkingSpotDTO.getId())) {userModel.setUserId(UUID.fromString("2eebe740-4b96-4290-9cc9-c1e993fca0d1"));
        }
        if ("user".equalsIgnoreCase(userparkingSpotDTO.getId())) {userModel.setUserId(UUID.fromString("c1dccf85-e7ce-45f1-82ca-d859f69b8216"));}

        return ResponseEntity.status(HttpStatus.CREATED).body(userParkingSpotService.save(userModel));
    }

    //@PermitAll
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userParkingSpotService.findAll(pageable));
    }

}
