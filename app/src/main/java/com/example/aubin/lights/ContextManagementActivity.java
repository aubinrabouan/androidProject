package com.example.aubin.lights;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ContextManagementActivity extends AppCompatActivity {

    private String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_management);
    }

    public void onClick(View v) {
            room = ((EditText) findViewById(R.id.editText1))
                    .getText().toString();
            retrieveRoomContextState(room);
        }

    private void retrieveRoomContextState(String room) {
    }



}
