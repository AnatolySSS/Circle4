package com.scorp.circle4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.scorp.circle4.data.MainValuesContract;
import com.scorp.circle4.data.MainValuesContract.MainValuesEntry;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(
                new StateAdapter(this)
        );
        viewPager2.setCurrentItem(1, false);
    }

    @Override
    protected void onPause() {
        super.onPause();

        ContentValues cv_main_values = new ContentValues();
        cv_main_values.put(MainValuesEntry.COLUMN_MAIN_VALUE_CURRENT_CIRCLE, ((GlobalVariables) getApplication()).getCurrentCircle());
        cv_main_values.put(MainValuesEntry.COLUMN_MAIN_VALUE_CURRENT_SCORE, ((GlobalVariables) getApplication()).getCurrentScore());

        ((GlobalVariables) getApplication()).mDb.update(MainValuesEntry.TABLE_NAME, cv_main_values, MainValuesEntry._ID + "=" + 1, null);

    }
}