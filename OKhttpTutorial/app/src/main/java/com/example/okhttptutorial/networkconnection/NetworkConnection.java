package com.example.okhttptutorial.networkconnection;

import android.util.Log;

import com.example.okhttptutorial.netbean.Student;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkConnection {

    public static final String BASE_URL = "http://192.168.0.77:8080/NamedQueryTutorial/webresources/";

    private OkHttpClient client = null;
    private String results;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public NetworkConnection() {
        client = new OkHttpClient();
    }

    public String getAllStudents() {
        final String methodPath = "student.student";

        Request.Builder builder = new Request.Builder();
        builder.url(BASE_URL+methodPath);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            results = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public String getByCourse(int courseid) {
        final String methodPath = "student.student/findByCourseid/" + courseid;

        Request.Builder builder = new Request.Builder();
        builder.url(BASE_URL+methodPath);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            results = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public String deleteStudent(int studentId) {
        final String methodPath = "student.student/" + studentId;

        Request.Builder builder = new Request.Builder();
        builder.url(BASE_URL+methodPath);
        Request request = builder.delete().build();

        try {
            Response response = client.newCall(request).execute();
            results = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public String addStudent(String[] details) {

        Student student = new Student(Integer.parseInt(details[0]), details[1],details[2]);
        student.setCourse(Integer.parseInt(details[3]),details[4]);

        Gson gson = new Gson();
        String studentJson = gson.toJson(student);
        String strResponse = "";

        Log.i("json", studentJson);

        final String methodPath = "student.student/";
        RequestBody body = RequestBody.create(studentJson, JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+methodPath)
                .post(body)
                .build();
        System.out.print(body.toString());

        try {
            Response response = client.newCall(request).execute();
            strResponse = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strResponse;
    }

}
