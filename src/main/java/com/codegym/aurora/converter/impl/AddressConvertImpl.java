package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.AddressConvert;
import com.codegym.aurora.entity.Address;
import com.codegym.aurora.payload.request.AddressRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressConvertImpl implements AddressConvert {
    @Override
    public Address convertAddressRequestDTOToEntity(AddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequestDTO,address);
        return address;
    }
}
