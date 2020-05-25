package com.example.okhttptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.okhttptutorial.networkconnection.NetworkConnection;

public class MainActivity extends AppCompatActivity {

    NetworkConnection networkConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkConnection = new NetworkConnection();

        Button findAllStudentBtn = findViewById(R.id.btnFindAll);
        final EditText editTextCourseId = findViewById(R.id.etCourseId);
        Button findByCourseBtn = findViewById(R.id.btnByCourse);
        Button btnDelete = findViewById(R.id.btnDelete);
        final Button addBtn = findViewById(R.id.btnAdd);

        findAllStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllStudentsTask getAllStudentsTask = new GetAllStudentsTask();
                getAllStudentsTask.execute();
            }
        });

        findByCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetByCourseTask getByCourseTask = new GetByCourseTask();
                String strId = editTextCourseId.getText().toString().trim();
                if (!strId.isEmpty()) {
                    int id = Integer.parseInt(strId);
                    getByCourseTask.execute(id);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAsyncTask deleteAsyncTask = new DeleteAsyncTask();
                String strId = editTextCourseId.getText().toString().trim();
                if (!strId.isEmpty()) {
                    int id = Integer.parseInt(strId);
                    deleteAsyncTask.execute(id);
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editTextCourseId.getText().toString().trim().isEmpty())) {
                    String[] details = editTextCourseId.getText().toString().trim().split(" ");
                    if (details.length == 5) {
                        AddStudentTask addStudentTask = new AddStudentTask();
                        addStudentTask.execute(details);
                    }
                }
            }
        });

    }

    private class GetAllStudentsTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return networkConnection.getAllStudents();
        }

        @Override
        protected void onPostExecute(String s) {
            TextView resultTextView = findViewById(R.id.tvResult);
            resultTextView.setText(s);
        }
    }

    private class GetByCourseTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            int id = integers[0].intValue();
            return networkConnection.getByCourse(id);
        }

        @Override
        protected void onPostExecute(String s) {
            TextView resultTextView = findViewById(R.id.tvByCourse);
            resultTextView.setText(s);
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            String message = "The record id: " + integers[0] + " was deleted";
            return networkConnection.deleteStudent(integers[0]) + message;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textView = findViewById(R.id.tvDelete);
            textView.setText(s);
        }
    }

    private class AddStudentTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String message = "The student with id: " + strings[0] + " was added";
            return networkConnection.addStudent(strings) + message;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textView = findViewById(R.id.tvAdd);
            textView.setText(s);
        }
    }
}
