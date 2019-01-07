package com.example.aubin.lights;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.divyanshu.colorseekbar.ColorSeekBar;

import java.util.concurrent.TimeUnit;

//import android.support.v7.app.AppCompatActivity;

public class ContextManagementActivity extends AppCompatActivity {

    private String room;

    //handles all the http request
    LightContextHttpManager RoomContextHttpManager = new LightContextHttpManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_management);


        //BUTTON to retreive the state of a specific light
        ((Button) findViewById(R.id.buttonCheck)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();
                RoomContextHttpManager.retrieveLightContextState(light);
            }
        });

        //Button to switch the selected light
        ((Button) findViewById(R.id.buttonSwitchLight)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();
                RoomContextHttpManager.switchLight(light);
            }
        });

        //button to delete the selected light
        ((Button) findViewById(R.id.buttonDeleteLight)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();
                RoomContextHttpManager.deleteLight(light);
            }
        });

        //listener when we change the value of the colorbar
        ColorSeekBar colorSeekBar = (ColorSeekBar) findViewById(R.id.color_seek_bar);
        //colorSeekBar.getColor();
        colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int i) {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();
                String color = Integer.toHexString(i).substring(2);
                RoomContextHttpManager.Changecolor(light, color);
                //((TextView) findViewById(R.id.textViewLightValue)).setText(String.valueOf(colorSeekBar.getColor()));
                ((TextView) findViewById(R.id.colorviewer)).setBackgroundColor(colorSeekBar.getColor());
            }

        });



    }




    public void onDeleteLight() {

        ((TextView) findViewById(R.id.textViewLightValue)).setText("");
        ((TextView) findViewById(R.id.textViewNoiseValue)).setText("");

        ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_bulb_off);



    }


    public void onUpdateLight(LightContextState context) {

        ((TextView) findViewById(R.id.textViewLightValue)).setText(String.valueOf(context.getLevel()));

        ((TextView) findViewById(R.id.textViewNoiseValue)).setText(String.valueOf(context.getRoomId()));




        if (context.getStatus().equals("ON")) {
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_bulb_on);
        } else {
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_bulb_off);
        }

    }


    //transforming the hex value to something understandable for the color picker
    public void testcolor(String color) {
        String bip = color;
        int col = Integer.parseInt(("f88fff"),16);
        findViewById(R.id.colorviewer).setBackgroundColor(-col);
        ((TextView) findViewById(R.id.textViewNoiseValue)).setText(bip);

    }



/*

    private String colorHex(int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
    }
*/
}
