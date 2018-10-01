package com.example.nirajmali.facemaker_mali21;

/**
 * @author: Niraj Mali
 *
 * Version: 9/30/2018
 *
 * Face: Creates qualities for face and has draw
 * methods to be called to draw the face
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class Face{

    //Variables to determine features of face
    int skinColor;
    int eyeColor;
    int hairColor;
    int hairStyle;

    //Implementing paint for color and Random for random facial features
    Paint paint;
    Random random;

    //Constructor: Initially, it just needs a random face so we call randomize() method
    public Face(){
        randomize();
    }

    //Creates a random face
    protected void randomize() {
    /**
     External Citation
     Date: 28 September 2018
     Problem: Could not figure out how to get a random int in certain range
     Resource:
     https://stackoverflow.com/questions/27976857/how-
     to-get-random-number-with-negative-number-in-range
     /27977067
     Solution: I copied and paste and replaced with values I could use
    */
        random = new Random();
        this.skinColor = (int) random.nextInt(Color.WHITE + 1 - Color.BLACK) + Color.BLACK;
        this.eyeColor = (int) random.nextInt(Color.WHITE + 1 - Color.BLACK) + Color.BLACK;
        this.hairColor = (int) random.nextInt(Color.WHITE + 1 - Color.BLACK) + Color.BLACK;
        this.hairStyle = (int) random.nextInt(3 + 1 - 0) + 0;
    }

    //Set hair style
    protected void setHairStyle(int selection){
        hairStyle = selection;
    }

    //Set color values given rgb values
    protected void setHairColor(int red, int green, int blue){
        hairColor = Color.rgb(red, green, blue);
    }
    protected void setEyeColor(int red, int green, int blue){
        eyeColor = Color.rgb(red, green, blue);
    }
    protected void setSkinColor(int red, int green, int blue){
        skinColor = Color.rgb(red, green, blue);
    }

    /**
     External Citation
     Date: 14 September 2015
     Problem: Needed way to grab rgb values from a single int value
     to change
     Resource:
     https://stackoverflow.com/questions/23323183/
     how-to-get-rgb-values-from-the-paint-object
     Solution: Used methods given in the link
     */
    //Return r, g, or b values
    protected int getRed(int colorVal){
        return Color.red(colorVal);
    }
    protected int getGreen(int colorVal){
        return Color.green(colorVal);
    }
    protected int getBlue(int colorVal){
        return Color.blue(colorVal);
    }

    //Main draw method
    protected void onDraw(Canvas canvas){
        paint = new Paint();

        Log.i("Face", "" + hairStyle);

        if(hairStyle == 3){
            paint.setColor(skinColor);
            drawFace(canvas, paint);

            paint.setColor(hairColor);
            drawHair(canvas, paint);
        }

        else {
            paint.setColor(hairColor);
            drawHair(canvas, paint);

            paint.setColor(skinColor);
            drawFace(canvas, paint);
        }
        paint.setColor(eyeColor);
        drawEyes(canvas, paint);

    }

    //Draws face
    protected void drawFace(Canvas canvas, Paint paint){
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/8, paint);
    }

    //Draws hair
    protected void drawHair(Canvas canvas, Paint paint){

        switch(this.hairStyle){

            //Square
            case 0:
                canvas.drawRect(canvas.getWidth()/2 - canvas.getWidth()/9, canvas.getHeight()/8 - 50,
                        canvas.getWidth()/2 + canvas.getWidth()/9, canvas.getHeight()/2, paint);
                break;
            //Afro
            case 1:
                canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2 - canvas.getHeight()/8,
                        canvas.getWidth()/7, paint);
                break;
            //Bald
            case 2:
                break;
            //Hat
            case 3:
                canvas.drawOval(canvas.getWidth()/2 - canvas.getWidth()/8, canvas.getHeight()/2 - canvas.getHeight()/3,
                        canvas.getWidth()/2 + canvas.getWidth()/8, canvas.getHeight()/2 - canvas.getHeight()/9, paint);
                break;
        }
    }

    //Draws eyes
    protected void drawEyes(Canvas canvas, Paint paint){
        canvas.drawCircle(canvas.getWidth()/2 - canvas.getWidth()/14, canvas.getHeight()/2 - 20, canvas.getWidth()/30, paint);
        canvas.drawCircle(canvas.getWidth()/2 + canvas.getWidth()/14, canvas.getHeight()/2 - 20, canvas.getWidth()/30, paint);
    }



}
