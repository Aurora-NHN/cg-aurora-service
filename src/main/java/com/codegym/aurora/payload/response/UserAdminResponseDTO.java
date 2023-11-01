package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAdminResponseDTO {

    private String username;

    private Integer count;

    private String role;

    private Integer totalCount;

    private boolean isActivated;
}
