package com.scorp.circle4;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class UnlimitePlayFragment extends Fragment {

    private View unlimitePlayView;
    public ImageView circle;
    private TextView scoreText;
    private ObjectAnimator animCircle, animText;
    private int deviceWidth = 0;
    private int deviceHeight = 0;
    private long score;
    private String totalScoreStr;
    private int xRand, yRand;

    public UnlimitePlayFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        unlimitePlayView = inflater.inflate(R.layout.fragment_unlimite_play, container, false);
        circle = unlimitePlayView.findViewById(R.id.circle);
        scoreText = unlimitePlayView.findViewById(R.id.scoreText);

        deviceWidth = unlimitePlayView.getResources().getDisplayMetrics().widthPixels;
        deviceHeight = unlimitePlayView.getResources().getDisplayMetrics().heightPixels;

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                score++;
                totalScoreStr = String.valueOf(score);
                scoreText.setText(totalScoreStr);

                xRand = new Random().nextInt(deviceWidth - circle.getWidth());
                yRand = new Random().nextInt(deviceHeight - circle.getHeight());

                Path path = new Path();
                path.moveTo(circle.getX(), circle.getY());
                path.lineTo(xRand, yRand);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animCircle = ObjectAnimator.ofFloat(circle, "x", "y", path);
                    animCircle.setDuration(500);
                    animCircle.start();

                    animText = ObjectAnimator.ofFloat(scoreText, "x", "y", path);
                    animText.setDuration(500);
                    animText.start();
                }

                ((GlobalVariables) getActivity().getApplication()).setTotalScore(score);
            }
        });

        return unlimitePlayView;
    }

    @Override
    public void onResume() {
        super.onResume();

        byte[] currentCircle = ((GlobalVariables) this.getActivity().getApplication()).getCircleType();
        if (currentCircle != null) {

            score = ((GlobalVariables) getActivity().getApplication()).getTotalScore();
            scoreText.setText(String.valueOf(((GlobalVariables) getActivity().getApplication()).getTotalScore()));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[1024 *32];

            Bitmap bm = BitmapFactory.decodeByteArray(currentCircle, 0, currentCircle.length, options);

            circle.setImageBitmap(bm);
        }
    }
}