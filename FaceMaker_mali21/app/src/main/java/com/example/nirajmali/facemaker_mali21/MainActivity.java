package com.example.nirajmali.facemaker_mali21;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends AppCompatActivity {

    Canvas canvas;
    FaceView fv;

    RadioButton hairRad;
    RadioButton eyesRad;
    RadioButton skinRad;

    Spinner hairPick;

    SeekBar redSlider;
    SeekBar greenSlider;
    SeekBar blueSlider;

    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize and define items
        canvas = new Canvas();
        fv = (FaceView) findViewById(R.id.faceView);

        seekBarListener sbl = new seekBarListener();


        //Sliders
        redSlider = (SeekBar) findViewById(R.id.redSlider);
        redSlider.setMax(255);
        redSlider.setProgress(fv.face.getRed(fv.face.hairColor));
        redSlider.setOnSeekBarChangeListener(sbl);

        greenSlider = (SeekBar) findViewById(R.id.greenSlider);
        greenSlider.setMax(255);
        greenSlider.setProgress(fv.face.getGreen(fv.face.hairColor));
        greenSlider.setOnSeekBarChangeListener(sbl);

        blueSlider = (SeekBar) findViewById(R.id.blueSlider);
        blueSlider.setMax(255);
        greenSlider.setProgress(fv.face.getGreen(fv.face.hairColor));
        blueSlider.setOnSeekBarChangeListener(sbl);

        //Text Views
        TextView redNum = (TextView) findViewById(R.id.redText);
        TextView greenNum = (TextView) findViewById(R.id.greenText);
        TextView blueNum = (TextView) findViewById(R.id.blueText);

        //Spinner
        hairPick = (Spinner) findViewById(R.id.hairSty);
        hairPick.setSelection(fv.face.hairStyle);
        hairPick.setOnItemSelectedListener(new SpinnerListener());

        //Radio Group/Buttons
        hairRad = (RadioButton) findViewById(R.id.hairButton);
        eyesRad = (RadioButton) findViewById(R.id.eyesButton);
        skinRad = (RadioButton) findViewById(R.id.skinButton);
        RadioGroupListener rgl = new RadioGroupListener();
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.check(hairRad.getId());
        rg.setOnCheckedChangeListener(rgl);

        Button randFace = (Button) findViewById(R.id.randomFace);
        randFace.setOnClickListener(new ButtonListener());

    }

    private class seekBarListener implements SeekBar.OnSeekBarChangeListener {

        /**
         External Citation
         Date: 29 September 2018
         Problem: Couldn't remember how to implement a listener for the SeekBar
         Resource:
         https://developer.android.com/reference/
         android/widget/SeekBar.OnSeekBarChangeListener
         Solution: Copied methods and class; changed functionality
         */

        public void onProgressChanged(SeekBar seek, int progress, boolean fromUser) {
            if(hairRad.isChecked()){
                if(seek.equals(findViewById(R.id.redSlider))){
                    fv.face.setHairColor(progress, fv.face.getGreen(fv.face.hairColor), fv.face.getBlue(fv.face.hairColor));
                }
                else if(seek.equals(findViewById(R.id.greenSlider))){
                    fv.face.setHairColor(fv.face.getRed(fv.face.hairColor), progress, fv.face.getBlue(fv.face.hairColor));
                }
                else//(seek.equals(findViewById(R.id.blueSlider))){
                {    fv.face.setHairColor(fv.face.getRed(fv.face.hairColor), fv.face.getGreen(fv.face.hairColor), progress);
                }
            }
            if(eyesRad.isChecked()){
                if(seek.equals(findViewById(R.id.redSlider))){
                    fv.face.setEyeColor(progress, fv.face.getGreen(fv.face.eyeColor), fv.face.getBlue(fv.face.eyeColor));
                }
                if(seek.equals(findViewById(R.id.greenSlider))){
                    fv.face.setEyeColor(fv.face.getRed(fv.face.eyeColor), progress, fv.face.getBlue(fv.face.eyeColor));
                }
                if(seek.equals(findViewById(R.id.blueSlider))){
                    fv.face.setEyeColor(fv.face.getRed(fv.face.eyeColor), fv.face.getGreen(fv.face.eyeColor), progress);
                }
            }
            if(skinRad.isChecked()){
                if(seek.equals(findViewById(R.id.redSlider))){
                    fv.face.setSkinColor(progress, fv.face.getGreen(fv.face.skinColor), fv.face.getBlue(fv.face.skinColor));
                }
                if(seek.equals(findViewById(R.id.greenSlider))){
                    fv.face.setSkinColor(fv.face.getRed(fv.face.skinColor), progress, fv.face.getBlue(fv.face.skinColor));
                }
                if(seek.equals(findViewById(R.id.blueSlider))){
                    fv.face.setSkinColor(fv.face.getRed(fv.face.skinColor), fv.face.getGreen(fv.face.skinColor), progress);
                }
            }
            fv.invalidate();
        }

        public void onStartTrackingTouch(SeekBar seek) {

        }

        public void onStopTrackingTouch(SeekBar seek) {

        }
    }

    /**
     External Citation
     Date: 29 September 2019
     Problem: Difficulty in implementing the Spinner listener
     Resource:
     https://developer.android.com/guide/topics/ui/
     controls/spinner#java
     Solution: Copied into code and adjusted method body
     */
    //Check what user has chosen in Spinner
    public class SpinnerListener extends Activity implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            Log.i("Spinner", "" + pos + "");
            fv.face.setHairStyle(pos);
            fv.invalidate();

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    public class ButtonListener extends Activity implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            fv.face.randomize();
            hairPick.setSelection(fv.face.hairStyle);
            redSlider.setProgress(fv.face.getRed(fv.face.hairColor));
            greenSlider.setProgress(fv.face.getGreen(fv.face.hairColor));
            blueSlider.setProgress(fv.face.getBlue(fv.face.hairColor));
            fv.invalidate();
            Log.i("Inval: ", "ButtonListener");
        }
    }

    /**
     External Citation
     Date: 30 September 2018
     Problem: Needed reference for implementing a Radio group listener
     Resource:
     https://developer.android.com/reference/android/widget/
     RadioGroup.OnCheckedChangeListener
     Solution: Implemented RadioGroup.OnCheckedChangeListener and
     adjusted methods accordingly
     */
    //Check if selected Radio Button has changed
    public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{
        public void onCheckedChanged(RadioGroup group, int checkedId){
            if(checkedId == hairRad.getId()){
                redSlider.setProgress(fv.face.getRed(fv.face.hairColor));
                greenSlider.setProgress(fv.face.getGreen(fv.face.hairColor));
                blueSlider.setProgress(fv.face.getBlue(fv.face.hairColor));
            }
            if(checkedId == eyesRad.getId()){
                Log.i("Radio: ", "Eyes");
                redSlider.setProgress(fv.face.getRed(fv.face.eyeColor));
                greenSlider.setProgress(fv.face.getGreen(fv.face.eyeColor));
                blueSlider.setProgress(fv.face.getBlue(fv.face.eyeColor));
            }
            if(checkedId == skinRad.getId()){
                redSlider.setProgress(fv.face.getRed(fv.face.skinColor));
                greenSlider.setProgress(fv.face.getGreen(fv.face.skinColor));
                blueSlider.setProgress(fv.face.getBlue(fv.face.skinColor));
            }
        }
    }


}
