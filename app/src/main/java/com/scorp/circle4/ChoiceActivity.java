package com.scorp.circle4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);

        final ArrayList<Circle> circles = ((GlobalVariables) this.getApplication()).circles;

        CircleAdapter purchaseAdapter = new CircleAdapter(this, circles);
        GridView gridView = (GridView) findViewById(R.id.choice_list);
        gridView.setAdapter(purchaseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long currentScore = ((GlobalVariables) getApplication()).getTotalScore();
                int price = circles.get(i).getmPrice();

                if (circles.get(i).isBought()) {
                    ((GlobalVariables) getApplication()).setCircleType(circles.get(i).getmImage());
                    startActivity(intent);
                } else {
                    if (currentScore >= price) {
                        circles.get(i).setBought(true);
                        ((GlobalVariables) getApplication()).setCircleType(circles.get(i).getmImage());
                        ((GlobalVariables) getApplication()).setTotalScore(currentScore - price);
                        view.findViewById(R.id.choice_image).setAlpha(1);
                        view.findViewById(R.id.choice_price).setVisibility(View.GONE);
                        Toast.makeText(ChoiceActivity.this, "", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ChoiceActivity.this, "You've got it!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(ChoiceActivity.this, "Need more Clicks", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
