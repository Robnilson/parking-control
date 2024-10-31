package com.api.parking_control.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserParkingSpotDTO {

    @NotBlank
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;


    public @NotBlank String getUsername() { return username; }

    public void setUserName(@NotBlank String userName) {this.username = userName;}

    public @NotBlank String getPassword() { return password; }

    public void setPassword(@NotBlank String password){this.password = password;}

    public @NotBlank String getId() {return id; }

    public void setId(@NotBlank String id) { this.id = id; }
}
