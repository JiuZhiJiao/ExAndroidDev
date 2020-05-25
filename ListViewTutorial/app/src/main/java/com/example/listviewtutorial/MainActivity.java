package com.example.listviewtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<HashMap<String,String>> unitListArray;
    SimpleAdapter myListAdapter;
    ListView unitList;
    Button addButton;
    EditText addEditText;

    HashMap<String,String> map = new HashMap<>();
    String[] colHEAD = new String[] {"CODE","UNITS","SEMESTER"};
    int[] dataCell = new int[] {R.id.UnitCode,R.id.UnitName,R.id.Semester};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitList = this.findViewById(R.id.listView);
        unitListArray = new ArrayList<HashMap<String, String>>();

        map.put("CODE", "FIT5046");
        map.put("UNITS", "Mobile and distributed Computing");
        map.put("SEMESTER", "Sem1 2020");
        unitListArray.add(map);

        map.put("CODE", "FIT5145");
        map.put("UNITS", "Introduction to Data Science");
        map.put("SEMESTER", "Sem1 2020");
        unitListArray.add(map);

        myListAdapter = new SimpleAdapter(this,unitListArray,R.layout.list_view,colHEAD,dataCell);
        unitList.setAdapter(myListAdapter);

        addButton = this.findViewById(R.id.addButton);
        addEditText = this.findViewById(R.id.addEditText);

        initAddUnitListeners();
    }

    protected void initAddUnitListeners() {
        this.addButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUnit = addEditText.getText().toString().trim();
                if (!newUnit.isEmpty()) {
                    String[] unitsArray = newUnit.split(",");
                    HashMap<String,String> map = new HashMap<>();
                    map.put("CODE", unitsArray[0]);
                    map.put("UNITS",unitsArray[1]);
                    map.put("SEMESTER",unitsArray[2]);
                    addMap(map);
                } else {
                    sendToast("Please Enter Text Firstly!");
                }
            }
        });
    }

    protected void addMap(HashMap map) {
        unitListArray.add(map);
        myListAdapter = new SimpleAdapter(this,unitListArray,R.layout.list_view,colHEAD,dataCell);
        unitList.setAdapter(myListAdapter);
    }

    protected void sendToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
