package com.codegym.aurora.logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiAppender extends AppenderSkeleton {
    private final RestTemplate restTemplate;
    private static final String LOG_API = "https://discord.com/api/webhooks/1173268338315628654/MIf3hNLkVicrophIg9bA_z27avZ2abb-yEDeEguYWZwwsPPI5O_eNgQ8qRU-skw5dSuv";

    public ApiAppender() {
        this.restTemplate = new RestTemplate();
        initAppender();
    }

    private void initAppender() {
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "```[%-5p] [%d{MM-dd HH:mm:ss zZ}] - [%m]```";
        layout.setConversionPattern(conversionPattern);
        this.setLayout(layout);
        this.activateOptions();
    }

    @Override
    protected void append(LoggingEvent event) {
        String message = layout.format(event);
        try {
            Map<String,String> data = new HashMap<>();
            data.put("content", message);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> content = new HttpEntity<>(data, headers);

            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(LOG_API, content, Object.class);
            // Handle API response
        } catch (Exception e) {
            // Handle if failed to sent log
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
