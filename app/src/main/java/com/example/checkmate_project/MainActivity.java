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
        switch(view.getId()){
            case R.id.button7:
                Toast.makeText(MainActivity.this, "Sign Up button is clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Register.class);
                intent.putExtra("switch_state", switcher.isChecked());
                startActivityForResult(intent, 1);
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
}