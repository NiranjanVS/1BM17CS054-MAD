package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.AlertDialog.Builder;

public class MainActivity extends AppCompatActivity {
    EditText name,usn;
    Button submit;
    Spinner department,section,semester;
    String[] depto = {"--","Computer Science","Electronics and Communication","Mechanical","Civil","Biotechnology","Electrical","Telecommunication","Information Science"};
    String[] semo = {"--","1","2","3","4","5","6","7","8"};
    String[] seco = {"--","A","B","C"};
    String nam,us,dep,sec,sem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        usn = findViewById(R.id.usn);
        submit = findViewById(R.id.submit);
        department = findViewById(R.id.department);
        section = findViewById(R.id.section);
        semester = findViewById(R.id.semester);

        ArrayAdapter adapterdept = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,depto);
        ArrayAdapter adaptersec = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,seco);
        ArrayAdapter adaptersem = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,semo);

        department.setAdapter(adapterdept);
        semester.setAdapter(adaptersem);
        section.setAdapter(adaptersec);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nam = name.getText().toString();
                us = usn.getText().toString();
                dep = department.getSelectedItem().toString();
                sem = semester.getSelectedItem().toString();
                sec = section.getSelectedItem().toString();

                if(nam.toString().trim().length()==0)
                    showMessage("Error","Enter the Name");
                else if(us.toString().trim().length()==0)
                    showMessage("Error","Enter the USN");
                else if(sem.equals("--"))
                    showMessage("Error","please select the Semester");
                else if(sec.equals("--"))
                    showMessage("Error","please select the Section");
                else if(dep.equals("--"))
                    showMessage("Error","please select the Department");
                else{
                    Intent i = new Intent(MainActivity.this,Secondary.class);
                    i.putExtra("name",nam);
                    i.putExtra("usn",us);
                    i.putExtra("dept",dep);
                    i.putExtra("sem",sem);
                    i.putExtra("sec",sec);
                    startActivity(i);
                }
            }
        });
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
