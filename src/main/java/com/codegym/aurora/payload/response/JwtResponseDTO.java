package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponseDTO implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwtToken;

}