package com.example.nirajmali.facemaker_mali21;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Face {
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;
    Paint paint;

    public Face(){
        randomize();
    }

    protected void randomize(){
        this.skinColor = (int) (Math.random() * 255);
        this.eyeColor = (int) (Math.random() * 255);
        this.hairColor = (int) (Math.random() * 255);
        this.hairStyle = (int) (Math.random() * 4);
    }

    protected void onDraw(Canvas canvas){
        paint = new Paint();
        paint.setColor(skinColor);

        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 50, paint);
    }

    protected void setHairStyle(int selection){
        hairStyle = selection;
    }
}
