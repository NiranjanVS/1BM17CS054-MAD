package com.example.madlab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    TextView res;
    EditText ip1;
    EditText ip2;
    Button add,mul,div,sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = (TextView) findViewById(R.id.result);
        ip1 = (EditText) findViewById(R.id.ip1);
        ip2  = (EditText)findViewById(R.id.ip2);
        add = (Button)findViewById(R.id.add);
        mul =(Button)findViewById(R.id.mul);
        sub = (Button)findViewById(R.id.sub);
        div = (Button)findViewById(R.id.div);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
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
        res.setText(num1+""+operator+""+num2+"="+result);
    }
}
