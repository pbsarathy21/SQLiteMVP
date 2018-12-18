package com.bobby.sqlitemvp.view;

public interface LoginView {

    void userNotExists();
    void passwordIncorrect();
    void userEmpty();
    void passEmpty();
    void logInSuccess();

}
