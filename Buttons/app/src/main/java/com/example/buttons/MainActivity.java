package com.example.buttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button reverseButton = findViewById(R.id.reverseButton);
        Button clearButton = findViewById(R.id.clearButton);

        reverseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edit_message);
                String builder = new StringBuilder(editText.getText()).reverse().toString();
                editText.setText(builder);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edit_message);
                editText.setText("");
            }
        });

    }
}
