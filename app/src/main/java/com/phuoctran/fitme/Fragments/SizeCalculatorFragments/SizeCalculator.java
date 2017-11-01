package com.phuoctran.fitme.Fragments.SizeCalculatorFragments;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;

import com.phuoctran.fitme.Models.Categories;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.BottomNavigationViewHelper;

public class SizeCalculator extends Fragment
{

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_size_calculator, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                item.setChecked(true);
                hideKeyboard();
                switch (item.getItemId())
                {
                    case R.id.navigation_tops:
                        SizeCalculator.this.navigateTo(Categories.Tops);
                        return true;
                    case R.id.navigation_bottoms:
                        SizeCalculator.this.navigateTo(Categories.Bottoms);
                        return true;
                    case R.id.navigation_shoe:
                        SizeCalculator.this.setFragment(new SizeCalculatorShoes());
                        return true;
                    case R.id.navigation_hat:
                        SizeCalculator.this.setFragment(new SizeCalculatorHat());
                        return true;
                    case R.id.navigation_ring:
                        SizeCalculator.this.setFragment(new SizeCalculatorRing());
                        return true;
                    default:
                        return false;
                }
            }
        });

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        navigateTo(Categories.Tops);
    }

    public void hideKeyboard()
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void navigateTo(Categories categories)
    {
        Bundle bundle = new Bundle();
        bundle.putString("Category", categories.name());
        Fragment fragment = new SizeCalculatorContent();
        fragment.setArguments(bundle);
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment)
    {
        try
        {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
