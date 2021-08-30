package com.scorp.circle4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoiceFragment extends Fragment {

    public ChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.activity_choice, container, false);

        final ArrayList<Circle> circles = ((GlobalVariables) getActivity().getApplication()).circles;
        circles.add(new Circle(R.drawable.circle_black2, 10, false));
        circles.add(new Circle(R.drawable.circle_blue2, 20, false));
        circles.add(new Circle(R.drawable.circle_red2, 30, false));
        circles.add(new Circle(R.drawable.circle_purple2, 40, false));

        CircleAdapter purchaseAdapter = new CircleAdapter(this.getContext(), circles);
        GridView gridView = (GridView) mView.findViewById(R.id.choice_list);
        gridView.setAdapter(purchaseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long currentScore = ((GlobalVariables) getActivity().getApplication()).getTotalScore();
                int price = circles.get(i).getmPrice();

                if (circles.get(i).isBought()) {
                    ((GlobalVariables) getActivity().getApplication()).setCircleType(circles.get(i).getmImage());
                } else {
                    if (currentScore >= price) {
                        circles.get(i).setBought(true);
                        ((GlobalVariables) getActivity().getApplication()).setCircleType(circles.get(i).getmImage());
                        ((GlobalVariables) getActivity().getApplication()).setTotalScore(currentScore - price);
                        view.findViewById(R.id.choice_image).setAlpha(1);
                        view.findViewById(R.id.choice_price).setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "You've got it!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Need more Clicks", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return mView;
    }
}