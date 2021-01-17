package com.shopper.account.service;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.shopper.account.Validator;
import com.shopper.account.db.LoginDB;
import com.shopper.account.exceptions.InvalidPasswordException;
import com.shopper.account.exceptions.Messages;
import com.shopper.account.exceptions.UserNotFoundException;
import com.shopper.account.model.LoginRequestBody;
import com.shopper.account.model.SignUpRequestBody;
import com.shopper.account.model.UserDetailsDAO;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final Validator validator;
    private final LoginDB loginDB;

    @Autowired
    AccountService(Validator validator, LoginDB loginDB) {
        this.validator = validator;
        this.loginDB = loginDB;
    }

    public String login(final LoginRequestBody loginRequestBody) {
        MongoCursor<Document> documentCursor = getUserDetails(loginRequestBody.getUserName());

        if(!documentCursor.hasNext()) {
            throw new UserNotFoundException(Messages.USER_NOT_FOUND, loginRequestBody.getUserName());
        }

        Document userDetail = documentCursor.next();
        if(loginRequestBody.getPassword().equals(userDetail.get("password"))) {
            return "Login Successful";
        }

        throw new InvalidPasswordException();
    }

    public String signUp(final SignUpRequestBody signUpRequestBody) {
        validator.validateUserDetails(signUpRequestBody);
        MongoCursor<Document> documentCursor = getUserDetails(signUpRequestBody.getUserName());

        if(documentCursor.hasNext()) {
            throw new UserNotFoundException(Messages.USER_ALREADY_EXISTS, signUpRequestBody.getUserName());
        }

        UserDetailsDAO userDetails = UserDetailsDAO.builder().emailId(signUpRequestBody.getEmailId())
                .password(signUpRequestBody.getPassword())
                .userName(signUpRequestBody.getUserName())
                .build();

        loginDB.insert(userDetails);
        return "Sign Up Successful";
    }

    private MongoCursor<Document> getUserDetails(final String userName) {
        Bson filter = Filters.eq("userName", userName);
        return loginDB.find(filter);
    }

}