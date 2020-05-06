package com.example.a1bm17cs054;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.provider.Settings;
import android.view.View;
import android.app.AlertDialog.Builder;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener, LocationListener {
    EditText id, address;
    Button Insert, Delete, View, Sync;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.ide);
        address = (EditText) findViewById(R.id.adde);
        Insert = (Button) findViewById(R.id.ins);
        Delete = (Button) findViewById(R.id.del);
        View = (Button) findViewById(R.id.vie);
        Sync = (Button) findViewById(R.id.syn);
        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        View.setOnClickListener(this);
        Sync.setOnClickListener(this);


        db = openOrCreateDatabase("FacultyDB", Context.MODE_PRIVATE, null);
        db.execSQL("Create Table if not exists faculty54(id VARCHAR,address VARCHAR);");


    }

    @Override
    public void onLocationChanged(Location location) {
        String msg = "Lat: " + location.getLatitude() + "\nLong: " + location.getLongitude();
        address.setText(msg);
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "GPS is turned on!!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "GPS is turned off!!!",
                Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        if (view == Sync){
            LocationManager locationManager;
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
        }
        if (view == Insert) {
            if(id.getText().toString().trim().length()==0 || address.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values (To add address click on location)");
                return;
            }
            db.execSQL("INSERT INTO faculty54 VALUES('"+id.getText()+"','"+address.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==Delete)
        {
            if(id.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter ID");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM faculty54 WHERE id ='"+id.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("DELETE FROM faculty54 WHERE id ='"+id.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else {
                showMessage("Error", "Invalid ID");
            }
            clearText();
        }

        if(view==View) {
            if(id.getText().toString().trim().length()==0 && address.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter ID or Address");
                return;
            }
            if(id.getText().toString().trim().length()!=0) {
                Cursor c = db.rawQuery("SELECT * FROM faculty54 WHERE id='" + id.getText() + "'", null);
                if (c.moveToFirst()) {
                    showMessage("Details", "ID: " + c.getString(0) + "\nAddress: " + c.getString(1));
                } else {
                    showMessage("Error", "Invalid ID");
                    clearText();
                }
            }
            else if(address.getText().toString().trim().length()!=0) {
                Cursor c = db.rawQuery("SELECT * FROM faculty54 WHERE address='" + address.getText() + "'", null);
                if (c.moveToFirst()) {
                    showMessage("Details", "ID: " + c.getString(0) + "\nAddress: " + c.getString(1));
                } else {
                    showMessage("Error", "Invalid address");
                    clearText();
                }
            }
        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        id.setText("");
        address.setText("");
        id.requestFocus();
    }
}

