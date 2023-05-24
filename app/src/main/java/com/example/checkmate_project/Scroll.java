package com.example.checkmate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Scroll extends AppCompatActivity implements View.OnClickListener {


    Button btnRedTheme, btnBlueTheme,btnGreenTheme,btnDefaultTheme,btnBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch switcher;

    boolean night, redTheme, blueTheme, greenTheme,defaultTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        getSupportActionBar().hide();

        btnBack = findViewById(R.id.button99);
        btnBack.setOnClickListener(this);
        btnRedTheme = findViewById(R.id.button21);
        btnRedTheme.setOnClickListener(this);

        btnBlueTheme = findViewById(R.id.button20);
        btnBlueTheme.setOnClickListener(this);

        btnGreenTheme = findViewById(R.id.button19);
        btnGreenTheme.setOnClickListener(this);

        btnDefaultTheme = findViewById(R.id.button18);
        btnDefaultTheme.setOnClickListener(this);

        // Find the switch view in the second activity
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        night = sharedPreferences.getBoolean("night", false);
        redTheme = sharedPreferences.getBoolean("red_theme", false);
        blueTheme = sharedPreferences.getBoolean("blue_theme", false);
        greenTheme = sharedPreferences.getBoolean("green_theme", false);
        defaultTheme = sharedPreferences.getBoolean("default_theme", false);
        switcher = findViewById(R.id.switch3);
        switcher.setChecked(night);

        if (night) {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switcher.setText("Dark");
        }

        if (redTheme) {
            applyRedTheme();
        }
        else if(blueTheme) {
            applyBlueTheme();
        }else if(greenTheme){
            applyGreenTheme();
        }else{
            resetToDefaultTheme();
        }


        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable, drawable1,drawable2,drawable3,drawable4;
                if (night) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.putBoolean("switch_state", switcher.isChecked());
                editor.apply();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button99:
                Toast.makeText(Scroll.this, "Clicked back", Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("switch_state", switcher.isChecked());
                resultIntent.putExtra("red_theme", redTheme);
                resultIntent.putExtra("blue_theme", blueTheme);
                resultIntent.putExtra("green_theme", greenTheme);
                resultIntent.putExtra("default_theme", defaultTheme);
                setResult(RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.button18:
                resetToDefaultTheme();
                blueTheme = false;
                redTheme = false;
                greenTheme = false;
                defaultTheme = true;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("default_theme", defaultTheme);
                editor.apply();
                break;
            case R.id.button19:
                applyBlueTheme();
                redTheme = false;
                blueTheme = true;
                greenTheme = false;
                defaultTheme = false;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("default_theme", defaultTheme);
                editor.apply();
                break;
            case R.id.button20:
                applyGreenTheme();
                redTheme = false;
                blueTheme = false;
                greenTheme = true;
                defaultTheme = false;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("default_theme", defaultTheme);
                editor.apply();
                break;
            case R.id.button21:
                applyRedTheme();
                redTheme = true;
                blueTheme = false;
                greenTheme = false;
                defaultTheme = false;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("default_theme", defaultTheme);
                editor.apply();
                break;
        }
    }
    private void applyRedTheme() {

        final Button button1 = findViewById(R.id.button99);
        final Button button2 = findViewById(R.id.button100);
        final Button button3 = findViewById(R.id.button101);
        final Button button4 = findViewById(R.id.button102);
        final Button button5 = findViewById(R.id.button103);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        button3.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button5.setBackgroundColor(getResources().getColor(R.color.red, null));


        switch1.setTextColor(getResources().getColor(R.color.red, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));

    }
    private void applyBlueTheme(){

        final Button button1 = findViewById(R.id.button99);
        final Button button2 = findViewById(R.id.button100);
        final Button button3 = findViewById(R.id.button101);
        final Button button4 = findViewById(R.id.button102);
        final Button button5 = findViewById(R.id.button103);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button3.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button4.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button5.setBackgroundColor(getResources().getColor(R.color.skyblue, null));

        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyGreenTheme(){

        final Button button1 = findViewById(R.id.button99);
        final Button button2 = findViewById(R.id.button100);
        final Button button3 = findViewById(R.id.button101);
        final Button button4 = findViewById(R.id.button102);
        final Button button5 = findViewById(R.id.button103);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.green, null));
        button2.setBackgroundColor(getResources().getColor(R.color.green, null));
        button3.setBackgroundColor(getResources().getColor(R.color.green, null));
        button4.setBackgroundColor(getResources().getColor(R.color.green, null));
        button5.setBackgroundColor(getResources().getColor(R.color.green, null));

        switch1.setTextColor(getResources().getColor(R.color.green, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));

    }

    private void resetToDefaultTheme (){

        final Button button1 = findViewById(R.id.button99);
        final Button button2 = findViewById(R.id.button100);
        final Button button3 = findViewById(R.id.button101);
        final Button button4 = findViewById(R.id.button102);
        final Button button5 = findViewById(R.id.button103);
        final Switch switch1 = findViewById(R.id.switch3);


        if(night){

            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button3.setTextAppearance(this, R.style.BUTTON);
            button4.setTextAppearance(this, R.style.BUTTON);
            button5.setTextAppearance(this, R.style.BUTTON);


            button1.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button3.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button4.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button5.setBackgroundColor(getResources().getColor(android.R.color.white, null));


            switch1.setTextColor(getResources().getColor(android.R.color.white, null));

            button1.setTextColor(getResources().getColor(R.color.black, null));
            button2.setTextColor(getResources().getColor(R.color.black, null));
            button3.setTextColor(getResources().getColor(R.color.black, null));
            button4.setTextColor(getResources().getColor(R.color.black, null));
            button5.setTextColor(getResources().getColor(R.color.black, null));
        }else{

            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button3.setTextAppearance(this, R.style.BUTTON);
            button4.setTextAppearance(this, R.style.BUTTON);
            button5.setTextAppearance(this, R.style.BUTTON);

            button1.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button3.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button4.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button5.setBackgroundColor(getResources().getColor(android.R.color.black, null));


            switch1.setTextColor(getResources().getColor(android.R.color.black, null));

            button1.setTextColor(getResources().getColor(R.color.white, null));
            button2.setTextColor(getResources().getColor(R.color.white, null));
            button3.setTextColor(getResources().getColor(R.color.white, null));
            button4.setTextColor(getResources().getColor(R.color.white, null));
            button5.setTextColor(getResources().getColor(R.color.white, null));
        }
    }

}