package com.example.a054lab0506;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
public class MainActivity extends AppCompatActivity implements OnClickListener {
    EditText roll,name,marks;
    Button ins,del,upd,vie,vi;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = findViewById(R.id.roll);
        name = findViewById(R.id.name);
        marks = findViewById(R.id.marks);
        ins = findViewById(R.id.ins);
        del = findViewById(R.id.del);
        upd = findViewById(R.id.upd);
        vie = findViewById(R.id.vie);
        vi = findViewById(R.id.viea);
        ins.setOnClickListener(this);
        del.setOnClickListener(this);
        upd.setOnClickListener(this);
        vie.setOnClickListener(this);
        vi.setOnClickListener(this);
        db = openOrCreateDatabase("StudentDB",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR , name VARCHAR , marks VARCHAR);");
    }
    @Override
    public void onClick(View view) {
        if(view==ins)
        {
            if((roll.getText().toString().trim().length()==0) || (name.getText().toString().trim().length()==0) || (marks.getText().toString().trim().length()==0))
            {
                showMessage("Error","Please enter all the values");
                return;
            }
            db.execSQL("INSERT INTO student69 VALUES('"+roll.getText()+"','"+name.getText()+ "','"+marks.getText()+"');");
            showMessage("Success","Record added");
            clearText();
        }
        if(view==del)
        {
            if((roll.getText().toString().trim().length()==0) || (name.getText().toString().trim().length()==0)) {
                showMessage("Error", "Please Enter the Roll no ");
                return;
            }
            Cursor c = db.rawQuery("select * from student where rollno="+roll.getText()+";",null);
            if(c.moveToFirst())
            {
                db.execSQL("delete from student where rollno = "+roll.getText()+";");
                showMessage("Success","Record Deleted");
            }
            else
                showMessage("Error","Invalid Roll no");
            clearText();
        }
        if(view == upd)
        {
            if(roll.getText().toString().trim().length()==0)
            {
                showMessage("Error","Please enter all the values");
                return;
            }
            Cursor c = db.rawQuery("select * from student where rollno="+roll.getText()+";",null);
            if(c.moveToFirst())
            {
                db.execSQL("update student set name ="+name.getText()+",marks ="+marks.getText()+" where rollno = "+roll.getText()+";");
                showMessage("Success","Record Modifies");
            }
            else
                showMessage("Error","Invalid Roll no");
            clearText();
        }
        if(view == vie)
        {
            if(roll.getText().toString().trim().length()==0)
            {
                showMessage("Error","Please Enter the Roll number");
                return;
            }
            Cursor c = db.rawQuery("select * from student where rollno="+roll.getText()+";",null);
            if(c.moveToFirst())
            {
                name.setText(c.getString(1));
                marks.setText(c.getString(2));
            }
            else
            {
                showMessage("Error","No records found");
                clearText();
            }
        }
        if(view==vi)
        {
            Cursor c = db.rawQuery("select * from student ;",null);
            if(c.getCount()==0)
            {
                showMessage("Error","No records found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("roll : "+c.getString(0)+"\n");
                buffer.append("name : "+c.getString(1)+"\n");
                buffer.append("Marks : "+c.getString(2)+"\n\n");
            }
            showMessage("Student Details",buffer.toString());
        }
    }
    private void clearText()
    {
        roll.setText("");
        name.setText("");
        marks.setText("");
        roll.requestFocus();
    }
    private void showMessage(String title, String message)
    {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
