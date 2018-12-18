package com.bobby.sqlitemvp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bobby.sqlitemvp.R;
import com.bobby.sqlitemvp.model.LoginModel;
import com.bobby.sqlitemvp.presenter.LoginPresenter;
import com.bobby.sqlitemvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView{

    EditText username, password;
    TextView signUp;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginModel(getApplicationContext(), this);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signUp=findViewById(R.id.text_signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSignUpClicked();
            }
        });
    }

    public void isLogInClicked(View view) {
        String name = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        loginPresenter.performLogin(name, pass);
    }

    public void isSignUpClicked() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    @Override
    public void userNotExists() {
        username.setError("user not exists");
    }

    @Override
    public void passwordIncorrect() {
        password.setError("Incorrect");
        password.requestFocus();
    }

    @Override
    public void userEmpty() {
        username.setError("required");
        username.requestFocus();
    }

    @Override
    public void passEmpty() {
        password.setError("required");
        password.requestFocus();
    }

    @Override
    public void logInSuccess() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, ListDataActivity.class));
    }
}
