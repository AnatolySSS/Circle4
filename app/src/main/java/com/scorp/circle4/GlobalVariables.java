package com.scorp.circle4;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.scorp.circle4.data.CircleDbHelper;

import java.io.IOException;
import java.util.ArrayList;

import static com.scorp.circle4.data.CircleContract.CircleEntry;

public class GlobalVariables extends Application {
    public long totalScore = 0;
    public byte[] circleType;
    public final ArrayList<Circle> circles = new ArrayList<>();
    public CircleDbHelper mDbHelper;
    public SQLiteDatabase mDb;

    @Override
    public void onCreate() {
        super.onCreate();
        mDbHelper = new CircleDbHelper(this);

        try {
            mDbHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDbHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        String[] projection = {
                CircleEntry._ID,
                CircleEntry.COLUMN_CIRCLE_NAME,
                CircleEntry.COLUMN_CIRCLE_PRICE,
                CircleEntry.COLUMN_CIRCLE_ISBOUGHT,
                CircleEntry.COLUMN_CIRCLE_PICTURE
        };

        Cursor cursor = mDb.query(CircleEntry.TABLE_NAME, projection, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            circles.add(new Circle(cursor.getString(cursor.getColumnIndex(CircleEntry.COLUMN_CIRCLE_NAME)),
                                   cursor.getInt(cursor.getColumnIndex(CircleEntry.COLUMN_CIRCLE_PRICE)),
                                   cursor.getInt(cursor.getColumnIndex(CircleEntry.COLUMN_CIRCLE_ISBOUGHT)),
                                   cursor.getBlob(cursor.getColumnIndex(CircleEntry.COLUMN_CIRCLE_PICTURE))));
        } while (cursor.moveToNext());
        cursor.close();
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setCircleType(byte[] circleType) {
        this.circleType = circleType;
    }

    public byte[] getCircleType() {
        return circleType;
    }

}
