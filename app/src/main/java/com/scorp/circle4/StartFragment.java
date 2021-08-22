package com.scorp.circle4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class StartFragment extends Fragment {

    ImageView startCircle;
    ImageView imageNoAds;
    ImageView imageResults;
    ImageView imageChangeCircle;
    ImageView imageBuy;
    TextView circle2;
    TextView tapToGame;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        startCircle = (ImageView) rootView.findViewById(R.id.startCircle);
        imageNoAds = (ImageView) rootView.findViewById(R.id.imageNoAds);
        imageResults = (ImageView) rootView.findViewById(R.id.imageResults);
        imageChangeCircle = (ImageView) rootView.findViewById(R.id.imageChangeCircle);
        imageBuy = (ImageView) rootView.findViewById(R.id.imageBuy);
        circle2 = (TextView) rootView.findViewById(R.id.circle2);
        tapToGame = (TextView) rootView.findViewById(R.id.tapToGame);

        return rootView;
    }
}