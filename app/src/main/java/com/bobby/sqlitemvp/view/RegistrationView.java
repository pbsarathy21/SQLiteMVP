package com.bobby.sqlitemvp.view;

public interface RegistrationView {

    void inputValidation(Integer integer);
    void emailExists();
    void userExists();
    void mobileExists();
    void registrationFailed();
    void registrationSuccess();

}
