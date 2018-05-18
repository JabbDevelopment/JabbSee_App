package com.jabb.jabbsee.managers;

import android.accounts.AccountManager;

import com.jabb.jabbsee.communicators.AuthCommunicator;
import com.jabb.jabbsee.models.User;

import java.io.IOException;

public class AuthManager {

    private AccountManager accountManager;
    private AuthCommunicator authCommunicator;
    private static User loggedInUser;

    public AuthManager(){
        authCommunicator = new AuthCommunicator();
    }

    public boolean login(String username, String password) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(authCommunicator.validateUserFromServer(user) == null) {
            return false;
        }
        loggedInUser = user;
        return true;

    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
