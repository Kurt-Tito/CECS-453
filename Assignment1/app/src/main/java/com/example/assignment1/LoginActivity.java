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

public class LoginActivity extends AppCompatActivity {

    private String username = "desusite";
    private String password = "123abc";

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
                if (username.equals(editTextUsername.getText().toString()) && password.equals(editTextPassword.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
                else if (tryCount == tryMax)
                {
                    /* finish() - finishes the activity where it is called from and you see the previous activity. */
                    /* System.exit(0) - restarts the app with one fewer activity on the stack.
                    So, if you called ActivityB from ActivityA, and System.exit(0) is called in ActivityB,
                    then the application will be killed and started immediately with only one activity ActivityA */

                    finish();
                }
                else if (!username.equals(editTextUsername.getText().toString()) || !(password.equals(editTextPassword.getText().toString())))
                {
                    Toast.makeText(LoginActivity.this, "INVALID USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
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
}
