package com.codegym.aurora.service;

import com.codegym.aurora.payload.sdi.ClientSdi;

import java.util.UUID;

public interface ClientService {

    Boolean create(ClientSdi sdi);

    Boolean sendOrderReturn(String email, UUID paymentId);
}
