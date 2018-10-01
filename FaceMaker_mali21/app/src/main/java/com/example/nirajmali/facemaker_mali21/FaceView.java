package com.example.nirajmali.facemaker_mali21;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 External Citation
 Date: 28 September 2018
 Problem: Needed a reference for using SurfaceView implementation
 Resource:
 https://developer.android.com/reference/android/
 view/SurfaceView
 Solution: Referred to source and copied needed methods and adjusted
 based on what I needed
 */
//Creating SurfaceView subclass to draw face on canvas

public class FaceView extends SurfaceView {

    Face face;

    public FaceView(Context context, AttributeSet attrs){
        super(context, attrs);
        face = new Face();
    }

    protected void onDraw(Canvas canvas){
        face.onDraw(canvas);
    }

}
