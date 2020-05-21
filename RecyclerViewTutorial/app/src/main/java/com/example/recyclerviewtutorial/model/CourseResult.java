package com.example.recyclerviewtutorial.model;

import java.util.ArrayList;
import java.util.List;

public class CourseResult {

    private String mUnit;
    private int mMark;

    public CourseResult(String unit, int mark) {
        mUnit = unit;
        mMark = mark;
    }

    public String getUnit() {
        return mUnit;
    }

    public int getMark() {
        return mMark;
    }

    public static List<CourseResult> createContactsList() {
        List<CourseResult> units = new ArrayList<>();
        units.add(new CourseResult("FIT5046",87));
        units.add(new CourseResult("FIT5152",77));
        return units;
    }

}
