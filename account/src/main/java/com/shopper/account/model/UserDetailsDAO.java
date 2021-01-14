package com.shopper.account.model;

import lombok.Builder;

@Builder
public class UserDetailsDAO extends DBDAO{
    private String userName;
    private String password;
    private String emailId;
    @Builder.Default
    private final long passwordExpiryDate = System.currentTimeMillis() + 777600000L;
}
