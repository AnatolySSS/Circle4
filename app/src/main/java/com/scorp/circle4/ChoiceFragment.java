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
        View mView = inflater.inflate(R.layout.fragment_choice, container, false);

        final ArrayList<Circle> purchases = new ArrayList<>();
        purchases.add(new Circle(R.drawable.circle_black2, 10, false));
        purchases.add(new Circle(R.drawable.circle_blue2, 20, false));
        purchases.add(new Circle(R.drawable.circle_red2, 30, false));
        purchases.add(new Circle(R.drawable.circle_purple2, 40, false));
        purchases.add(new Circle(R.drawable.circle_black2, 50, false));
        purchases.add(new Circle(R.drawable.circle_blue2, 60, false));
        purchases.add(new Circle(R.drawable.circle_red2, 70, false));
        purchases.add(new Circle(R.drawable.circle_purple2, 80, false));
        purchases.add(new Circle(R.drawable.circle_black2, 90, false));
        purchases.add(new Circle(R.drawable.circle_blue2, 100, false));
        purchases.add(new Circle(R.drawable.circle_red2, 110, false));
        purchases.add(new Circle(R.drawable.circle_purple2, 120, false));

        ChoiceAdapter purchaseAdapter = new ChoiceAdapter(this.getContext(), purchases);
        GridView gridView = (GridView) mView.findViewById(R.id.choice_list);
        gridView.setAdapter(purchaseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long currentScore = ((GlobalVariables) getActivity().getApplication()).getTotalScore();
                int price = purchases.get(i).getmPrice();

                if (purchases.get(i).isBought()) {
                    ((GlobalVariables) getActivity().getApplication()).setCircleType(purchases.get(i).getmImage());
                } else {
                    if (currentScore >= price) {
                        purchases.get(i).setBought(true);
                        ((GlobalVariables) getActivity().getApplication()).setCircleType(purchases.get(i).getmImage());
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