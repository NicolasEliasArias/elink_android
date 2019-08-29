package com.example.nicol.elink.DatabaseElinkManager;

import com.example.nicol.elink.CallBacks.Callback;

public interface AuthManager {
    public void logIn(String email, String password, final Callback callback);
    public void registerUser(final String email, final String password, final Callback callback);
}
