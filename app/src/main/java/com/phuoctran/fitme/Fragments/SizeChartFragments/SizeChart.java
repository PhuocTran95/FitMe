package com.phuoctran.fitme.Fragments.SizeChartFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Models.Categories;
import com.phuoctran.fitme.Util.BottomNavigationViewHelper;

public class SizeChart extends Fragment {

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.navigation_chart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.navigation_tops:
                        SizeChart.this.navigateTo(Categories.Tops);
                        return true;
                    case R.id.navigation_bottoms:
                        SizeChart.this.navigateTo(Categories.Bottoms);
                        return true;
                    case R.id.navigation_shoe:
                        SizeChart.this.navigateTo(Categories.Shoes);
                        return true;
                    case R.id.navigation_hat:
                        SizeChart.this.setFragment(new HatChart());
                        return true;
                    case R.id.navigation_ring:
                        SizeChart.this.setFragment(new RingChart());
                        return true;
                    default:
                        return false;
                }
            }
        });

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        navigateTo(Categories.Tops);
    }

    private void navigateTo(Categories categories) {
        Bundle bundle = new Bundle();
        bundle.putString("Category", categories.name());
        Fragment fragment = new SizeChartContent();
        fragment.setArguments(bundle);
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        try {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            transaction.replace(R.id.content_chart, fragment);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
