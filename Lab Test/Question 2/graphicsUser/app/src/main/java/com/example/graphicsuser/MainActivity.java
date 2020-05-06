package com.example.graphicsuser;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name,usn;
    Button submit;
    RadioGroup filled;
    RadioButton fopt;
    Spinner Colours,Shapes;
    String[] shapes = {"circle","oval","rectangle","triangle"};
    String[] colors = {"red","blue","green"};
    String shape,color,fil;
    int radioid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Colours = findViewById(R.id.colo);
        Shapes = findViewById(R.id.shap);
        filled = findViewById(R.id.rgroup);
        submit = findViewById(R.id.submit);
        ArrayAdapter adapterc = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,colors);
        Colours.setAdapter(adapterc);
        ArrayAdapter adapters = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,shapes);
        Shapes.setAdapter(adapters);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                color = Colours.getSelectedItem().toString();
                shape = Shapes.getSelectedItem().toString();
                radioid = filled.getCheckedRadioButtonId();
                fopt = findViewById(radioid);
                fil = fopt.getText().toString();
                Intent i = new Intent(MainActivity.this,Secondary.class);
                i.putExtra("shape_key",shape);
                i.putExtra("color_key",color);
                i.putExtra("fil_key",fil);
                startActivity(i);
            }
        });
    }
}
