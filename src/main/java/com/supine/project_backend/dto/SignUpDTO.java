package com.supine.project_backend.dto;

import com.supine.project_backend.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpDTO {
    String email;
    String password;
    UserRole userRole;
}
