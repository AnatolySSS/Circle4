package com.scorp.circle4;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import android.widget.Toast;

import java.util.Random;

public class TournamentFragment extends Fragment {

    private View unlimitePlayView;
    private ImageView circle;
    private TextView scoreText, testText;
    private ObjectAnimator animCircle, animText;
    private int deviceWidth = 0;
    private int deviceHeight = 0;
    private long score = 0;
    private String totalScoreStr;
    private int xRand, yRand;
    private int xStart, yStart;
    private final int MAX_LENGTH = 420;

    public TournamentFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        unlimitePlayView = inflater.inflate(R.layout.fragment_tournament, container, false);
        circle = unlimitePlayView.findViewById(R.id.circle);
        scoreText = unlimitePlayView.findViewById(R.id.scoreText);
        testText = unlimitePlayView.findViewById(R.id.testText);

        deviceWidth = unlimitePlayView.getResources().getDisplayMetrics().widthPixels;
        deviceHeight = unlimitePlayView.getResources().getDisplayMetrics().heightPixels;

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                xStart = (int) circle.getX();
                yStart = (int) circle.getY();

                score++;
                totalScoreStr = String.valueOf(score);
                scoreText.setText(totalScoreStr);

                xRand = new Random().nextInt(deviceWidth - circle.getWidth());
                yRand = new Random().nextInt(deviceHeight - circle.getHeight());

                Path path = new Path();
                path.moveTo(xStart, yStart);
                path.lineTo(xRand, yRand);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animCircle = ObjectAnimator.ofFloat(circle, "x", "y", path);
                    animCircle.setDuration(1000);
                    animCircle.start();

                    animText = ObjectAnimator.ofFloat(scoreText, "x", "y", path);
                    animText.setDuration(1000);
                    animText.start();

                    animCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float currentX = (float) valueAnimator.getAnimatedValue("x");
                            float currentY = (float) valueAnimator.getAnimatedValue("y");
                            int currentLength = (int) Math.sqrt(Math.pow(Math.abs(currentX - xStart), 2) + Math.pow(Math.abs(currentY - yStart), 2));
                            testText.setText("");
                            if (currentLength > MAX_LENGTH) {
                                testText.append(String.valueOf(currentLength));
                            }
                        }
                    });
                }
            }
        });

        return unlimitePlayView;
    }

    @Override
    public void onResume() {
        super.onResume();
        byte[] currentCircle = ((GlobalVariables) this.getActivity().getApplication()).getCircleType();
        if (currentCircle != null) {

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