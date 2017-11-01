package com.phuoctran.fitme.Fragments.SizeChartFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Models.Categories;

public class SizeChartContent extends Fragment {
    private Categories categories;
    private ViewPager mViewPager;
    private int[] tabIcons = {R.drawable.man,R.drawable.woman};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_chart_content, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.categories = Categories.valueOf(getArguments().getString("Category", "Tops"));
        this.mViewPager = (ViewPager) getActivity().findViewById(R.id.viewPager_chart);
        this.mViewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager())
        {
            public int getCount() {
                return 2;
            }

            public Fragment getItem(int position) {
                if (SizeChartContent.this.categories == Categories.Tops) {
                    if (position == 0) {
                        return new TopChartForMale();
                    }
                    return new TopChartForFemale();
                } else if (SizeChartContent.this.categories == Categories.Bottoms) {
                    if (position == 0) {
                        return new BottomChartForMale();
                    }
                    return new BottomChartForFemale();
                } else if (SizeChartContent.this.categories == Categories.Shoes) {
                    if (position == 0) {
                        return new ShoesChartForMale();
                    }
                    return new ShoesChartForFemale();
                } else {
                    return null;
                }
            }

            public CharSequence getPageTitle(int position) {
//                switch (position) {
//                    case 0 :
//                        return SizeChartContent.this.getResources().getString(R.string.male);
//                    case 1:
//                        return SizeChartContent.this.getResources().getString(R.string.female);
//                    default:
//                        return null;
//                }
                return null;
            }
        });

        String[] tabText = {getString(R.string.male),getString(R.string.female)};
        TabLayout tabLayout=(TabLayout) getActivity().findViewById(R.id.contentTab_chart);
        tabLayout.setupWithViewPager(this.mViewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++)
        {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.layout_tab_item, null);

            ImageView imageView = (ImageView) view1.findViewById(R.id.tab_item_image);
            imageView.setImageResource(tabIcons[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            TextView txt = (TextView) view1.findViewById(R.id.tab_item_text);
            txt.setText(tabText[i]);

            tabLayout.getTabAt(i).setCustomView(view1);
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                View view1 = tab.getCustomView();

                TextView txt = (TextView) view1.findViewById(R.id.tab_item_text);
                txt.setTextColor(getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
                View view1 = tab.getCustomView();

                TextView txt = (TextView) view1.findViewById(R.id.tab_item_text);
                txt.setTextColor(getResources().getColor(R.color.subColor));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                View view1 = tab.getCustomView();

                TextView txt = (TextView) view1.findViewById(R.id.tab_item_text);
                txt.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });
        tabLayout.getTabAt(0).select();
    }
}
