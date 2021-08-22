package com.scorp.circle4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StateAdapter extends FragmentStateAdapter {

    public StateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentChoice();
            case 1:
                return new StartFragment();
            default:
                return new UnlimitePlayFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
