package com.scorp.circle4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ChoiceAdapter extends ArrayAdapter<Choice> {

    public ChoiceAdapter(@NonNull Context context, ArrayList<Choice> choice) {
        super(context, 0, choice);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Choice currentPurchase = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.choice_list, parent, false);
        }

        TextView purchasePrice = (TextView) convertView.findViewById(R.id.choice_price);
        purchasePrice.setText(currentPurchase.getmPrice() + " Clicks");
        ImageView purchaseImage = (ImageView) convertView.findViewById(R.id.choice_image);
        purchaseImage.setImageResource(currentPurchase.getmImage());

        return convertView;
    }
}
