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
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button btnBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch switcher;
    boolean night;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnBack = (Button)findViewById(R.id.button2);
        btnBack.setOnClickListener(this);

        // Find the switch view in the second activity
        boolean switchState = getIntent().getBooleanExtra("switch_state", false);
        boolean redTheme = getIntent().getBooleanExtra("red_theme", false);
        boolean blueTheme = getIntent().getBooleanExtra("blue_theme",false);
        switcher = findViewById(R.id.switch2); // Replace "switch2" with the id of the switch in the second activity
        switcher.setChecked(switchState);


        // Retrieve the saved state of the switch button
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        night = sharedPreferences.getBoolean("night", false);

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
            editor = sharedPreferences.edit();
            editor.putBoolean("red_theme", redTheme);
            editor.apply();
        }
        if(blueTheme){
            applyRedTheme();
            editor = sharedPreferences.edit();
            editor.putBoolean("red_theme", redTheme);
            editor.apply();
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
                    editText.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword.setBackgroundResource(R.drawable.edt_background);
                    editText2.setBackgroundResource(R.drawable.edt_background);
                    editTextPassword2.setBackgroundResource(R.drawable.edt_background);
                    editText3.setBackgroundResource(R.drawable.edt_background);
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
        if (redTheme) {
            applyRedTheme();
        }
        if(blueTheme){
            applyBlueTheme();
        }
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button2:
                Toast.makeText(Register.this, "Clicked back", Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("switch_state", switcher.isChecked());
                setResult(RESULT_OK, resultIntent);
                finish();
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
    }
}