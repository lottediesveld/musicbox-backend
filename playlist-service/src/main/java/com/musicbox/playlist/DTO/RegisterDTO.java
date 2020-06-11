package com.musicbox.playlist.DTO;

public class RegisterDTO {
    Long userId;
    String username;

    public RegisterDTO() {
    }

    public RegisterDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
