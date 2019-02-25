package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    //private String username = "desusite";
    //private String password = "123abc";

    /* Declare Views */
    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonNewUser;

    private int tryCount = 0;
    private int tryMax = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Initialize Views */
        setUPViews();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUser(editTextUsername.getText().toString(), editTextPassword.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Login is successful", Toast.LENGTH_SHORT).show();
                    tryCount = 0;
                }
                else if (tryCount == tryMax)
                {
                    /* finish() - finishes the activity where it is called from and you see the previous activity. */
                    /* System.exit(0) - restarts the app with one fewer activity on the stack.
                    So, if you called ActivityB from ActivityA, and System.exit(0) is called in ActivityB,
                    then the application will be killed and started immediately with only one activity ActivityA */

                    finish();
                }
                else if (!checkUser(editTextUsername.getText().toString(), editTextPassword.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    tryCount++;
                }
            }
        });

        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(i);
            }
        });
    }

    private void setUPViews()
    {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonNewUser = (Button) findViewById(R.id.buttonNewUser);
    }

    /*check if username and password match and is in DB */
    private boolean checkUser(String username, String password)
    {
        String db ="";
        int ln_user = 0;
        int ln_password = 0;

        boolean validateUsername = false;
        boolean validatePassword = false;

        try {

            FileInputStream file = openFileInput("db.txt");
            InputStreamReader reader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String receiveString = "";

            while ((receiveString = bufferedReader.readLine()) != null) {

                ln_user++;
                if (receiveString.contains(username))
                {
                    Log.e("usernameline", receiveString +" ln: " +ln_user);
                    validateUsername = true;
                    break;
                }
            }

            while ((receiveString = bufferedReader.readLine()) != null) {

                ln_password++;
                if (receiveString.contains(password))
                {
                    Log.e("passwordline", receiveString +" ln:" +ln_password);
                    validatePassword = true;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (validateUsername && validatePassword)
            return true;
        else
            return false;

    }
}
