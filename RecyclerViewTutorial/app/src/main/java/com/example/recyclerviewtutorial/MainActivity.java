package com.example.recyclerviewtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclerviewtutorial.model.CourseResult;
import com.example.recyclerviewtutorial.adapter.RecylerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText etUnit, etMark;
    private Button button;
    private List<CourseResult> units;
    private RecylerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        units = new ArrayList<CourseResult>();
        units = CourseResult.createContactsList();

        etUnit = findViewById(R.id.et_unit);
        etMark = findViewById(R.id.et_mark);
        button = findViewById(R.id.add_button);

        adapter = new RecylerViewAdapter(units);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unit = etUnit.getText().toString().trim();
                String smark = etMark.getText().toString().trim();
                if (!unit.isEmpty() || !smark.isEmpty()) {
                    int mark = 0;
                    if (!smark.isEmpty()) {
                        mark = new Integer(smark).intValue();
                    }
                    saveData(unit,mark);
                }
            }
        });



    }

    private void saveData(String unit, int mark) {
        CourseResult courseResult = new CourseResult(unit,mark);
        units.add(courseResult);
        adapter.addUnits(units);
    }
}
