package com.codegym.aurora.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public final class PaymentCache {

    private Boolean activeVnPayService;
    private final HashMap<String,String> paymentMap;

    private PaymentCache(){
        paymentMap = new HashMap<>();
        activeVnPayService = true;
    }

    public Boolean getActiveVnPayService() {
        return activeVnPayService;
    }

    public void setActiveVnPayService(Boolean activeVnPayService) {
        this.activeVnPayService = activeVnPayService;
    }

    public void addPaymentId(String username, String paymentId) {
        paymentMap.put(username, paymentId);
    }

    public String getPaymentId(String username){
        return paymentMap.get(username);
    }

    public String getUsername(String paymentId) {
        for (HashMap.Entry<String, String> entry : paymentMap.entrySet()) {
            if (entry.getValue().equals(paymentId)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public void removePaymentId(String username){
        paymentMap.remove(username);
    }
}
