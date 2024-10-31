package com.api.parking_control.services;

import com.api.parking_control.model.ParkingSpotModel;
import com.api.parking_control.model.UserModel;
import com.api.parking_control.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


@Service
public class UserParkingSpotService {

    final UserRepository userRepository;

    public UserParkingSpotService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }
    public boolean existsByUsername(@NotBlank String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByPassword(@NotBlank String Password){
        return userRepository.existsByPassword(Password);

    }
    public boolean existsById(@NotBlank String Id){
        return userRepository.existsById(UUID.fromString(Id));
    }
    @Transactional
    public Object findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


}
