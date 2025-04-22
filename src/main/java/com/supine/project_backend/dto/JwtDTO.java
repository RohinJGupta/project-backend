package com.supine.project_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtDTO {
    String jwtToken;

    public JwtDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
