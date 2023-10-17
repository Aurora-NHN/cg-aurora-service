package com.codegym.aurora.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public final class PaymentCache {

    private final HashMap<String,UUID> paymentMap;

    private PaymentCache(){
        paymentMap = new HashMap<>();
    }

    public void addPaymentId(String username, UUID uuid) {
        paymentMap.put(username, uuid);
    }

    public UUID getPaymentId(String username){
        return paymentMap.get(username);
    }

    public String getUsername(UUID uuid) {
        for (HashMap.Entry<String, UUID> entry : paymentMap.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public void removePaymentId(String username){
        paymentMap.remove(username);
    }
}
