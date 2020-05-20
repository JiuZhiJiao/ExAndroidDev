package com.example.spinnertutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        final Spinner courseSpinner = findViewById(R.id.courseSpinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCourse = courseSpinner.getSelectedItem().toString();
                textView.setText(selectedCourse);
            }
        });

        // Dynamic data using ArrayAdapter
        List<String> list = new ArrayList<String>();
        list.add("Avengers");
        list.add("Lion King");
        list.add("John Wick");

        Button buttonAddMovie = findViewById(R.id.button2);
        final EditText editText = findViewById(R.id.editText);
        final Spinner movieList = findViewById(R.id.spinner2);

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        movieList.setAdapter(spinnerAdapter);

        buttonAddMovie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newMovie = editText.getText().toString();
                spinnerAdapter.add(newMovie);
                spinnerAdapter.notifyDataSetChanged();
                movieList.setSelection(spinnerAdapter.getPosition(newMovie));
            }
        });

        movieList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMovie = parent.getItemAtPosition(position).toString();
                if (selectedMovie != null) {
                    Toast.makeText(parent.getContext(), "Movie selected is " + selectedMovie, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
