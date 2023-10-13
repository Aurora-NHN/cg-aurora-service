package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LifePhaseResponseDTO {

    private Long id;

    private YoungStateNumberResponseDTO youngStateNumberResponseDTO;

    private MatureStateNumberResponseDTO matureStateNumberResponseDTO;

    private OldStateNumberResponseDTO oldStateNumberResponseDTOr;

}
