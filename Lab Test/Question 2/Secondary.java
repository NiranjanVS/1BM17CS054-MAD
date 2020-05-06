package com.example.graphicsuser;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;


public class Secondary extends AppCompatActivity {
    TextView name,usn,dept;
    String shape,color,filled;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        Bitmap bg = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setBackgroundDrawable(new BitmapDrawable(bg));
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();
        Intent itv = getIntent();
        shape = itv.getStringExtra("shape_key");
        color = itv.getStringExtra("color_key");
        filled = itv.getStringExtra("fil_key");

        if(color.toString().equals("red"))
            paint.setColor(Color.RED);
        else if(color.toString().equals("blue"))
            paint.setColor(Color.BLUE);
        else
            paint.setColor(Color.GREEN);
        if(filled.toString().equals("Yes"))
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        else
            paint.setStyle(Paint.Style.STROKE);
        if(shape.toString().equals("circle"))
            canvas.drawCircle(500,500,100,paint);
        else if (shape.toString().equals("oval"))
            canvas.drawOval(200,200,300,400,paint);
        else if (shape.toString().equals("rectangle"))
            canvas.drawRect(200,200,400,600,paint);
        else {
            int x = 300;
            int y = 300;
            int width = 200;
            int height = 200;
            Point p1 = new Point(x,y);
            int pointX = x + width/2;
            int pointY = y - height;
            Point p2 = new Point(pointX,pointY);
            Point p3 = new Point(x+width,y);
            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(p1.x,p1.y);
            path.lineTo(p2.x,p2.y);
            path.lineTo(p3.x,p3.y);
            path.close();
            canvas.drawPath(path, paint);
        }
    }
}
