package com.scorp.circle4;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class UnlimitePlayFragment extends Fragment {

    View unlimitePlayView;
    ImageView circle;
    TextView scoreText;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet;
    int deviceWidth = 0;
    int deviceHeight = 0;
    long score;
    String totalScoreStr;
    int xRand, yRand;

    public UnlimitePlayFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        unlimitePlayView = inflater.inflate(R.layout.fragment_unlimite_play, container, false);
        constraintLayout = unlimitePlayView.findViewById(R.id.activity_unlimit_play);
        circle = unlimitePlayView.findViewById(R.id.circle);
        scoreText = unlimitePlayView.findViewById(R.id.scoreText);
        constraintSet = new ConstraintSet();

        deviceWidth = unlimitePlayView.getResources().getDisplayMetrics().widthPixels;
        deviceHeight = unlimitePlayView.getResources().getDisplayMetrics().heightPixels;

//        xRand = (deviceWidth - circle.getWidth()) / 2;
//        yRand = (deviceHeight - circle.getHeight()) / 2;
        onClick ();
        return unlimitePlayView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((GlobalVariables) getActivity().getApplication()).getCircleType() != 0) {
            score = ((GlobalVariables) getActivity().getApplication()).getTotalScore();
            circle.setImageResource(((GlobalVariables) getActivity().getApplication()).getCircleType());
            scoreText.setText(String.valueOf(((GlobalVariables) getActivity().getApplication()).getTotalScore()));
        }
    }

    private void onClick () {
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                score++;
                totalScoreStr = String.valueOf(score);
                scoreText.setText(totalScoreStr);

                constraintSet.clone(constraintLayout);

                xRand = new Random().nextInt(deviceWidth - circle.getWidth());
                yRand = new Random().nextInt(deviceHeight - circle.getHeight());

//                TranslateAnimation move = new TranslateAnimation(circle.getX(), xRand, circle.getY(), yRand);
//                move.setDuration(1000);
//                circle.startAnimation(move);

                constraintSet.clear(R.id.circle, ConstraintSet.END);
                constraintSet.clear(R.id.circle, ConstraintSet.BOTTOM);
                constraintSet.clear(R.id.scoreText, ConstraintSet.END);
                constraintSet.clear(R.id.scoreText, ConstraintSet.BOTTOM);

                constraintSet.setMargin(R.id.circle, ConstraintSet.START, xRand);
                constraintSet.setMargin(R.id.circle, ConstraintSet.TOP, yRand);
                constraintSet.setMargin(R.id.scoreText, ConstraintSet.START, xRand);
                constraintSet.setMargin(R.id.scoreText, ConstraintSet.TOP, yRand);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                }

                constraintSet.applyTo(constraintLayout);
                ((GlobalVariables) getActivity().getApplication()).setTotalScore(score);
            }
        });
    }
}