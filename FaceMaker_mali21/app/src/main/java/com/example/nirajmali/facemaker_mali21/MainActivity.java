package com.example.nirajmali.facemaker_mali21;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize and define items
        Canvas canvas = new Canvas();

        SeekBar redSlider = (SeekBar) findViewById(R.id.redSlider);
        //redSlider.setOnSeekBarChangeListener();
        SeekBar greenSlider = (SeekBar) findViewById(R.id.greenSlider);
        SeekBar blueSlider = (SeekBar) findViewById(R.id.blueSlider);

        TextView redNum = (TextView) findViewById(R.id.redText);
        TextView greenNum = (TextView) findViewById(R.id.greenText);
        TextView blueNum = (TextView) findViewById(R.id.blueText);

        Face face = new Face();

        Spinner hairPick = (Spinner) findViewById(R.id.hairSty);

        //Reference: http://www.java2s.com/Code/Android/UI/SpinnerItemSelectedListener.htm
        hairPick.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                face.setHairStyle(position);
                face.onDraw(Canvas);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        //Draw Face first
        face.onDraw(canvas);
    }

    private class seekBarListener implements SeekBar.OnSeekBarChangeListener {

        //Reference: https://developer.android.com/reference/android/widget/SeekBar.OnSeekBarChangeListener

        public void onProgressChanged(SeekBar seek, int progress, boolean fromUser) {
            int value = progress * 256;

        }

        public void onStartTrackingTouch(SeekBar seek) {

        }

        public void onStopTrackingTouch(SeekBar seek) {

        }
    }

    private class spinnerListener implements  {

    }
}
