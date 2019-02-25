package com.example.assignment1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//import static android.content.Context.MODE_APPEND;

public class DBModel {

    static final int READ_BLOCK_SIZE = 100;
    public static DBModel model = new DBModel();

    /* Creates a new User entry in the DB */
    public void writeNewEntry(Context ctx, String data)
    {
        try {
            FileOutputStream file = ctx.openFileOutput("db.txt", ctx.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(file);

            writer.write(data);
            writer.close();

            Toast.makeText(ctx, "File Saved Successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Checks if the User exists in the database and if its password matches */
    public boolean DBcheckUser(Context ctx, String username, String password)
    {
        String db ="";
        int ln_user = 0;
        int ln_password = 0;

        boolean validateUsername = false;
        boolean validatePassword = false;

        try {

            FileInputStream file = ctx.openFileInput("db.txt");
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

    /* Reads and returns the entire DB file as a Toast message */
    public void readData(Context ctx)
    {
        try {
            FileInputStream file = ctx.openFileInput("db.txt");
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
            Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
            //Log.d("DB_Text", s); //debug output

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Reads and returns the entire DB file as a string */
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
