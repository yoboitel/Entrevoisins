package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {


    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            Log.d("MSG", "My Neighbours");
            return NeighbourFragment.newInstance();
        }
        else if (position == 1){
            Log.d("MSG", "Favs Neighbours");
            return FavNeighbourFragment.newInstance();
        }
        else return NeighbourFragment.newInstance();
    }


    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}