package com.example.madlab08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button read, write, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText)findViewById(R.id.editText);
        write = (Button)findViewById((R.id.button));
        read = (Button)findViewById(R.id.button2);
        clear = (Button)findViewById(R.id.button3);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = e1.getText().toString();
                try{
                    File f = new File("/SDCARD/testfile.txt");
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(msg.getBytes());
                    fo.close();
                    Toast.makeText(getBaseContext(), "Data wrote to SD Card", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String message;
                String buf = "";
                try {
                    File f = new File("/SDCARD/testfile.txt");
                    FileInputStream fin = new FileInputStream(f);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while((message = br.readLine()) != null) {
                        buf += message;
                    }
                    e1.setText(buf);
                    br.close();
                    fin.close();
                    Toast.makeText(getBaseContext(),"Data Received from SDCARD",Toast.LENGTH_LONG).show();
                } catch(Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                e1.setText("");
            }
        });
    }
}
