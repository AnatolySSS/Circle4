package com.scorp.circle4;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.scorp.circle4.data.CircleDbHelper;
import com.scorp.circle4.data.MainValuesContract.MainValuesEntry;

import java.io.IOException;
import java.util.ArrayList;

import static com.scorp.circle4.data.CircleContract.CircleEntry;

public class GlobalVariables extends Application {
    public long currentScore;
    public byte[] currentCircle;
    public int recordTournamentScore;
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

        String[] projection_circles = {
                CircleEntry._ID,
                CircleEntry.COLUMN_CIRCLE_NAME,
                CircleEntry.COLUMN_CIRCLE_PRICE,
                CircleEntry.COLUMN_CIRCLE_ISBOUGHT,
                CircleEntry.COLUMN_CIRCLE_PICTURE
        };

        Cursor cursor_table_circles = mDb.query(CircleEntry.TABLE_NAME, projection_circles, null, null, null, null, null);
        cursor_table_circles.moveToFirst();
        do {
            circles.add(new Circle(cursor_table_circles.getString(cursor_table_circles.getColumnIndex(CircleEntry.COLUMN_CIRCLE_NAME)),
                                   cursor_table_circles.getInt(cursor_table_circles.getColumnIndex(CircleEntry.COLUMN_CIRCLE_PRICE)),
                                   cursor_table_circles.getInt(cursor_table_circles.getColumnIndex(CircleEntry.COLUMN_CIRCLE_ISBOUGHT)),
                                   cursor_table_circles.getBlob(cursor_table_circles.getColumnIndex(CircleEntry.COLUMN_CIRCLE_PICTURE))));
        } while (cursor_table_circles.moveToNext());

        cursor_table_circles.close();

        String[] projection_main_values = {
                MainValuesEntry.COLUMN_MAIN_VALUE_CURRENT_SCORE,
                MainValuesEntry.COLUMN_MAIN_VALUE_CURRENT_CIRCLE,
                MainValuesEntry.COLUMN_MAIN_VALUE_RECORD_TOURNAMENT_SCORE
        };

        Cursor cursor_table_main_values = mDb.query(MainValuesEntry.TABLE_NAME, projection_main_values, null, null, null, null, null);
        cursor_table_main_values.moveToFirst();

        currentScore = cursor_table_main_values.getInt(cursor_table_main_values.getColumnIndex(MainValuesEntry.COLUMN_MAIN_VALUE_CURRENT_SCORE));
        currentCircle = cursor_table_main_values.getBlob(cursor_table_main_values.getColumnIndex(MainValuesEntry.COLUMN_MAIN_VALUE_CURRENT_CIRCLE));
        recordTournamentScore = cursor_table_main_values.getInt(cursor_table_main_values.getColumnIndex(MainValuesEntry.COLUMN_MAIN_VALUE_RECORD_TOURNAMENT_SCORE));

        cursor_table_main_values.close();
    }

    public void setCurrentScore(long currentScore) {
        this.currentScore = currentScore;
    }

    public long getCurrentScore() {
        return currentScore;
    }

    public void setCurrentCircle(byte[] currentCircle) {
        this.currentCircle = currentCircle;
    }

    public byte[] getCurrentCircle() {
        return currentCircle;
    }



}
