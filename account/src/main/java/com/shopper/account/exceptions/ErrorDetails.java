package com.shopper.account.exceptions;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ErrorDetails {
    private String errorCode;
    private String errorDetail;
}
