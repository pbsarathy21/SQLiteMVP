package com.bobby.sqlitemvp.model;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import com.bobby.sqlitemvp.presenter.RegistrationPresenter;
import com.bobby.sqlitemvp.utilities.DatabaseHelper;
import com.bobby.sqlitemvp.view.RegistrationView;
public class RegistrationModel implements RegistrationPresenter {
    private Context context;
    private RegistrationView registrationView;
    public RegistrationModel(Context context, RegistrationView registrationView) {
        this.context = context;
        this.registrationView = registrationView;
    }
    @Override
    public void performRegistration(String name, String username, String email,
                                    String password, String dob, String gender, String mobile) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        if (TextUtils.isEmpty(name))
        {
            registrationView.inputValidation(0);
            return;
        }
        if (TextUtils.isEmpty(username))
        {
            registrationView.inputValidation(1);
            return;
        }
        if (TextUtils.isEmpty(email))
        {
            registrationView.inputValidation(2);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            registrationView.inputValidation(7);
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            registrationView.inputValidation(3);
            return;
        }
        if (TextUtils.isEmpty(dob))
        {
            registrationView.inputValidation(4);
            return;
        }
        if (TextUtils.isEmpty(gender))
        {
            registrationView.inputValidation(5);
            return;
        }
        if (TextUtils.isEmpty(mobile))
        {
            registrationView.inputValidation(6);
            return;
        }
        boolean isUsernameExists = databaseHelper.checkData(username, "USERNAME");
        if (isUsernameExists)
        {
            registrationView.userExists();
            return;
        }
        boolean isEmailExists = databaseHelper.checkData(email, "EMAIL");
        if (isEmailExists)
        {
            registrationView.emailExists();
            return;
        }
        boolean isMobileExists = databaseHelper.checkData(mobile, "MOBILE");
        if (isMobileExists)
        {
            registrationView.mobileExists();
            return;
        }
        boolean isInserted = databaseHelper.insertData(name, username, email, password,
                dob, gender, mobile);

        if (isInserted)
        {
            registrationView.registrationSuccess();
        }
        else
        {
            registrationView.registrationFailed();
        }
    }
}