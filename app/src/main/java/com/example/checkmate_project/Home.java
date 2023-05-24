package com.example.checkmate_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_CODE_HOME = 2;

    Button btnRedTheme, btnBlueTheme,btnGreenTheme,btnDefaultTheme;
    Button btnGoal,btnBack,btnEvent,btnHobby,btnGoal1,btnEvent1,btnHobby1;
    Button btnHome,btnProfile,btnNote,btnTask;

    String name;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch switcher;

    boolean night, redTheme, blueTheme, greenTheme,defaultTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        EditText titleEditText1 = findViewById(R.id.title);
        EditText titleEditText2 = findViewById(R.id.title1);
        EditText titleEditText3 = findViewById(R.id.title2);
        name = getIntent().getStringExtra("name");
        titleEditText1.setText("@"+name);
        titleEditText2.setText("@"+name);
        titleEditText3.setText("@"+name);

        btnHome = (Button)findViewById(R.id.button33);
        btnProfile = (Button)findViewById(R.id.button42);
        btnTask = (Button)findViewById(R.id.button41);
        btnNote = (Button)findViewById(R.id.button40);
        btnGoal = (Button) findViewById(R.id.add);
        btnEvent = (Button) findViewById(R.id.add2);
        btnHobby = (Button) findViewById(R.id.add3);
        btnGoal1 = (Button) findViewById(R.id.button24);
        btnEvent1 = (Button) findViewById(R.id.button27);
        btnHobby1 = (Button) findViewById(R.id.button28);

        btnBack = (Button)findViewById(R.id.button22);



        // Set the OnClickListener for the btnAdd button
        btnHome.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnTask.setOnClickListener(this);
        btnNote.setOnClickListener(this);
        btnGoal.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnHobby.setOnClickListener(this);
        btnGoal1.setOnClickListener(this);
        btnEvent1.setOnClickListener(this);
        btnHobby1.setOnClickListener(this);
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



        btnGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddNotes.class);
                intent.putExtra("button_id", "goal");
                startActivityForResult(intent, REQUEST_CODE_HOME);
            }
        });
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddNotes.class);
                intent.putExtra("button_id", "event");
                startActivityForResult(intent, REQUEST_CODE_HOME);
            }
        });

        btnHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddNotes.class);
                intent.putExtra("button_id", "hobby");
                startActivityForResult(intent, REQUEST_CODE_HOME);
            }
        });

        btnGoal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText goalEditText = findViewById(R.id.goal);
                goalEditText.setText("");
            }
        });
        btnEvent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eventEditText = findViewById(R.id.event);
                eventEditText.setText("");
            }
        });

        btnHobby1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText hobbyEditText = findViewById(R.id.hobby);
                hobbyEditText.setText("");
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_HOME && resultCode == RESULT_OK) {
            String description = data.getStringExtra("description");
            String time = data.getStringExtra("time");
            String buttonId = data.getStringExtra("button_id");

            EditText goalEditText = findViewById(R.id.goal);
            EditText eventEditText = findViewById(R.id.event);
            EditText hobbyEditText = findViewById(R.id.hobby);

            String timeText = "(" + time + ")";
            SpannableString spannableTimeText = new SpannableString(timeText);
            spannableTimeText.setSpan(new RelativeSizeSpan(0.7f), 0, timeText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            switch (buttonId) {
                case "goal":
                    goalEditText.setText("\"" + description + "\"");
                    goalEditText.append("\n");
                    goalEditText.append(spannableTimeText);
                    break;
                case "event":
                    eventEditText.setText("\"" + description + "\"");
                    eventEditText.append("\n");
                    eventEditText.append(spannableTimeText);
                    break;
                case "hobby":
                    hobbyEditText.setText("\"" + description + "\"");
                    hobbyEditText.append("\n");
                    hobbyEditText.append(spannableTimeText);
                    break;
            }
        }
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
            case R.id.button33:
                if (!isHomePage()) {
                    Intent intent1 = new Intent(this, Home.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "This is already Home", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button40:
                //For Note class
                Intent intent2 = new Intent(this,Notes.class);
                intent2.putExtra("name", name);
                startActivity(intent2);
                Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button41:
                //For Task class
                Intent intent3 = new Intent(this,Tasks.class);
                intent3.putExtra("name", name);
                startActivity(intent3);
                Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button42:
                Intent intent4 = new Intent(this,Profile.class);
                intent4.putExtra("name", name);
                startActivity(intent4);
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean isHomePage() {
        return this.getClass().getSimpleName().equals("Home");
    }

    private void applyRedTheme() {

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Button button8 = findViewById(R.id.button33);
        final Button button9 = findViewById(R.id.button42);
        final Button button10 = findViewById(R.id.button41);
        final Button button11 = findViewById(R.id.button40);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.red, null));
        button2.setBackgroundColor(getResources().getColor(R.color.red, null));
        button3.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button4.setBackgroundColor(getResources().getColor(R.color.red, null));
        button5.setBackgroundColor(getResources().getColor(R.color.red, null));
        button6.setBackgroundColor(getResources().getColor(R.color.red, null));
        button7.setBackgroundColor(getResources().getColor(R.color.red, null));
        button8.setBackgroundColor(getResources().getColor(R.color.red, null));
        button9.setBackgroundColor(getResources().getColor(R.color.red, null));
        button10.setBackgroundColor(getResources().getColor(R.color.red, null));
        button11.setBackgroundColor(getResources().getColor(R.color.red, null));

        switch1.setTextColor(getResources().getColor(R.color.red, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
        button7.setTextColor(getResources().getColor(R.color.white, null));
        button8.setTextColor(getResources().getColor(R.color.white, null));
        button9.setTextColor(getResources().getColor(R.color.white, null));
        button10.setTextColor(getResources().getColor(R.color.white, null));
        button11.setTextColor(getResources().getColor(R.color.white, null));

    }
    private void applyBlueTheme(){

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Button button8 = findViewById(R.id.button33);
        final Button button9 = findViewById(R.id.button42);
        final Button button10 = findViewById(R.id.button41);
        final Button button11 = findViewById(R.id.button40);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button2.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button3.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button4.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button5.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button6.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button7.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button8.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button9.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button10.setBackgroundColor(getResources().getColor(R.color.skyblue, null));
        button11.setBackgroundColor(getResources().getColor(R.color.skyblue, null));

        switch1.setTextColor(getResources().getColor(R.color.skyblue, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
        button7.setTextColor(getResources().getColor(R.color.white, null));
        button8.setTextColor(getResources().getColor(R.color.white, null));
        button9.setTextColor(getResources().getColor(R.color.white, null));
        button10.setTextColor(getResources().getColor(R.color.white, null));
        button11.setTextColor(getResources().getColor(R.color.white, null));
    }
    private void applyGreenTheme(){

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Button button8 = findViewById(R.id.button33);
        final Button button9 = findViewById(R.id.button42);
        final Button button10 = findViewById(R.id.button41);
        final Button button11 = findViewById(R.id.button40);
        final Switch switch1 = findViewById(R.id.switch3);



        button1.setBackgroundColor(getResources().getColor(R.color.green, null));
        button2.setBackgroundColor(getResources().getColor(R.color.green, null));
        button3.setBackgroundColor(getResources().getColor(R.color.green, null));
        button4.setBackgroundColor(getResources().getColor(R.color.green, null));
        button5.setBackgroundColor(getResources().getColor(R.color.green, null));
        button6.setBackgroundColor(getResources().getColor(R.color.green, null));
        button7.setBackgroundColor(getResources().getColor(R.color.green, null));
        button8.setBackgroundColor(getResources().getColor(R.color.green, null));
        button9.setBackgroundColor(getResources().getColor(R.color.green, null));
        button10.setBackgroundColor(getResources().getColor(R.color.green, null));
        button11.setBackgroundColor(getResources().getColor(R.color.green, null));

        switch1.setTextColor(getResources().getColor(R.color.green, null));

        button1.setTextColor(getResources().getColor(R.color.white, null));
        button2.setTextColor(getResources().getColor(R.color.white, null));
        button3.setTextColor(getResources().getColor(R.color.white, null));
        button4.setTextColor(getResources().getColor(R.color.white, null));
        button5.setTextColor(getResources().getColor(R.color.white, null));
        button6.setTextColor(getResources().getColor(R.color.white, null));
        button7.setTextColor(getResources().getColor(R.color.white, null));
        button8.setTextColor(getResources().getColor(R.color.white, null));
        button9.setTextColor(getResources().getColor(R.color.white, null));
        button10.setTextColor(getResources().getColor(R.color.white, null));
        button11.setTextColor(getResources().getColor(R.color.white, null));
    }

    private void resetToDefaultTheme (){

        final Button button1 = findViewById(R.id.button22);
        final Button button2 = findViewById(R.id.button24);
        final Button button3 = findViewById(R.id.add);
        final Button button4 = findViewById(R.id.button27);
        final Button button5 = findViewById(R.id.add2);
        final Button button6 = findViewById(R.id.button28);
        final Button button7 = findViewById(R.id.add3);
        final Button button8 = findViewById(R.id.button33);
        final Button button9 = findViewById(R.id.button42);
        final Button button10 = findViewById(R.id.button41);
        final Button button11 = findViewById(R.id.button40);

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
            button8.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button9.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button10.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            button11.setBackgroundColor(getResources().getColor(android.R.color.white, null));

            switch1.setTextColor(getResources().getColor(android.R.color.white, null));

            button1.setTextColor(getResources().getColor(R.color.black, null));
            button2.setTextColor(getResources().getColor(R.color.black, null));
            button3.setTextColor(getResources().getColor(R.color.black, null));
            button4.setTextColor(getResources().getColor(R.color.black, null));
            button5.setTextColor(getResources().getColor(R.color.black, null));
            button6.setTextColor(getResources().getColor(R.color.black, null));
            button7.setTextColor(getResources().getColor(R.color.black, null));
            button8.setTextColor(getResources().getColor(R.color.black, null));
            button9.setTextColor(getResources().getColor(R.color.black, null));
            button10.setTextColor(getResources().getColor(R.color.black, null));
            button11.setTextColor(getResources().getColor(R.color.black, null));
        }else{

            button1.setTextAppearance(this, R.style.BUTTON);
            button2.setTextAppearance(this, R.style.BUTTON);
            button3.setTextAppearance(this, R.style.BUTTON);
            button4.setTextAppearance(this, R.style.BUTTON);
            button5.setTextAppearance(this, R.style.BUTTON);
            button6.setTextAppearance(this, R.style.BUTTON);
            button7.setTextAppearance(this, R.style.BUTTON);
            button8.setTextAppearance(this, R.style.BUTTON);
            button9.setTextAppearance(this, R.style.BUTTON);
            button10.setTextAppearance(this, R.style.BUTTON);
            button11.setTextAppearance(this, R.style.BUTTON);

            button1.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button2.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button3.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button4.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button5.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button6.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button7.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button8.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button9.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button10.setBackgroundColor(getResources().getColor(android.R.color.black, null));
            button11.setBackgroundColor(getResources().getColor(android.R.color.black, null));

            switch1.setTextColor(getResources().getColor(android.R.color.black, null));

            button1.setTextColor(getResources().getColor(R.color.white, null));
            button2.setTextColor(getResources().getColor(R.color.white, null));
            button3.setTextColor(getResources().getColor(R.color.white, null));
            button4.setTextColor(getResources().getColor(R.color.white, null));
            button5.setTextColor(getResources().getColor(R.color.white, null));
            button6.setTextColor(getResources().getColor(R.color.white, null));
            button7.setTextColor(getResources().getColor(R.color.white, null));
            button8.setTextColor(getResources().getColor(R.color.white, null));
            button9.setTextColor(getResources().getColor(R.color.white, null));
            button10.setTextColor(getResources().getColor(R.color.white, null));
            button11.setTextColor(getResources().getColor(R.color.white, null));
        }
    }
}