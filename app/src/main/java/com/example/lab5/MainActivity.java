package com.example.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    final String FILE_NAME = "file.txt";

    EditText userNameET;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userNameET = findViewById(R.id.name_ET);




    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.optionMenu)
        {
            Intent myintent = new Intent(MainActivity.this, SecondActivity.class);

            startActivity(myintent);

            return false;




        }
            return super.onOptionsItemSelected(item);
    }



    public void saveData(View view) {


        String name = userNameET.getText().toString();

            try {
                FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);

                fos.write(name.getBytes());
                fos.write("\n".getBytes());
                fos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        userNameET.setText("");


    }



    public void deleteData(View v){

        openDialog();


    }


    public void openDialog() {
        File dir = getFilesDir();
        File file = new File(dir, FILE_NAME);




        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to delete File?")
                .setCancelable(false)
                .setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(file.exists()){
                            deleteFile(FILE_NAME);
                        }
                        Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setTitle("Deleting Users_List_FILE")
                .setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog alert =builder.create();
        alert.show();



    }





}