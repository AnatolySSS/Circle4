package com.scorp.circle4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class FragmentChoice extends Fragment {

    public FragmentChoice() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choice, container, false);

        final ArrayList<Choice> purchases = new ArrayList<>();
        purchases.add(new Choice(R.drawable.circle_black2, 50));
        purchases.add(new Choice(R.drawable.circle_blue2, 150));
        purchases.add(new Choice(R.drawable.circle_red2, 200));
        purchases.add(new Choice(R.drawable.circle_purple2, 250));
        purchases.add(new Choice(R.drawable.circle_black2, 50));
        purchases.add(new Choice(R.drawable.circle_blue2, 150));
        purchases.add(new Choice(R.drawable.circle_red2, 200));
        purchases.add(new Choice(R.drawable.circle_purple2, 250));
        purchases.add(new Choice(R.drawable.circle_black2, 50));
        purchases.add(new Choice(R.drawable.circle_blue2, 150));
        purchases.add(new Choice(R.drawable.circle_red2, 200));
        purchases.add(new Choice(R.drawable.circle_purple2, 250));

        ChoiceAdapter purchaseAdapter = new ChoiceAdapter(this.getContext(), purchases);
        GridView gridView = (GridView) view.findViewById(R.id.choice_list);
        gridView.setAdapter(purchaseAdapter);
        return view;
    }
}