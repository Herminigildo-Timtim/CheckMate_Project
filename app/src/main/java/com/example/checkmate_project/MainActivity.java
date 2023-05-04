package com.example.checkmate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignUp,btn2,btn3,btn4,btn5;
    Switch switcher;
    boolean night;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSignUp = (Button)findViewById(R.id.button7);
        btnSignUp.setOnClickListener(this);

        getSupportActionBar().hide();
        switcher = findViewById(R.id.switch1);
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        //To save mode after exiting from the program
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        //Light mode is the default mode
        night = sharedPreferences.getBoolean("night",false);

        if(night){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editText.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword.setBackgroundResource(R.drawable.edt_backgroundwhite);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(night){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",false);
                    editText.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword.setBackgroundResource(R.drawable.edt_background);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",true);
                    editText.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword.setBackgroundResource(R.drawable.edt_background);
                }
                editor.apply();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button7:
                Toast.makeText(MainActivity.this,"Sign Up button is clicked!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }
}