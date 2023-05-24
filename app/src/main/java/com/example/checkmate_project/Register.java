package com.example.checkmate_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button btnBack, btnRedTheme, btnBlueTheme,btnGreenTheme,btnDefaultTheme, btnCalendar , btnSignup;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch switcher;
    boolean night, redTheme, blueTheme, greenTheme,defaultTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        btnBack = findViewById(R.id.button2);
        btnBack.setOnClickListener(this);

        btnSignup = findViewById(R.id.button14);
        btnSignup.setOnClickListener(this);

        btnCalendar = findViewById(R.id.button8);
        btnCalendar.setOnClickListener(this);

        btnRedTheme = findViewById(R.id.button10);
        btnRedTheme.setOnClickListener(this);

        btnBlueTheme = findViewById(R.id.button11);
        btnBlueTheme.setOnClickListener(this);

        btnGreenTheme = findViewById(R.id.button9);
        btnDefaultTheme = findViewById(R.id.button12);
        btnGreenTheme.setOnClickListener(this);
        btnDefaultTheme.setOnClickListener(this);

        // Find the switch view in the second activity
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        night = sharedPreferences.getBoolean("night", false);
        redTheme = sharedPreferences.getBoolean("red_theme", false);
        blueTheme = sharedPreferences.getBoolean("blue_theme", false);
        greenTheme = sharedPreferences.getBoolean("green_theme", false);
        defaultTheme = sharedPreferences.getBoolean("default_theme", false);
        switcher = findViewById(R.id.switch2);
        switcher.setChecked(night);



        // Retrieve the saved state of the switch button



        final EditText editText = findViewById(R.id.editText2);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        final EditText editText2 = findViewById(R.id.editText3);
        final EditText editTextPassword2 = findViewById(R.id.editTextTextPassword3);
        final EditText editText3 = findViewById(R.id.editText4);

        Drawable drawable, drawable1,drawable2,drawable3,drawable4;


        if (night) {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editText.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editText2.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword2.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editText3.setBackgroundResource(R.drawable.edt_backgroundwhite);
            drawable = getResources().getDrawable(R.drawable.baseline_person_24_white, null);
            drawable1 = getResources().getDrawable(R.drawable.baseline_key_24_white, null);
            drawable2 = getResources().getDrawable(R.drawable.baseline_mark_email_read_24_white, null);
            drawable3 = getResources().getDrawable(R.drawable.baseline_check_circle_24_white, null);
            drawable4 = getResources().getDrawable(R.drawable.baseline_date_range_24_white,null);
            switcher.setText("Dark");
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable1.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable2.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable3.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable4.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            editText.setCompoundDrawables(drawable, null, null, null);
            editTextPassword.setCompoundDrawables(drawable1, null, null, null);
            editText2.setCompoundDrawables(drawable2, null, null, null);
            editTextPassword2.setCompoundDrawables(drawable3, null, null, null);
            editText3.setCompoundDrawables(drawable4,null,null,null);
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
                    drawable = getResources().getDrawable(R.drawable.baseline_person_24, null);
                    drawable1 = getResources().getDrawable(R.drawable.baseline_key_24, null);
                    drawable2 = getResources().getDrawable(R.drawable.baseline_mark_email_read_24, null);
                    drawable3 = getResources().getDrawable(R.drawable.baseline_check_circle_24, null);
                    drawable4 = getResources().getDrawable(R.drawable.baseline_date_range_24,null);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                    drawable = getResources().getDrawable(R.drawable.baseline_person_24_white, null);
                    drawable1 = getResources().getDrawable(R.drawable.baseline_key_24_white, null);
                    drawable2 = getResources().getDrawable(R.drawable.baseline_mark_email_read_24_white, null);
                    drawable3 = getResources().getDrawable(R.drawable.baseline_check_circle_24_white, null);
                    drawable4 = getResources().getDrawable(R.drawable.baseline_date_range_24_white,null);
                    switcher.setText("Dark");
                    editText.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword.setBackgroundResource(R.drawable.edt_background);
                    editText2.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword2.setBackgroundResource(R.drawable.edt_background);
                    editText3.setBackgroundResource(R.drawable.edt_background);
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
                drawable2.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable3.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
                drawable4.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
                editText.setCompoundDrawables(drawable, null, null, null);
                editTextPassword.setCompoundDrawables(drawable1, null, null, null);
                editText2.setCompoundDrawables(drawable2, null, null, null);
                editTextPassword2.setCompoundDrawables(drawable3, null, null, null);
                editText3.setCompoundDrawables(drawable4, null, null, null);

                editor.putBoolean("switch_state", switcher.isChecked());
                editor.apply();
            }
        });



        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date to set as the default date in the DatePickerDialog
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // Create a new DatePickerDialog instance with the current date as default date
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the chosen date in the EditText
                                String date = (month + 1) + " / " + dayOfMonth + " / " + year;
                                editText3.setText(date);
                            }
                        }, year, month, day);

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        // Get the name and password from the EditText fields
                        String name = editText.getText().toString().trim();
                        String password = editTextPassword.getText().toString().trim();
                        String confirm = editTextPassword2.getText().toString().trim();
                        String email = editText2.getText().toString().trim();
                        String birth = editText3.getText().toString().trim();

                        // Check if name and password are valid
                        if (name.isEmpty() || password.isEmpty() || confirm.isEmpty() || email.isEmpty() || birth.isEmpty()) {
                            Toast.makeText(Register.this, "Please fill all required inputs", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name", name);
                            editor.putString("password", password);
                            editor.apply();

                            // Pass the name and password to the main activity
                            Intent intent = new Intent();
                            intent.putExtra("name", name);
                            intent.putExtra("password", password);
                            intent.putExtra("switch_state", switcher.isChecked());
                            intent.putExtra("red_theme", redTheme);
                            intent.putExtra("blue_theme", blueTheme);
                            intent.putExtra("green_theme", greenTheme);
                            intent.putExtra("default_theme", defaultTheme);
                            setResult(RESULT_OK, intent);
                            finish();
                        }

                        // Save the name and password to SharedPreferences
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Toast.makeText(Register.this, "Clicked back", Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("switch_state", switcher.isChecked());
                resultIntent.putExtra("red_theme", redTheme);
                resultIntent.putExtra("blue_theme", blueTheme);
                resultIntent.putExtra("green_theme", greenTheme);
                resultIntent.putExtra("default_theme", defaultTheme);
                setResult(RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.button10:
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

            case R.id.button11:
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

            case R.id.button9:
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
            case R.id.button12:
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
        }
    }

    private void applyRedTheme() {
        final EditText editText = findViewById(R.id.editText2);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        final EditText editText2 = findViewById(R.id.editText3);
        final EditText editTextPassword2 = findViewById(R.id.editTextTextPassword3);
        final EditText editText3 = findViewById(R.id.editText4);
        final Button button1 = findViewById(R.id.button2);
        final Button button2 = findViewById(R.id.button14);
        final Switch switch1 = findViewById(R.id.switch2);


        editText.setBackgroundResource(R.drawable.edt_backgroundred);
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundred);
        editText2.setBackgroundResource(R.drawable.edt_backgroundred);
        editTextPassword2.setBackgroundResource(R.drawable.edt_backgroundred);
        editText3.setBackgroundResource(R.drawable.edt_backgroundred);
        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        switch1.setTextColor(getResources().getColor(R.color.red, null));
        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyBlueTheme(){
        final EditText editText = findViewById(R.id.editText2);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        final EditText editText2 = findViewById(R.id.editText3);
        final EditText editTextPassword2 = findViewById(R.id.editTextTextPassword3);
        final EditText editText3 = findViewById(R.id.editText4);
        final Button button1 = findViewById(R.id.button2);
        final Button button2 = findViewById(R.id.button14);
        final Switch switch1 = findViewById(R.id.switch2);


        editText.setBackgroundResource(R.drawable.edt_backgroundblue);
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundblue);
        editText2.setBackgroundResource(R.drawable.edt_backgroundblue);
        editTextPassword2.setBackgroundResource(R.drawable.edt_backgroundblue);
        editText3.setBackgroundResource(R.drawable.edt_backgroundblue);
        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));
        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyGreenTheme(){
        final EditText editText = findViewById(R.id.editText2);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        final EditText editText2 = findViewById(R.id.editText3);
        final EditText editTextPassword2 = findViewById(R.id.editTextTextPassword3);
        final EditText editText3 = findViewById(R.id.editText4);
        final Button button1 = findViewById(R.id.button2);
        final Button button2 = findViewById(R.id.button14);
        final Switch switch1 = findViewById(R.id.switch2);


        editText.setBackgroundResource(R.drawable.edt_backgroundgreen);
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundgreen);
        editText2.setBackgroundResource(R.drawable.edt_backgroundgreen);
        editTextPassword2.setBackgroundResource(R.drawable.edt_backgroundgreen);
        editText3.setBackgroundResource(R.drawable.edt_backgroundgreen);
        button1.setBackgroundColor(getResources().getColor(R.color.green, null));
        button2.setBackgroundColor(getResources().getColor(R.color.green, null));
        switch1.setTextColor(getResources().getColor(R.color.green, null));
        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
    }

    private void resetToDefaultTheme (){
        final EditText editText = findViewById(R.id.editText2);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        final EditText editText2 = findViewById(R.id.editText3);
        final EditText editTextPassword2 = findViewById(R.id.editTextTextPassword3);
        final EditText editText3 = findViewById(R.id.editText4);
        final Button button1 = findViewById(R.id.button2);
        final Button button2 = findViewById(R.id.button14);
        final Switch switch1 = findViewById(R.id.switch2);


        if(night){
            editText.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editText2.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editText3.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword.setBackgroundResource(R.drawable.edt_backgroundwhite);
            editTextPassword2.setBackgroundResource(R.drawable.edt_backgroundwhite);
            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button1.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            switch1.setTextColor(getResources().getColor(android.R.color.white, null));
            button1.setTextColor(getResources().getColor(R.color.black, null));
            button2.setTextColor(getResources().getColor(R.color.black, null));
        }else{
            editText.setBackgroundResource(R.drawable.edt_background);
            editText2.setBackgroundResource(R.drawable.edt_background);
            editText3.setBackgroundResource(R.drawable.edt_background);
            editTextPassword.setBackgroundResource(R.drawable.edt_background);
            editTextPassword2.setBackgroundResource(R.drawable.edt_background);
            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button1.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            switch1.setTextColor(getResources().getColor(android.R.color.black, null));
            button1.setTextColor(getResources().getColor(R.color.white, null));
            button2.setTextColor(getResources().getColor(R.color.white, null));
        }
    }


}