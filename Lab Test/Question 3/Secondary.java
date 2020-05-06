package com.example.studentinformation;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Secondary extends AppCompatActivity {
    TextView name,usn,dept,section,semester;
    String nam,us,dep,sec,sem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        name = findViewById(R.id.oname);
        usn = findViewById(R.id.ousn);
        dept = findViewById(R.id.odepartment);
        section = findViewById(R.id.osection);
        semester = findViewById(R.id.osemester);
        Intent i = getIntent();
        nam = i.getStringExtra("name");
        us = i.getStringExtra("usn");
        dep = i.getStringExtra("dept");
        sec = i.getStringExtra("sec");
        sem = i.getStringExtra("sem");

        name.setText(String.format("Name:    %s", nam));
        usn.setText(String.format("USN:    %s", us));
        dept.setText(String.format("Dept:    %s", dep));
        semester.setText(String.format("Semester:     %s", sem));
        section.setText(String.format("Section:  %s", sec));
    }
}
