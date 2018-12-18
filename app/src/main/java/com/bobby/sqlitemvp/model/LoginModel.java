package com.bobby.sqlitemvp.model;

import android.content.Context;
import android.text.TextUtils;

import com.bobby.sqlitemvp.presenter.LoginPresenter;
import com.bobby.sqlitemvp.utilities.DatabaseHelper;
import com.bobby.sqlitemvp.view.LoginView;

public class LoginModel implements LoginPresenter {

    private Context context;
    private LoginView loginView;

    public LoginModel(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    @Override
    public void performLogin(String username, String password) {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        
        if (TextUtils.isEmpty(username)) {
            loginView.userEmpty();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            loginView.passEmpty();
            return;
        }
            boolean isUserExists = databaseHelper.checkData(username, "USERNAME");

            if (isUserExists)
            {
                boolean isUser = databaseHelper.checkPassword(username, password);

                if (isUser)
                {
                    loginView.logInSuccess();
                }
                else
                {
                    loginView.passwordIncorrect();
                }
            }

            else
            {
                loginView.userNotExists();
            }
        }
}
