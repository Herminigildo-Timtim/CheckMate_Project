package com.example.checkmate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView bottomNavigationView;

    Button btnRedTheme, btnBlueTheme,btnGreenTheme,btnDefaultTheme;
    Button btnGoal,btnBack,btnEvent,btnHobby;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch switcher;

    boolean night, redTheme, blueTheme, greenTheme,defaultTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btnGoal = (Button) findViewById(R.id.add);
        btnEvent = (Button) findViewById(R.id.add2);
        btnHobby = (Button) findViewById(R.id.add3);
        btnBack = (Button)findViewById(R.id.button22);


        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        // Set the OnClickListener for the btnAdd button
        btnGoal.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnHobby.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        btnRedTheme = findViewById(R.id.button21);
        btnRedTheme.setOnClickListener(this);

        btnBlueTheme = findViewById(R.id.button20);
        btnBlueTheme.setOnClickListener(this);

        btnGreenTheme = findViewById(R.id.button19);
        btnDefaultTheme = findViewById(R.id.button18);
        btnGreenTheme.setOnClickListener(this);
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
        switch(view.getId()){
            case R.id.add:
            case R.id.add2:
            case R.id.add3:
                Toast.makeText(Home.this, "Add note is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, AddNotes.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button22:
                Toast.makeText(Home.this, "Clicked back", Toast.LENGTH_SHORT).show();
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

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        button3.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button5.setBackgroundColor(getResources().getColor(R.color.red, null));
        button6.setBackgroundColor(getResources().getColor(R.color.red, null));
        button7.setBackgroundColor(getResources().getColor(R.color.red, null));

        switch1.setTextColor(getResources().getColor(R.color.red, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
        button7.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyBlueTheme(){

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button3.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button4.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button5.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button6.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button7.setBackgroundColor(getResources().getColor(R.color.skyblue, null));

        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
        button7.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyGreenTheme(){

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.green, null));
        button2.setBackgroundColor(getResources().getColor(R.color.green, null));
        button3.setBackgroundColor(getResources().getColor(R.color.green, null));
        button4.setBackgroundColor(getResources().getColor(R.color.green, null));
        button5.setBackgroundColor(getResources().getColor(R.color.green, null));
        button6.setBackgroundColor(getResources().getColor(R.color.green, null));
        button7.setBackgroundColor(getResources().getColor(R.color.green, null));

        switch1.setTextColor(getResources().getColor(R.color.green, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
        button7.setTextColor(getResources().getColor(R.color.white, null));
    }

    private void resetToDefaultTheme (){

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Switch switch1 = findViewById(R.id.switch3);


        if(night){

            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button3.setTextAppearance(this, R.style.BUTTON);
            button4.setTextAppearance(this, R.style.BUTTON);
            button5.setTextAppearance(this, R.style.BUTTON);
            button6.setTextAppearance(this, R.style.BUTTON);
            button7.setTextAppearance(this, R.style.BUTTON);

            button1.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button3.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button4.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button5.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button6.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button7.setBackgroundColor(getResources().getColor(android.R.color.white, null));

            switch1.setTextColor(getResources().getColor(android.R.color.white, null));

            button1.setTextColor(getResources().getColor(R.color.black, null));
            button2.setTextColor(getResources().getColor(R.color.black, null));
            button3.setTextColor(getResources().getColor(R.color.black, null));
            button4.setTextColor(getResources().getColor(R.color.black, null));
            button5.setTextColor(getResources().getColor(R.color.black, null));
            button6.setTextColor(getResources().getColor(R.color.black, null));
            button7.setTextColor(getResources().getColor(R.color.black, null));
        }else{

            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button3.setTextAppearance(this, R.style.BUTTON);
            button4.setTextAppearance(this, R.style.BUTTON);
            button5.setTextAppearance(this, R.style.BUTTON);
            button6.setTextAppearance(this, R.style.BUTTON);
            button7.setTextAppearance(this, R.style.BUTTON);

            button1.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button3.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button4.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button5.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button6.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button7.setBackgroundColor(getResources().getColor(android.R.color.black, null));

            switch1.setTextColor(getResources().getColor(android.R.color.black, null));

            button1.setTextColor(getResources().getColor(R.color.white, null));
            button2.setTextColor(getResources().getColor(R.color.white, null));
            button3.setTextColor(getResources().getColor(R.color.white, null));
            button4.setTextColor(getResources().getColor(R.color.white, null));
            button5.setTextColor(getResources().getColor(R.color.white, null));
            button6.setTextColor(getResources().getColor(R.color.white, null));
            button7.setTextColor(getResources().getColor(R.color.white, null));
        }
    }
}