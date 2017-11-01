package com.phuoctran.fitme.Fragments.SizeCalculatorFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phuoctran.fitme.Models.ChartConstants;
import com.phuoctran.fitme.Models.ChartDetail;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.MessageUtil;

public class SizeCalculatorHat extends Fragment
{
    Button btnCalculate;

    private final double S = 54.0d;
    private final double S_M = 54.9d;
    private final double M = 55.9d;
    private final double M_L = 56.8d;
    private final double L = 57.8d;
    private final double L_XL = 58.7d;
    private final double XL = 59.7d;
    private final double XL_XXL = 60.6d;
    private final double XXL = 61.6d;
    private final double XXXL = 62.5d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_hat, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_hat);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtLength = (EditText) getActivity().findViewById(R.id.hat_cm);
                if (txtLength.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), getResources().getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                int lengthIndex;
                try
                {
                    lengthIndex = getSizeIndexByLength(Double.parseDouble(txtLength.getText().toString()));
                } catch (Exception e)
                {
                    lengthIndex = 0;
                }

                String message = "";
                if (lengthIndex == 0)
                {
                    message = getString(R.string.result_message_smallSize, "S");
                } else if (lengthIndex == 1)
                {
                    message = "S";
                } else if (lengthIndex == 2)
                {
                    message = "S/M";
                } else if (lengthIndex == 3)
                {
                    message = "M";
                } else if (lengthIndex == 4)
                {
                    message = "M/L";
                } else if (lengthIndex == 5)
                {
                    message = "L";
                } else if (lengthIndex == 6)
                {
                    message = "L/XL";
                } else if (lengthIndex == 7)
                {
                    message = "XL";
                } else if (lengthIndex == 8)
                {
                    message = "XL/XXL";
                } else if (lengthIndex == 9)
                {
                    message = "XXL";
                } else
                {
                    message = getString(R.string.result_message_overSize, "XXL");
                }
                MessageUtil.showMessage(getActivity(), message, checkOtherSizeCategories(lengthIndex));
            }
        });
    }

    private String checkOtherSizeCategories(int selectedIndex)
    {
        if (selectedIndex > 0 && selectedIndex <= 9)
        {
            ChartDetail detail = ChartConstants.lstHat.get(selectedIndex - 1);
            return String.format("%1$s (US) - %2$s (UK)", detail.US, detail.UK);
        } else
            return null;
    }

    private int getSizeIndexByLength(double length)
    {
        if (length < S)
        {
            return 0;
        }
        if (length < S_M)
        {
            return 1;
        }
        if (length < M)
        {
            return 2;
        }
        if (length < M_L)
        {
            return 3;
        }
        if (length < L)
        {
            return 4;
        }
        if (length < L_XL)
        {
            return 5;
        }
        if (length < XL)
        {
            return 6;
        }
        if (length < XL_XXL)
        {
            return 7;
        }
        if (length < XXL)
        {
            return 8;
        }
        if (length < XXXL)
        {
            return 9;
        }
        return 10;
    }
}
