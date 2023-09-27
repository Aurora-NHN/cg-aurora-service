package com.codegym.aurora.back_office.service;

public interface SecurityService {

    boolean isAuthenticated();

    boolean isValidToken(String token);
}
