package com.shopper.account.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidUserDetailsException extends AccountServiceException{
    private String userName;
}
