package com.shopper.account.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDetailsDAO extends DBDAO{
    private String userName;
    private String password;
    private String emailId;
    @Builder.Default
    private final long passwordExpiryDate = System.currentTimeMillis() + 777600000L;
}
