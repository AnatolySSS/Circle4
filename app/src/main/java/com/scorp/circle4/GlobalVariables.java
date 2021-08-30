package com.scorp.circle4;

import android.app.Application;

import java.util.ArrayList;

public class GlobalVariables extends Application {
    long totalScore = 0;
    int circleType = 0;
    final ArrayList<Circle> circles = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        circles.add(new Circle(R.drawable.circle_black2, 10, true));
        circles.add(new Circle(R.drawable.circle_blue2, 20, false));
        circles.add(new Circle(R.drawable.circle_red2, 30, false));
        circles.add(new Circle(R.drawable.circle_purple2, 40, false));
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setCircleType(int circleType) {
        this.circleType = circleType;
    }

    public int getCircleType() {
        return circleType;
    }

}
