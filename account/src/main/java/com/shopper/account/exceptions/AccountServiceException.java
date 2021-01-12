package com.shopper.account.exceptions;

public class AccountServiceException extends RuntimeException {
    private static final long serialVersionUID = 7684248076818601417L;
    protected String userMessage;
    protected Object[] args;

    public AccountServiceException() {}

    public AccountServiceException(String userMessage, Object[] args) {
        this.userMessage = userMessage;
        this.args = args;
    }

    public ErrorDetail getErrorDetail() {
        return new ErrorDetail("1", userMessage);
    }

}
