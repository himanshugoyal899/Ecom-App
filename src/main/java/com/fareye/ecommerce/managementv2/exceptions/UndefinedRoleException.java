package com.fareye.ecommerce.managementv2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UndefinedRoleException extends RuntimeException {
    public UndefinedRoleException(String message) {
        super(message);
    }
}

