package com.example.assignment1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {

    private EditText createUsername, createPassword, confirmPassword, createFirstName, createLastName, createEmail, createAge;
    private Button cancelButton, saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        /* Initialize views */
        setUpViews();


        /* Buttons */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!allFieldsFilled())      /* Priority 1 */
                {
                    Toast.makeText(CreateUserActivity.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                }
                else if (!passwordsMatch())  /* Priority 2*/
                {
                    Toast.makeText(CreateUserActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                else if (!legalAge())        /* Priority 3 */
                {
                    Toast.makeText(CreateUserActivity.this, "Age must be between 1 and 99!", Toast.LENGTH_SHORT).show();
                }
                else if(allFieldsFilled() && passwordsMatch() && legalAge())
                {
                    Toast.makeText(CreateUserActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                    /* Insert Save Function */
                }
            }
        });
    }

    private void setUpViews()
    {
        createUsername = (EditText) findViewById(R.id.editTextCreateUsername);
        createPassword = (EditText) findViewById(R.id.editTextCreatePassword);
        confirmPassword = (EditText) findViewById(R.id.editTextConfirmCreatePassword);
        createFirstName = (EditText) findViewById(R.id.editTextCreateFirstName);
        createLastName = (EditText ) findViewById(R.id.editTextCreateLastName);
        createEmail = (EditText) findViewById(R.id.editTextCreateEmail);
        createAge = (EditText) findViewById(R.id.editTextCreateAge);

        cancelButton = (Button) findViewById(R.id.buttonCancel);
        saveButton = (Button) findViewById(R.id.buttonSave);
    }

    private boolean allFieldsFilled()
    {
        if (createUsername.getText().toString().equals("") || createPassword.getText().toString().equals("")
                || confirmPassword.getText().toString().equals("") || createFirstName.getText().toString().equals("")
                || createLastName.getText().toString().equals("") || createEmail.getText().toString().equals("")
                || createAge.getText().toString().equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean passwordsMatch()
    {
        if (createPassword.getText().toString().equals(confirmPassword.getText().toString()))
            return true;
        else
            return false;
    }

    private boolean legalAge()
    {
        if (Integer.parseInt(createAge.getText().toString()) >= 1 && Integer.parseInt(createAge.getText().toString()) <= 99)
            return true;
        else
            return false;
    }
}
