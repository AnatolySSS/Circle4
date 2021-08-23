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

public class ChoiceAdapter extends ArrayAdapter<Circle> {

    public ChoiceAdapter(@NonNull Context context, ArrayList<Circle> circle) {
        super(context, 0, circle);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Circle currentPurchase = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.choice_list, parent, false);
        }

        TextView purchasePrice = (TextView) convertView.findViewById(R.id.choice_price);
        purchasePrice.setText(currentPurchase.getmPrice() + "Cl");
        ImageView purchaseImage = (ImageView) convertView.findViewById(R.id.choice_image);
        purchaseImage.setImageResource(currentPurchase.getmImage());

        return convertView;
    }
}
