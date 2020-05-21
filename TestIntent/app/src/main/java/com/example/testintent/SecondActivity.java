package com.example.testintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button = findViewById(R.id.button);
        Button messageButton = findViewById(R.id.button2);
        final TextView textView = findViewById(R.id.messageText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                EditText editText = findViewById(R.id.editText);
                String message = editText.getText().toString();
                returnIntent.putExtra("message",message);

                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                /*
                String name = bundle.getString("name");
                String surname = bundle.getString("surname");
                Integer age = bundle.getInt("age");
                textView.setText(name+" " + surname + " " + age);
                 */
                Student student = bundle.getParcelable("student");
                String name = student.getName();
                int id = student.getId();
                textView.setText(id + " " + name);
            }
        });
    }
}
