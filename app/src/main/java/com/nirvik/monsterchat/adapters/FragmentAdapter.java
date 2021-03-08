package com.nirvik.monsterchat.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nirvik.monsterchat.fragments.CallFragment;
import com.nirvik.monsterchat.fragments.ChatFragment;
import com.nirvik.monsterchat.fragments.StatusFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChatFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new CallFragment();
            default:
                return new ChatFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String a=null;
       if(position==0)
       {
           a="CHATS";
       }
        if(position==1)
        {
            a="STATUS";
        }
        if(position==2)
        {
            a="CALLS";
        }

return a;
    }
}
