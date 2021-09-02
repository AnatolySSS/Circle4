package com.scorp.circle4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CircleAdapter extends ArrayAdapter<Circle> {

    final ArrayList<Circle> circles = ((GlobalVariables) getContext().getApplicationContext()).circles;

    public CircleAdapter(@NonNull Context context, ArrayList<Circle> circle) {
        super(context, 0, circle);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Circle currentCircle = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.choice_list, parent, false);
        }

        TextView circlePrice = (TextView) convertView.findViewById(R.id.choice_price);
        circlePrice.setText(currentCircle.getmPrice() + "Cl");
        ImageView circleImage = (ImageView) convertView.findViewById(R.id.choice_image);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[1024 * 32];

        Bitmap bm = BitmapFactory.decodeByteArray(currentCircle.getmImage(), 0, currentCircle.getmImage().length, options);
        bm = Bitmap.createScaledBitmap(bm, circleImage.getMaxWidth(),circleImage.getMaxHeight(),true);
        circleImage.setImageBitmap(bm);

        if (circles.get(position).isBought()) {
            circleImage.setAlpha((float) 1.0);
            circlePrice.setVisibility(View.GONE);
        } else {
            circleImage.setAlpha((float) 0.5);
            circlePrice.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
