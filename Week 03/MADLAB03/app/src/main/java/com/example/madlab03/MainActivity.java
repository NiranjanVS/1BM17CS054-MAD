package com.example.madlab03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText name,usn;
    Button submit;
    Spinner department;
    String[] dept = {"Computer Science","Electronics and Communication","Mechanical","Civil","Biotechnology","Electrical","Telecommunication","Information Science"};
    String na,us,dep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        usn = findViewById(R.id.usn);
        submit = findViewById(R.id.submit);
        department = findViewById(R.id.dept);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,dept);
        department.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                na = name.getText().toString();
                us = usn.getText().toString();
                dep = department.getSelectedItem().toString();
                Intent i = new Intent(MainActivity.this,Secondary.class);
                i.putExtra("name_key",na);
                i.putExtra("usn_key",us);
                i.putExtra("dept_key",dep);
                startActivity(i);
            }
        });
    }
}
