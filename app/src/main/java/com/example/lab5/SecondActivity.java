package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes;

public class SecondActivity extends AppCompatActivity {


    final String FILE_NAME = "file.txt";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        listView = (ListView)findViewById(R.id.name_list);



    }


    public void showData(View view) {

        File file = new File(getFilesDir(), FILE_NAME);
        List<NameData> namelist = new ArrayList<>();
        String filename = FILE_NAME;
        StringBuilder stringBuilder = new StringBuilder();
        if(file.exists())


        {



            try {

                FileInputStream fis = openFileInput(filename);
                Scanner sc = new Scanner(fis);
                String line = "";

                while (sc.hasNextLine()) {

                    line = sc.nextLine();
                    stringBuilder.append(line).append("\n");







                }

                sc.close();
                String data = stringBuilder.toString();
                NameData name = new NameData(data);

                namelist.add(name);
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, namelist);

                listView.setAdapter(arrayAdapter);


            } catch (IOException e) {

            }

        }else{
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }


    }
}