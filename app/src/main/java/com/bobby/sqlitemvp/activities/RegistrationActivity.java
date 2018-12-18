package com.bobby.sqlitemvp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bobby.sqlitemvp.R;
import com.bobby.sqlitemvp.model.RegistrationModel;
import com.bobby.sqlitemvp.presenter.RegistrationPresenter;
import com.bobby.sqlitemvp.view.RegistrationView;

public class RegistrationActivity extends AppCompatActivity implements RegistrationView{

    EditText name, username, email, password, dob, gender, mobile;
    RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationPresenter = new RegistrationModel(getApplicationContext(), this);

        name=findViewById(R.id.edit_name);
        username=findViewById(R.id.edit_username);
        email=findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);
        dob=findViewById(R.id.edit_dob);
        gender=findViewById(R.id.edit_gender);
        mobile=findViewById(R.id.edit_mobile);
    }

    public void isSignUpClicked(View view) {

        registrationPresenter.performRegistration(
                name.getText().toString(),
                username.getText().toString().trim(),
                email.getText().toString().trim(),
                password.getText().toString().trim(),
                dob.getText().toString(),
                gender.getText().toString().trim(),
                mobile.getText().toString().trim()
                );
    }

    @Override
    public void inputValidation(Integer integer) {

        switch (integer)
        {
            case 0 :
                name.setError("required");
                name.requestFocus();
                break;

            case 1 :
                username.setError("required");
                username.requestFocus();
                break;

            case 2 :
                email.setError("required");
                email.requestFocus();
                break;

            case 3 :
                password.setError("required");
                password.requestFocus();
                break;

            case 4 :
                dob.setError("required");
                dob.requestFocus();
                break;

            case 5 :
                gender.setError("required");
                gender.requestFocus();
                break;

            case 6 :
                mobile.setError("required");
                mobile.requestFocus();
                break;

            case 7 :
                email.setError("not a valid e-mail address");
                email.requestFocus();
                break;

                default:
                    Toast.makeText(this, "Something wrong on input validations", Toast.LENGTH_SHORT).show();
                    break;
        }

    }

    @Override
    public void emailExists() {

        email.setError("Already exists");
        email.requestFocus();

    }

    @Override
    public void userExists() {

        username.setError("Already exists");
        username.requestFocus();

    }

    @Override
    public void mobileExists() {

        mobile.setError("Already exists");
        mobile.requestFocus();

    }

    @Override
    public void registrationFailed() {

        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void registrationSuccess() {

        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

    }

}
