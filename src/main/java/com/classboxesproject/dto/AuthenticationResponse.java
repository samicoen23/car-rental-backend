package com.classboxesproject.dto;

import com.classboxesproject.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}
