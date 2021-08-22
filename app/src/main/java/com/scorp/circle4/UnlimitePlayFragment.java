package com.scorp.circle4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class UnlimitePlayFragment extends Fragment {

    Bitmap circle;
    ImageView imageView;
    int deviceWidth = 0;
    int deviceHeight = 0;
    int xRand, yRand;

    public UnlimitePlayFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = new CircleView(this.getContext());
        deviceWidth = view.getResources().getDisplayMetrics().widthPixels;
        deviceHeight = view.getResources().getDisplayMetrics().heightPixels;
        imageView = new ImageView(this.getContext());
        circle = BitmapFactory.decodeResource(getResources(), R.drawable.circle_black2);
        imageView.setImageBitmap(circle);
        xRand = (deviceWidth - circle.getWidth()) / 2;
        yRand = (deviceHeight - circle.getHeight()) / 2;
        TranslateAnimation move = new TranslateAnimation(imageView.getX(), xRand, imageView.getY(), yRand);
        move.setDuration(1000);
        imageView.startAnimation(move);
        return view;
    }

    class CircleView extends View {

        int touchX = 0;
        int touchY = 0;


        public CircleView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(circle, xRand, yRand, null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                touchX = (int) event.getX();
                touchY = (int) event.getY();
                if (clickedOnBitmap(touchX, touchY)) {
                    xRand = new Random().nextInt(deviceWidth - circle.getWidth());
                    yRand = new Random().nextInt(deviceHeight - circle.getHeight());
                    invalidate();
                }
            }
            return true;
        }

        public boolean clickedOnBitmap(final int x, final int y) {
            return x > xRand && x < xRand + circle.getWidth()
                    && y > yRand && y < yRand + circle.getHeight();
        }

    }
}