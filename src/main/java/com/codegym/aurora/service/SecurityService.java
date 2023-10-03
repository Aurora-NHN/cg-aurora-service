package com.codegym.aurora.service;

public interface SecurityService {

    boolean isAuthenticated();

    boolean isValidToken(String token);
}
