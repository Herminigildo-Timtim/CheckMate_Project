package com.example.checkmate_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_NOTE = 100;
    Button btnRedTheme, btnBlueTheme, btnGreenTheme, btnDefaultTheme, btnBack;
    Button btnPost, btnProfile, btnNote, btnTask, add;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch switcher;
    boolean night, redTheme, blueTheme, greenTheme, defaultTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        EditText nameDisplay = (EditText)findViewById(R.id.editTextTextPersonName);
        EditText hintDisplay = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText nameBIO = (EditText)findViewById(R.id.editTextTextPersonName6);
        EditText Bio = (EditText) findViewById(R.id.editTextTextPersonName7);
        EditText welcome = (EditText)findViewById(R.id.editTextTextPersonName6);


        String name = getIntent().getStringExtra("name");
        nameDisplay.setText(name);
        hintDisplay.setHint("@"+name);
        welcome.setText("Hi I am "+name+"!");
        nameDisplay.setEnabled(false);
        hintDisplay.setEnabled(false);


        add = findViewById(R.id.add4);
        btnBack = findViewById(R.id.button25);
        btnRedTheme = findViewById(R.id.button23);
        btnBlueTheme = findViewById(R.id.button);
        btnGreenTheme = findViewById(R.id.button13);
        btnDefaultTheme = findViewById(R.id.button15);
        btnPost = findViewById(R.id.button32);
        btnProfile = findViewById(R.id.button37);
        btnTask = findViewById(R.id.button36);
        btnNote = findViewById(R.id.button35);


        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        night = sharedPreferences.getBoolean("night", false);
        redTheme = sharedPreferences.getBoolean("red_theme", false);
        blueTheme = sharedPreferences.getBoolean("blue_theme", false);
        greenTheme = sharedPreferences.getBoolean("green_theme", false);
        defaultTheme = sharedPreferences.getBoolean("default_theme", false);
        switcher = findViewById(R.id.switch4);
        switcher.setChecked(night);

        if (night) {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switcher.setText("Dark");
        }

        if (redTheme) {
            applyRedTheme();
        } else if (blueTheme) {
            applyBlueTheme();
        } else if (greenTheme) {
            applyGreenTheme();
        } else {
            resetToDefaultTheme();
        }
        add.setOnClickListener(view ->{
            Toast.makeText(Profile.this, "Add note is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, AddNotes.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
        });
        switcher.setOnClickListener(view -> {
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
        });

        btnRedTheme.setOnClickListener(view -> {
            applyRedTheme();
            saveTheme("red_theme");
        });

        btnBlueTheme.setOnClickListener(view -> {
            applyBlueTheme();
            saveTheme("blue_theme");
        });

        btnGreenTheme.setOnClickListener(view -> {
            applyGreenTheme();
            saveTheme("green_theme");
        });

        btnDefaultTheme.setOnClickListener(view -> {
            resetToDefaultTheme();
            saveTheme("default_theme");
        });

        btnBack.setOnClickListener(view -> finish());

        btnPost.setOnClickListener(view -> {
            Intent intent3 = new Intent(this,Scroll.class);
            intent3.putExtra("name", name);
            startActivity(intent3);
            Toast.makeText(this, "Feed", Toast.LENGTH_SHORT).show();
        });

        btnProfile.setOnClickListener(view -> {
            Intent intent3 = new Intent(this,Home.class);
            intent3.putExtra("name", name);
            startActivity(intent3);
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        });

        btnTask.setOnClickListener(view -> {
            Intent intent3 = new Intent(this,Tasks.class);
            intent3.putExtra("name", name);
            startActivity(intent3);
            Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
        });
        btnNote.setOnClickListener(view -> {
            Intent intent2 = new Intent(this,Notes.class);
            intent2.putExtra("name", name);
            startActivity(intent2);
            Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveTheme(String theme) {
        editor = sharedPreferences.edit();
        editor.putBoolean("red_theme", false);
        editor.putBoolean("blue_theme", false);
        editor.putBoolean("green_theme", false);
        editor.putBoolean("default_theme", false);
        editor.putBoolean(theme, true);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK) {
            String description = data.getStringExtra("description");

            EditText Bio = (EditText) findViewById(R.id.editTextTextPersonName7);


            Bio.setText("\"" + description + "\"");
        }
    }


        // Add the theme methods from the Home class here (applyRedTheme, applyBlueTheme, applyGreenTheme, resetToDefaultTheme)
    private void applyRedTheme() {

        final Button button1 = findViewById(R.id.button25);
        final Button button2 = findViewById(R.id.button32);
        final Button button3 = findViewById(R.id.button35);
        final Button button4 = findViewById(R.id.button36);
        final Button button5 = findViewById(R.id.button37);
        final Button button6 = findViewById(R.id.add4);
        final Switch switch1 = findViewById(R.id.switch4);



        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        button3.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button5.setBackgroundColor(getResources().getColor(R.color.red, null));
        button6.setBackgroundColor(getResources().getColor(R.color.red, null));
        switch1.setTextColor(getResources().getColor(R.color.red, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));

    }
    private void applyBlueTheme(){

        final Button button1 = findViewById(R.id.button25);
        final Button button2 = findViewById(R.id.button32);
        final Button button3 = findViewById(R.id.button35);
        final Button button4 = findViewById(R.id.button36);
        final Button button5 = findViewById(R.id.button37);
        final Switch switch1 = findViewById(R.id.switch4);

        final Button button6 = findViewById(R.id.add4);



        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button3.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button4.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button5.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button6.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyGreenTheme(){

        final Button button1 = findViewById(R.id.button25);
        final Button button2 = findViewById(R.id.button32);
        final Button button3 = findViewById(R.id.button35);
        final Button button4 = findViewById(R.id.button36);
        final Button button5 = findViewById(R.id.button37);
        final Switch switch1 = findViewById(R.id.switch4);
        final Button button6 = findViewById(R.id.add4);



        button1.setBackgroundColor(getResources().getColor(R.color.green, null));
        button2.setBackgroundColor(getResources().getColor(R.color.green, null));
        button3.setBackgroundColor(getResources().getColor(R.color.green, null));
        button4.setBackgroundColor(getResources().getColor(R.color.green, null));
        button5.setBackgroundColor(getResources().getColor(R.color.green, null));
        button6.setBackgroundColor(getResources().getColor(R.color.green, null));
        switch1.setTextColor(getResources().getColor(R.color.green, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
    }

    private void resetToDefaultTheme (){

        final Button button1 = findViewById(R.id.button25);
        final Button button2 = findViewById(R.id.button32);
        final Button button3 = findViewById(R.id.button35);
        final Button button4 = findViewById(R.id.button36);
        final Button button5 = findViewById(R.id.button37);
        final Button button6 = findViewById(R.id.add4);
        final Switch switch1 = findViewById(R.id.switch4);


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
            button6.setBackgroundColor(getResources().getColor(android.R.color.white, null));

            switch1.setTextColor(getResources().getColor(android.R.color.white, null));

            button1.setTextColor(getResources().getColor(R.color.black, null));
            button2.setTextColor(getResources().getColor(R.color.black, null));
            button3.setTextColor(getResources().getColor(R.color.black, null));
            button4.setTextColor(getResources().getColor(R.color.black, null));
            button5.setTextColor(getResources().getColor(R.color.black, null));
            button6.setTextColor(getResources().getColor(R.color.black, null));

        }else{

            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button3.setTextAppearance(this, R.style.BUTTON);
            button4.setTextAppearance(this, R.style.BUTTON);
            button5.setTextAppearance(this, R.style.BUTTON);
            button6.setTextAppearance(this, R.style.BUTTON);


            button1.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button3.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button4.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button5.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button6.setBackgroundColor(getResources().getColor(android.R.color.black, null));


            switch1.setTextColor(getResources().getColor(android.R.color.black, null));

            button1.setTextColor(getResources().getColor(R.color.white, null));
            button2.setTextColor(getResources().getColor(R.color.white, null));
            button3.setTextColor(getResources().getColor(R.color.white, null));
            button4.setTextColor(getResources().getColor(R.color.white, null));
            button5.setTextColor(getResources().getColor(R.color.white, null));
            button6.setTextColor(getResources().getColor(R.color.white, null));

        }
    }
}