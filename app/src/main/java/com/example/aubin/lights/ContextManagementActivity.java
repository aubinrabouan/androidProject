package com.example.aubin.lights;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.defaults.colorpicker.ColorPickerView;

public class ContextManagementActivity extends AppCompatActivity {

    private String room;

    @BindView(R.id.colorPicker) ColorPickerView colorPickerView;
    @BindView(R.id.colorHex) TextView colorHex;


    RoomContextHttpManager RoomContextHttpManager = new RoomContextHttpManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_management);
        ButterKnife.bind(this);

        colorPickerView.subscribe((color, fromUser) -> {
            colorHex.setText(colorHex(color));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(color);
            }
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setBackgroundDrawable(new ColorDrawable(color));
            }
        });

        ((Button) findViewById(R.id.buttonCheck)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();
                RoomContextHttpManager.retrieveLightContextState(light);
            }
        });


        ((Button) findViewById(R.id.buttonSwitchLight)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();
                RoomContextHttpManager.switchLight(light);
            }
        });
        ((Button) findViewById(R.id.buttonDeleteLight)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String light = ((EditText) findViewById(R.id.editText1)).getText().toString();;
                RoomContextHttpManager.deleteLight(light);
            }
        });



    }




    public void onDeleteLight() {

        ((TextView) findViewById(R.id.textViewLightValue)).setText("");
        ((TextView) findViewById(R.id.textViewNoiseValue)).setText("");

        ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_bulb_off);



    }


    public void onUpdateLight(RoomContextState context) {

        ((TextView) findViewById(R.id.textViewLightValue)).setText(String.valueOf(context.getLevel()));
        ((TextView) findViewById(R.id.textViewNoiseValue)).setText(String.valueOf(context.getRoomId()));

        if (context.getStatus().equals("ON")) {
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_bulb_on);
        } else {
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_bulb_off);
        }

    }





    private String colorHex(int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
    }

}
