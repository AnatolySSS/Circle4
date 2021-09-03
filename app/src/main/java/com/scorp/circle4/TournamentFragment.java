package com.scorp.circle4;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ContentValues;
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

import com.scorp.circle4.data.MainValuesContract;
import com.scorp.circle4.data.MainValuesContract.MainValuesEntry;

import java.util.Random;

public class TournamentFragment extends Fragment {

    private View unlimitePlayView;
    private ImageView circle;
    private TextView scoreText, testText;
    private ObjectAnimator animCircle, animText;
    private int deviceWidth = 0;
    private int deviceHeight = 0;
    private long currentTournamentScore = 0;
    private long maxTournamentScore = 0;
    private long recordTournamentScore;
    private String totalScoreStr;
    private int xRand, yRand;
    private int xStart, yStart;
    private final int MAX_LENGTH = 420;
    boolean hasListener, wasFired;

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

        recordTournamentScore = ((GlobalVariables) this.getActivity().getApplication()).recordTournamentScore;
        testText.setText("Your record is " + recordTournamentScore);

        hasListener = false;

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wasFired = false;

                if (hasListener) {
                    animCircle.removeAllUpdateListeners();
                }

                xStart = (int) circle.getX();
                yStart = (int) circle.getY();

                currentTournamentScore++;
                totalScoreStr = String.valueOf(currentTournamentScore);
                scoreText.setText(totalScoreStr);

                xRand = new Random().nextInt(deviceWidth - circle.getWidth());
                yRand = new Random().nextInt(deviceHeight - circle.getHeight());

                Path path = new Path();
                path.moveTo(xStart, yStart);
                path.lineTo(xRand, yRand);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animCircle = ObjectAnimator.ofFloat(circle, "x", "y", path);
                    animCircle.setDuration(550);
                    animCircle.start();

                    animText = ObjectAnimator.ofFloat(scoreText, "x", "y", path);
                    animText.setDuration(550);
                    animText.start();

                    animCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            hasListener = true;
                            float currentX = (float) valueAnimator.getAnimatedValue("x");
                            float currentY = (float) valueAnimator.getAnimatedValue("y");
                            int currentLength = (int) Math.sqrt(Math.pow(currentX - xStart, 2) + Math.pow(currentY - yStart, 2));

                            if (currentLength > MAX_LENGTH && !wasFired) {
                                maxTournamentScore = currentTournamentScore;
                                Toast.makeText(getActivity(), "GameOver! Your score is " + maxTournamentScore, Toast.LENGTH_SHORT).show();
                                wasFired = true;
                                currentTournamentScore = 0;
                            }
                            if (currentTournamentScore > recordTournamentScore) {
                                recordTournamentScore = currentTournamentScore;
                                testText.setText("Your record is " + recordTournamentScore);

                                ContentValues cv_main_values = new ContentValues();

                                cv_main_values.put(MainValuesEntry.COLUMN_MAIN_VALUE_RECORD_TOURNAMENT_SCORE, recordTournamentScore);
                                ((GlobalVariables) getActivity().getApplication()).mDb.update(MainValuesEntry.TABLE_NAME, cv_main_values, MainValuesEntry._ID + "=" + 1, null);
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
        byte[] currentCircle = ((GlobalVariables) this.getActivity().getApplication()).getCurrentCircle();
        if (currentCircle != null) {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[1024 *32];

            Bitmap bm = BitmapFactory.decodeByteArray(currentCircle, 0, currentCircle.length, options);
            bm = Bitmap.createScaledBitmap(bm, circle.getMaxWidth(),circle.getMaxHeight(),true);

            circle.setImageBitmap(bm);
        }
    }
}