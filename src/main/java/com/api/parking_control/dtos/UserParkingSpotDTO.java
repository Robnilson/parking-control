package com.api.parking_control.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserParkingSpotDTO {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;


    public @NotBlank String getUsername() { return userName; }

    public void setUserName(@NotBlank String userName) {this.userName = userName;}

    public @NotBlank String getPassword() { return password; }
    public void setPassword(@NotBlank String password){this.password = password;}



}
