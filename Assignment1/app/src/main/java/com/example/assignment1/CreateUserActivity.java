package com.example.assignment1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CreateUserActivity extends AppCompatActivity {

    private EditText createUsername, createPassword, confirmPassword, createFirstName, createLastName, createEmail, createAge;
    private Button cancelButton, saveButton;

    static final int READ_BLOCK_SIZE = 100;

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
                else if (userExists())
                {
                    Toast.makeText(CreateUserActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                }
                else if (emailExists())
                {
                    Toast.makeText(CreateUserActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                }
                else if(allFieldsFilled() && passwordsMatch() && legalAge())
                {
                    //Toast.makeText(CreateUserActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                    /* Insert Save Function */
                    saveData();
                    readData();
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

    private boolean userExists()
    {
        String ret = "";

        try {
            FileInputStream file = openFileInput("db.txt");
            InputStreamReader reader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            reader.close();
            ret = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ret.contains(createUsername.getText().toString()))
            return true;
        else
            return false;

    }

    private boolean emailExists()
    {
        String ret = "";

        try {
            FileInputStream file = openFileInput("db.txt");
            InputStreamReader reader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            reader.close();
            ret = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ret.contains(createEmail.getText().toString()))
            return true;
        else
            return false;

    }

    private void writeData(String data)
    {
        try {
            FileOutputStream file = openFileOutput("db.txt", MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(file);

            writer.write(data);
            writer.close();

            Toast.makeText(getBaseContext(), "File Saved Successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void readData()
    {
        try {
            FileInputStream file = openFileInput("db.txt");
            InputStreamReader reader = new InputStreamReader(file);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = reader.read(inputBuffer)) > 0)
            {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }

            reader.close();
            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
            //Log.d("DB_Text", s); //debug output

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData()
    {
        writeData("username:"+createUsername.getText().toString() +"\n"
                    +"password:"+createPassword.getText().toString() +"\n"
                    +"firstName:"+createFirstName.getText().toString() +"\n"
                    +"lastName:"+createLastName.getText().toString() +"\n"
                    +"email:"+createEmail.getText().toString() +"\n"
                    +"age:"+createAge.getText().toString() +"\n"
                    +"-----------------------------------" +"\n");

        /*
        writeData("password:"+createPassword.getText().toString() +"\n"); //password
        writeData("firstName:"+createFirstName.getText().toString() +"\n"); //firstName
        writeData("lastName:"+createLastName.getText().toString() +"\n"); //lastName
        writeData("email:"+createEmail.getText().toString() +"\n"); //email
        writeData("age:"+createAge.getText().toString() +"\n"); //age
        */
    }

    /*********************************************************************/
    /*********************************************************************/

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("db.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
