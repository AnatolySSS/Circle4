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
    TextView scoreText;
    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);

        startCircle = rootView.findViewById(R.id.startCircle);
        imageNoAds = rootView.findViewById(R.id.imageNoAds);
        imageResults = rootView.findViewById(R.id.imageResults);
        imageChangeCircle = rootView.findViewById(R.id.imageChangeCircle);
        imageBuy = rootView.findViewById(R.id.imageBuy);
        circle2 = rootView.findViewById(R.id.circle2);
        tapToGame = rootView.findViewById(R.id.tapToGame);
        scoreText = rootView.findViewById(R.id.scoreTextStart);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        scoreText.setText("TOTAL SCORE ");
        scoreText.append(String.valueOf(((GlobalVariables) this.getActivity().getApplication()).getTotalScore()));
        if (((GlobalVariables) getActivity().getApplication()).getCircleType() != 0) {
            startCircle.setImageResource(((GlobalVariables) this.getActivity().getApplication()).getCircleType());
        }
    }
}