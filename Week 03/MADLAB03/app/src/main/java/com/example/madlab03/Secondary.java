package com.example.madlab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Secondary extends AppCompatActivity {
    TextView name,usn,dept;
    String n,u,d;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        name = findViewById(R.id.oname);
        usn = findViewById(R.id.ousn);
        dept = findViewById(R.id.odepartment);
        Intent i = getIntent();
        n = i.getStringExtra("name_key");
        u = i.getStringExtra("usn_key");
        d = i.getStringExtra("dept_key");
        name.setText(n);
        usn.setText(u);
        dept.setText(d);
    }
}
