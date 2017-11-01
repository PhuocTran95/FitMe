package com.phuoctran.fitme.Util;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.phuoctran.fitme.R;

import java.lang.reflect.Field;

/**
 * Created by VN-PC on 2017/05/25.
 */

public class BottomNavigationViewHelper {

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);

//                //Remove label
//                TextView smallText = (TextView) item.findViewById(R.id.smallLabel);
//                smallText.setVisibility(View.GONE);
//                //TextView largeText = (TextView) menuItemView.findViewById(R.id.largeLabel);
//                ImageView icon = (ImageView) item.findViewById(R.id.icon);
//                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) icon.getLayoutParams();
//                params.gravity = Gravity.CENTER;
//                icon.setLayoutParams(params);

                //noinspection RestrictedApi
                item.setShiftingMode(true);

                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}

