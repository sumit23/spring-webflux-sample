package com.springsample.webflux.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sumit Singhal
 *
 */
@Component
@Getter
@Setter
public class ApplicationProperties {

    public ApplicationProperties() {
    }

    @Value("${twilio.from.phonunumber: #{null}}")
    private String twilioFromPhoneNumber;

}

