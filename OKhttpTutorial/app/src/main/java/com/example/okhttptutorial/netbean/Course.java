package com.example.okhttptutorial.netbean;

public class Course {

    private int courseId;
    private String courseName;

    public Course(int courseId, String name) {
        this.courseId = courseId;
        this.courseName = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
