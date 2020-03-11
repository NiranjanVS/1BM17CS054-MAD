package com.example.a054lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button add,sub,mul,div,cls;
    TextView tv1,tv2;
    EditText ip1,ip2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        ip1 = findViewById(R.id.ip1);
        ip2 = findViewById(R.id.ip2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        float num1 = 0;
        float num2 = 0;
        float result = 0;
        String operator = "";
        if(TextUtils.isEmpty(ip1.getText().toString())||TextUtils.isEmpty(ip2.getText().toString()))
            return;
        num1 = Float.parseFloat(ip1.getText().toString());
        num2 = Float.parseFloat(ip2.getText().toString());
        switch(v.getId())
        {
            case R.id.add : operator = "+";
                result = num1+num2;
                break;
            case R.id.sub : operator = "-";
                result = num1-num2;
                break;
            case R.id.mul : operator = "x";
                result = num1*num2;
                break;
            case R.id.div : operator = "/";
                result = num1/num2;
                break;
            default : break;
        }
        tv1.setText(operator);
        tv2.setText(result+"");
    }
}

