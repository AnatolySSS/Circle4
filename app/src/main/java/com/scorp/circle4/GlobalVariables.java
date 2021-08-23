package com.scorp.circle4;

import android.app.Application;

public class GlobalVariables extends Application {
    long totalScore = 0;
    int circleType = 0;

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
