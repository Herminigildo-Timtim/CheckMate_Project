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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignUp,btnRed,btnBlue,btn4,btn5;
    Switch switcher;
    boolean night;
    boolean redTheme;
    boolean blueTheme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSignUp = (Button)findViewById(R.id.button7);
        btnRed = (Button)findViewById(R.id.button4);
        btnBlue = (Button)findViewById(R.id.button6);
        btnSignUp.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnBlue.setOnClickListener(this);

        getSupportActionBar().hide();
        switcher = findViewById(R.id.switch1);
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        //To save mode after exiting from the program
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        //Light mode is the default mode
        blueTheme = sharedPreferences.getBoolean("blue_theme", false);
        redTheme = sharedPreferences.getBoolean("red_theme", false);
        night = sharedPreferences.getBoolean("night",false);
        switcher.setChecked(night);

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
        }
        if(blueTheme){
            applyBlueTheme();
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
                        editText.setBackgroundResource(R.drawable.edt_background);
                        editTextPassword.setBackgroundResource(R.drawable.edt_background);

                    }
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    drawable1.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    editText.setCompoundDrawables(drawable, null, null, null);
                    editTextPassword.setCompoundDrawables(drawable1,null,null,null);
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
                intent.putExtra("red_theme", true);
                intent.putExtra("blue_theme", true);
                // Add this line to pass the red theme state
                startActivityForResult(intent, 1);
                break;
            case R.id.button4:

                applyRedTheme();
                redTheme = true;
                editor = sharedPreferences.edit();
                editor.putBoolean("red_theme", redTheme);
                editor.apply();
                break;

            case R.id.button6:
                applyBlueTheme();
                blueTheme = true;
                editor = sharedPreferences.edit();
                editor.putBoolean("blue_theme", blueTheme);
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
        Toast.makeText(MainActivity.this, "Red Theme!", Toast.LENGTH_SHORT).show();
        editText.setBackgroundResource(R.drawable.edt_backgroundred); // Create a new drawable for the red background
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundred); // Create a new drawable for the red background

        drawable = getResources().getDrawable(R.drawable.baseline_person_24_red, null); // Create a new drawable for the red person icon
        drawable1 = getResources().getDrawable(R.drawable.baseline_key_24_red, null); // Create a new drawable for the red key icon

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
        editText.setCompoundDrawables(drawable, null, null, null);
        editTextPassword.setCompoundDrawables(drawable1, null, null, null);
        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        switch1.setTextColor(getResources().getColor(R.color.red, null));
    }
    private void applyBlueTheme(){
        final EditText editText = findViewById(R.id.editText);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        final Button button1 = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button7);
        final Switch switch1 = findViewById(R.id.switch1);


        Drawable drawable, drawable1;
        Toast.makeText(MainActivity.this, "Blue Theme!", Toast.LENGTH_SHORT).show();
        editText.setBackgroundResource(R.drawable.edt_backgroundblue); // Create a new drawable for the red background
        editTextPassword.setBackgroundResource(R.drawable.edt_backgroundblue); // Create a new drawable for the red background

        drawable = getResources().getDrawable(R.drawable.baseline_person_24_blue, null); // Create a new drawable for the red person icon
        drawable1 = getResources().getDrawable(R.drawable.baseline_key_24_blue, null); // Create a new drawable for the red key icon

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
        editText.setCompoundDrawables(drawable, null, null, null);
        editTextPassword.setCompoundDrawables(drawable1, null, null, null);
        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));
    }
}