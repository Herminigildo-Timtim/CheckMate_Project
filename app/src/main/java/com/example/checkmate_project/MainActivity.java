package com.example.checkmate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignUp,btnRed,btnBlue,btnGreen,btnDefault;
    Switch switcher;
    boolean night;
    boolean redTheme;
    boolean blueTheme;
    boolean greenTheme;
    boolean defaultTheme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSignUp = (Button)findViewById(R.id.button7);
        btnRed = (Button)findViewById(R.id.button4);
        btnBlue = (Button)findViewById(R.id.button6);
        btnGreen = (Button)findViewById(R.id.button5);
        btnDefault = (Button)findViewById(R.id.button3);
        btnSignUp.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnDefault.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String password = sharedPreferences.getString("password", "");

        getSupportActionBar().hide();
        switcher = findViewById(R.id.switch1);
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        //To save mode after exiting from the program
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        //Light mode is the default mode
        blueTheme = sharedPreferences.getBoolean("blue_theme", false);
        redTheme = sharedPreferences.getBoolean("red_theme", false);
        greenTheme = sharedPreferences.getBoolean("green_theme", false);
        defaultTheme = sharedPreferences.getBoolean("default_theme", false);
        night = sharedPreferences.getBoolean("night",false);
        switcher.setChecked(night);

        // Check and apply the stored themes
        if (redTheme) {
            applyRedTheme();
        } else if (blueTheme) {
            applyBlueTheme();
        }else if(greenTheme){
            applyGreenTheme();
        }else{
            resetToDefaultTheme();
        }

        Drawable drawable,drawable1;

        if(night){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editText.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword.setBackgroundResource(R.drawable.edt_backgroundwhite);
            drawable = getResources().getDrawable(R.drawable.baseline_person_24_white,null);
            drawable1 = getResources().getDrawable(R.drawable.baseline_key_24_white,null);
            switcher.setText("Dark");
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable1.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editText.setCompoundDrawables(drawable, null, null, null);
            editTextPassword.setCompoundDrawables(drawable1,null,null,null);

        }
        if (redTheme) {
            applyRedTheme();
        } else if (blueTheme) {
            applyBlueTheme();
        }else if(greenTheme){
            applyGreenTheme();
        }else{
            resetToDefaultTheme();
        }




        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable,drawable1;
                if(night){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",false);
                    drawable = getResources().getDrawable(R.drawable.baseline_person_24,null);
                    drawable1 = getResources().getDrawable(R.drawable.baseline_key_24,null);
                    editText.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword.setBackgroundResource(R.drawable.edt_background);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night",true);
                    drawable = getResources().getDrawable(R.drawable.baseline_person_24_white,null);
                    drawable1 = getResources().getDrawable(R.drawable.baseline_key_24_white,null);
                    switcher.setText("Dark");
                    editText.setBackgroundResource(R.drawable.edt_backgroundwhite);
                    editTextPassword.setBackgroundResource(R.drawable.edt_backgroundwhite);

                    }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
                editText.setCompoundDrawables(drawable, null, null, null);
                editTextPassword.setCompoundDrawables(drawable1, null, null, null);
                editor.putBoolean("switch_state", switcher.isChecked());
                editor.apply();

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button7:
                Toast.makeText(MainActivity.this, "Sign Up button is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Register.class);
                intent.putExtra("switch_state", switcher.isChecked());
                intent.putExtra("red_theme", redTheme);
                intent.putExtra("blue_theme", blueTheme);
                intent.putExtra("green_theme", greenTheme);
                intent.putExtra("default_theme", defaultTheme);
                // Add this line to pass the red theme state
                startActivityForResult(intent, 1);
                break;
            case R.id.button4:

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

            case R.id.button6:
                applyBlueTheme();
                blueTheme = true;
                redTheme = false;
                greenTheme = false;
                defaultTheme = false;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("default_theme", defaultTheme);
                editor.apply();
                break;
            case R.id.button5:
                applyGreenTheme();
                blueTheme = false;
                redTheme = false;
                greenTheme = true;
                defaultTheme = false;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("default_theme", defaultTheme);
                editor.apply();
                break;
            case R.id.button3:
                resetToDefaultTheme();
                blueTheme = false;
                redTheme = false;
                greenTheme = false;
                defaultTheme = true;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.putBoolean("blue_theme", blueTheme);
                editor.putBoolean("green_theme", greenTheme);
                editor.putBoolean("white_theme", defaultTheme);
                editor.apply();
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean switchState = data.getBooleanExtra("switch_state", false);
                switcher.setChecked(switchState);

                // Receive the theme state from Register
                redTheme = data.getBooleanExtra("red_theme", false);
                blueTheme = data.getBooleanExtra("blue_theme", false);
                greenTheme = data.getBooleanExtra("green_theme", false);
                defaultTheme = data.getBooleanExtra("default_theme", false);

                String name = data.getStringExtra("name");
                String password = data.getStringExtra("password");

                // Print the name and password in the EditText fields
                EditText editTextName = findViewById(R.id.editText);
                EditText editTextPassword = findViewById(R.id.editTextTextPassword);
                editTextName.setText(name);
                editTextPassword.setText(password);


                // Apply the theme based on the received values
                if (redTheme) {
                    applyRedTheme();
                } else if (blueTheme) {
                    applyBlueTheme();
                }else if(greenTheme){
                    applyGreenTheme();
                }else{
                    resetToDefaultTheme();
                }
            }
        }
    }
    private void applyRedTheme() {
        // Your red theme application code here
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        final Button button1 = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button7);
        final Switch switch1 = findViewById(R.id.switch1);


        Drawable drawable, drawable1;

        editText.setBackgroundResource(R.drawable.edt_backgroundred); // Create a new drawable for the red background
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundred); // Create a new drawable for the red background

        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        switch1.setTextColor(getResources().getColor(R.color.red, null));
        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyBlueTheme(){
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        final Button button1 = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button7);
        final Switch switch1 = findViewById(R.id.switch1);


        Drawable drawable, drawable1;

        editText.setBackgroundResource(R.drawable.edt_backgroundblue); // Create a new drawable for the red background
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundblue); // Create a new drawable for the red background
        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));
        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));

    }
    private void applyGreenTheme() {
        // Your red theme application code here
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        final Button button1 = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button7);
        final Switch switch1 = findViewById(R.id.switch1);


        Drawable drawable, drawable1;

        editText.setBackgroundResource(R.drawable.edt_backgroundgreen); // Create a new drawable for the red background
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundgreen); // Create a new drawable for the red background

        button1.setBackgroundColor(getResources().getColor(R.color.green, null));
        button2.setBackgroundColor(getResources().getColor(R.color.green, null));
        switch1.setTextColor(getResources().getColor(R.color.green, null));
        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
    }

    private void resetToDefaultTheme() {
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        final Button button1 = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button7);
        final Switch switch1 = findViewById(R.id.switch1);


        if (night) {
            editText.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword.setBackgroundResource(R.drawable.edt_backgroundwhite);
            switch1.setTextColor(getResources().getColor(android.R.color.white, null));
            button1.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button1.setTextColor(getResources().getColor(R.color.black, null));
            button2.setTextColor(getResources().getColor(R.color.black, null));
        } else {
            editText.setBackgroundResource(R.drawable.edt_background);
            editTextPassword.setBackgroundResource(R.drawable.edt_background);
            switch1.setTextColor(getResources().getColor(android.R.color.black, null));
            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);

            button1.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button1.setTextColor(getResources().getColor(R.color.white, null));
            button2.setTextColor(getResources().getColor(R.color.white, null));
        }
    }



}