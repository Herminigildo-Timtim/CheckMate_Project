package com.example.checkmate_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AddNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        EditText description = findViewById(R.id.description);
        Button save = (Button) findViewById(R.id.button16);
        Button back = (Button)findViewById(R.id.button17);

        Button buttonSave = findViewById(R.id.button16);
        EditText descriptionEditText = findViewById(R.id.description);

        // get the button_id passed from Home activity
        String buttonId = getIntent().getStringExtra("button_id");

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionEditText.getText().toString();
                String time = DateFormat.getTimeInstance().format(new Date());

                if (description.isEmpty()) {
                    Toast.makeText(AddNotes.this, "Please input a description", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("time", time);
                resultIntent.putExtra("button_id", buttonId);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
