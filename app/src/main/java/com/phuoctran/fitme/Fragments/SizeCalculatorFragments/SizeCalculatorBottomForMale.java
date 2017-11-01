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
import android.widget.RadioButton;
import android.widget.Toast;

import com.phuoctran.fitme.Models.ChartConstants;
import com.phuoctran.fitme.Models.ChartDetail;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.MessageUtil;

public class SizeCalculatorBottomForMale extends Fragment
{
    Button btnCalculate;
    private final double hips_S = 95.0d;
    private final double hips_M = 99.0d;
    private final double hips_L = 103.0d;
    private final double hips_XL = 107.0d;
    private final double hips_XXL = 111.0d;
    private final double hips_3XL = 115.0d;
    private final double hips_4XL = 119.0d;

    private final double waist_S = 76.0d;
    private final double waist_M = 80.0d;
    private final double waist_L = 84.0d;
    private final double waist_XL = 89.0d;
    private final double waist_XXL = 95.5d;
    private final double waist_3XL = 102.0d;
    private final double waist_4XL = 108.0d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_bottom_for_male, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_bottom_male);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtWaist = (EditText) getActivity().findViewById(R.id.bottom_male_waist);
                EditText txtHips = (EditText) getActivity().findViewById(R.id.bottom_male_hips);

                if (txtWaist.getText().toString().equals("") && txtHips.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), getResources().getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                int waistIndex;
                int hipsIndex;
                int selectedIndex;

                try
                {
                    waistIndex = getSizeIndexByWaist(Double.parseDouble(txtWaist.getText().toString()));
                } catch (Exception e)
                {
                    waistIndex = 0;
                }
                try
                {
                    hipsIndex = getSizeIndexByHips(Double.parseDouble(txtHips.getText().toString()));
                } catch (Exception e2)
                {
                    hipsIndex = 0;
                }

                //Get the largest index
                if (waistIndex < hipsIndex)
                {
                    selectedIndex = hipsIndex;
                } else
                {
                    selectedIndex = waistIndex;
                }

                //Minus one size if Asian
                if (((RadioButton) getActivity().findViewById(R.id.bottom_male_asian)).isChecked() && selectedIndex > 0 && selectedIndex < 7)
                {
                    selectedIndex--;
                }

                String message = "";
                if (selectedIndex == 0)
                {
                    message = "XS";
                } else if (selectedIndex == 1)
                {
                    message = "S";
                } else if (selectedIndex == 2)
                {
                    message = "M";
                } else if (selectedIndex == 3)
                {
                    message = "L";
                } else if (selectedIndex == 4)
                {
                    message = "XL";
                } else if (selectedIndex == 5)
                {
                    message = "XXL";
                } else
                {
                    message = getString(R.string.result_message_overSize, "XXL");
                }
                MessageUtil.showMessage(getActivity(), message, checkOtherSizeCategories(selectedIndex));
            }
        });
    }

    private String checkOtherSizeCategories(int selectedIndex)
    {
        if (selectedIndex <= 5)
        {
            ChartDetail detail = ChartConstants.lstBottomsMale.get(selectedIndex);
            return String.format("%1$s (US) - %2$s (UK) - %3$s (EU) - %4$s (KR) - %5$s (JP)", detail.US, detail.UK, detail.EU, detail.KR, detail.JP);
        } else
            return null;
    }

    private int getSizeIndexByWaist(double waist)
    {
        if (waist < this.waist_S)
        {
            return 0;
        }
        if (waist < this.waist_M)
        {
            return 1;
        }
        if (waist < this.waist_L)
        {
            return 2;
        }
        if (waist < this.waist_XL)
        {
            return 3;
        }
        if (waist < this.waist_XXL)
        {
            return 4;
        }
        if (waist < this.waist_3XL)
        {
            return 5;
        }
        if (waist < this.waist_4XL)
        {
            return 6;
        }
        return 7;
    }

    private int getSizeIndexByHips(double hips)
    {
        if (hips < this.hips_S)
        {
            return 0;
        }
        if (hips < this.hips_M)
        {
            return 1;
        }
        if (hips < this.hips_L)
        {
            return 2;
        }
        if (hips < this.hips_XL)
        {
            return 3;
        }
        if (hips < this.hips_XXL)
        {
            return 4;
        }
        if (hips < this.hips_3XL)
        {
            return 5;
        }
        if (hips < this.hips_4XL)
        {
            return 6;
        }
        return 7;
    }
}
