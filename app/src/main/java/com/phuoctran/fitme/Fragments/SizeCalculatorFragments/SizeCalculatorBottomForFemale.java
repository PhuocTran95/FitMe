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

public class SizeCalculatorBottomForFemale extends Fragment
{
    Button btnCalculate;
    private final double hips_S = 94.0d;
    private final double hips_M = 98.0d;
    private final double hips_L = 104.0d;
    private final double hips_XL = 110.0d;
    private final double hips_XXL = 116.0d;
    private final double hips_3XL = 122.0d;

    private final double waist_S = 66.0d;
    private final double waist_M = 70.0d;
    private final double waist_L = 76.0d;
    private final double waist_XL = 82.0d;
    private final double waist_XXL = 88.0d;
    private final double waist_3XL = 94.0d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_bottom_for_female, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_bottom_female);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtWaist = (EditText) getActivity().findViewById(R.id.bottom_female_waist);
                EditText txtHips = (EditText) getActivity().findViewById(R.id.bottom_female_hips);

                if (txtWaist.getText().toString().equals("") && txtHips.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                int waistIndex;
                int hipsIndex;
                int selectedIndex;

                try
                {
                    waistIndex = SizeCalculatorBottomForFemale.this.getSizeIndexByWaist(Double.parseDouble(txtWaist.getText().toString()));
                } catch (Exception e)
                {
                    waistIndex = 0;
                }
                try
                {
                    hipsIndex = SizeCalculatorBottomForFemale.this.getSizeIndexByHips(Double.parseDouble(txtHips.getText().toString()));
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
                if (((RadioButton) getActivity().findViewById(R.id.bottom_female_asian)).isChecked() && selectedIndex > 0 && selectedIndex < 6)
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
                } else
                {
                    message = getString(R.string.result_message_overSize, "XL");
                }
                MessageUtil.showMessage(getActivity(), message, checkOtherSizeCategories(selectedIndex));
            }
        });
    }

    private String checkOtherSizeCategories(int selectedIndex)
    {
        if (selectedIndex <= 4)
        {
            ChartDetail detail = ChartConstants.lstBottomsFemale.get(selectedIndex);
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
        return 6;
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
        return 6;
    }
}
