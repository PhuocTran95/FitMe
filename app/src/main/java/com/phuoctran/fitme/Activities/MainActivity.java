package com.phuoctran.fitme.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.phuoctran.fitme.Fragments.SizeCalculatorFragments.SizeCalculator;
import com.phuoctran.fitme.Fragments.SizeChartFragments.SizeChart;
import com.phuoctran.fitme.Fragments.SizeListFragments.SizeListMain;
import com.phuoctran.fitme.Models.ChartConstants;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Databases.Db;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    String[] langArr;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Db.InitDb(this,null);
        Db.db.GetData("Select * from SizeType");
        setDefaultLang();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);

        ChartConstants.setData();

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle((int) R.string.app_name);
        this.toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(this.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener()
        {
            @Override
            public void onBackStackChanged()
            {
                try
                {
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    Integer index = Integer.valueOf(Integer.parseInt(fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName()));
                    navigationView.setCheckedItem(index.intValue());
                    MainActivity.this.setToolbarTitle(index.intValue());
                }
                catch (Exception ex){
                    toolbar.setTitle(getString(R.string.nav_left_list));
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1)
        {
            super.onBackPressed();
        } else
        {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_settings)
        {
            showLanguaChooser();
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void showLanguaChooser()
    {
//        this.langArr = new String[]{getResources().getString(R.string.lang_Eng), getResources().getString(R.string.lang_Vie), getResources().getString(R.string.lang_Kor), getString(R.string.russian)};
        this.langArr = new String[]{"English", "Tiếng Việt", "한국어", "Русский"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(this.langArr, getLangNow(), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                setLangNow(which);
                dialog.cancel();
            }
        });
        builder.setTitle(getResources().getString(R.string.lang_chooserString));
        builder.create().show();
    }

    private void setDefaultLang()
    {
        int index = getLangNow();
        if (index >= 0)
        {
            changeLocale(index);
        } else
        {
            Locale current = getResources().getConfiguration().locale;  //Device
            String s = current.toString();
            this.sharedPreferences = getSharedPreferences(getString(R.string.sharedPreferences), 0);
            SharedPreferences.Editor edt = this.sharedPreferences.edit();
            edt.putInt(getResources().getString(R.string.sharedPreferences_Lang), getIndexFromLocale(s));
            edt.commit();
            getLangNow();
        }
        setContentView(R.layout.activity_main);
    }

    private void changeLocale(int index)
    {
        String str = index == 0 ? "en" : index == 1 ? "vi" : index == 2 ? "ko" : "ru";
        Locale locate = new Locale(str);
        Configuration configuration = new Configuration();
        configuration.locale = locate;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }

    private int getIndexFromLocale(String s)
    {
        switch (s)
        {
            case "vi_VN":
                return 1;
            case "ko_KR":
                return 2;
            case "ru_RU":
                return 3;
            default:
                return 0;
        }
    }

    private void setLangNow(int index)
    {
        this.sharedPreferences = getSharedPreferences(getResources().getString(R.string.sharedPreferences), 0);
        SharedPreferences.Editor edt = this.sharedPreferences.edit();
        edt.putInt(getResources().getString(R.string.sharedPreferences_Lang), index);
        edt.commit();
        changeLocale(index);
        setContentView(R.layout.activity_main);
    }

    private int getLangNow()
    {
        this.sharedPreferences = getSharedPreferences(getResources().getString(R.string.sharedPreferences), 0);
        return this.sharedPreferences.getInt(getResources().getString(R.string.sharedPreferences_Lang), -1);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        setToolbarTitle(id);
        if (id == R.id.nav_left_calculator)
        {
            setFragment(new SizeCalculator(), id);
        } else if (id == R.id.nav_left_table)
        {
            setFragment(new SizeChart(), id);
        } else if (id == R.id.nav_left_list)
        {
            setFragment(new SizeListMain(), id);
        } else if (id == R.id.nav_left_about)
        {
            showAbout();
        } else if (id == R.id.nav_left_lang)
        {
            showLanguaChooser();
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    private void showAbout()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_about);
        Button btnRate = (Button) dialog.findViewById(R.id.btn_about_rate);
        ((Button) dialog.findViewById(R.id.btn_about_ok)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.cancel();
            }
        });
        btnRate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
    }

    private void setFragment(Fragment fragment, int id)
    {
        try
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);

//            Log.d("123", R.id.container+"");

            //Checking fragment duplicate
            if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            {
                String name = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
                if (name.equals(String.valueOf(id)) == false)
                    transaction.addToBackStack(String.valueOf(id));
            } else transaction.addToBackStack(String.valueOf(id));
            transaction.commit();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    void setToolbarTitle(int menuIndex)
    {
        switch (menuIndex)
        {
            case R.id.nav_left_calculator:
            {
                this.toolbar.setTitle(getString(R.string.nav_left_calculator));
                return;
            }
            case R.id.nav_left_table:
            {
                this.toolbar.setTitle(getString(R.string.nav_left_table));
                return;
            }
            case R.id.nav_left_list:
            {
                this.toolbar.setTitle(getString(R.string.nav_left_list));
                return;
            }
        }
    }
}
