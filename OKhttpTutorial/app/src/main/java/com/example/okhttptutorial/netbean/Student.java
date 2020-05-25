package com.example.okhttptutorial.netbean;

public class Student {

    private int stuId;
    private String stuName;
    private String stuPostcode;
    private Course course;

    public Student(int stuId, String stuName, String stuPostcode) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuPostcode = stuPostcode;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPostcode() {
        return stuPostcode;
    }

    public void setStuPostcode(String stuPostcode) {
        this.stuPostcode = stuPostcode;
    }

    public void setCourse(int id, String name) {
        course = new Course(id, name);
    }
}
