package com.codegym.aurora.converter;

import com.codegym.aurora.entity.Address;
import com.codegym.aurora.payload.request.AddressRequestDTO;
import com.codegym.aurora.payload.response.AddressResponseDTO;

public interface AddressConvert {
    Address convertAddressRequestDTOToEntity(AddressRequestDTO addressRequestDTO);
    AddressResponseDTO convertAddressRequestEntityToDTO(Address address);

}
