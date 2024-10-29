package com.api.parking_control.services;

import com.api.parking_control.model.UserModel;
import com.api.parking_control.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;


@Service
public class UserParkingSpotService {

    final UserRepository userRepository;

    public UserParkingSpotService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public UserModel save(UserModel userModel){
        return null;
    }
    public boolean existsByUsername(@NotBlank String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByPassword(@NotBlank String Password){
        return userRepository.existsByPassword(Password);
    }

  //  public boolean existsByUsername(@NotBlank String username) {
   //     return userRepository.existsByUsername(username);
 //   }
}
