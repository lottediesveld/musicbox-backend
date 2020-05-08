package com.musicbox.user.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicbox.user.common.security.Role;

import java.util.Set;

public class UserDTO {
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String username;

    @JsonProperty
    private String email;

    @JsonProperty
    private String mobileNumber;

    @JsonProperty
    private String zipCode;

    @JsonProperty
    private String houseNumber;

    @JsonProperty
    private Set<Role> roles;

    public UserDTO(String firstName, String lastName, String username, String email, String mobileNumber, String zipCode, String houseNumber, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.roles = roles;
    }
}
