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

public class SizeCalculatorTopForFemale extends Fragment
{
    Button btnCalculate;

    private final double bust_XS = 82.0d;
    private final double bust_S = 86.5d;
    private final double bust_M = 90.0d;
    private final double bust_L = 96.0d;
    private final double bust_XL = 102.0d;
    private final double bust_XXL = 108.0d;
    private final double bust_3XL = 114.0d;

    private final double hip_XS = 90.0d;
    private final double hip_S = 94.0d;
    private final double hip_M = 98.0d;
    private final double hip_L = 104.0d;
    private final double hip_XL = 110.0d;
    private final double hip_XXL = 116.0d;
    private final double hip_3XL = 122.0d;

    private final double waist_XS = 62.0d;
    private final double waist_S = 66.0d;
    private final double waist_M = 70.0d;
    private final double waist_L = 76.0d;
    private final double waist_XL = 85.0d;
    private final double waist_XXL = 94.0d;
    private final double waist_3XL = 103.0d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_top_for_female, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_top_female);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtBust = (EditText) getActivity().findViewById(R.id.top_female_bust);
                EditText txtWaist = (EditText) getActivity().findViewById(R.id.top_female_waist);
                EditText txtHip = (EditText) getActivity().findViewById(R.id.top_female_hip);

                if (txtBust.getText().toString().equals("") && txtWaist.getText().toString().equals("") && txtHip.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), getResources().getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                int bustIndex;
                int waistIndex;
                int hipIndex;

                try
                {
                    bustIndex = getSizeIndexByBust(Double.parseDouble(txtBust.getText().toString()));
                } catch (Exception e)
                {
                    bustIndex = 0;
                }
                try
                {
                    waistIndex = getSizeIndexByWaist(Double.parseDouble(txtWaist.getText().toString()));
                } catch (Exception e2)
                {
                    waistIndex = 0;
                }
                try
                {
                    hipIndex = getSizeIndexByHip(Double.parseDouble(txtHip.getText().toString()));
                } catch (Exception e3)
                {
                    hipIndex = 0;
                }

                ////Get the largest index => Minus one size if Asian
                int selectedIndex = getMaxIndex(bustIndex, waistIndex, hipIndex);
                if (((RadioButton) getActivity().findViewById(R.id.top_female_asian)).isChecked() && selectedIndex > 0 && selectedIndex < 7)
                {
                    selectedIndex--;
                }

                String message = "";
                if (selectedIndex == 0)
                {
                    message = "XXS";
                } else if (selectedIndex == 1)
                {
                    message = "XS";
                } else if (selectedIndex == 2)
                {
                    message = "S";
                } else if (selectedIndex == 3)
                {
                    message = "M";
                } else if (selectedIndex == 4)
                {
                    message = "L";
                } else if (selectedIndex == 5)
                {
                    message = "XL";
                } else
                {
                    message = getString(R.string.result_message_overSize, "XL");
                }
                MessageUtil.showMessage(getActivity(), message,checkOtherSizeCategories(selectedIndex));
            }
        });
    }

    private String checkOtherSizeCategories(int selectedIndex)
    {
        if (selectedIndex <= 5)
        {
            ChartDetail detail = ChartConstants.lstTopsFemale.get(selectedIndex);
            return String.format("%1$s (US) - %2$s (UK) - %3$s (EU) - %4$s (KR) - %5$s (JP)", detail.US, detail.UK, detail.EU, detail.KR, detail.JP);
        } else
            return null;
    }

    private int getMaxIndex(int bustIndex, int waistIndex, int hipIndex)
    {
        int max = 0;
        if (bustIndex > 0)
        {
            max = bustIndex;
        }
        if (waistIndex > max)
        {
            max = waistIndex;
        }
        if (hipIndex > max)
        {
            return hipIndex;
        }
        return max;
    }

    private int getSizeIndexByBust(double bust)
    {
        if (bust < this.bust_XS)
        {
            return 0;
        }
        if (bust < this.bust_S)
        {
            return 1;
        }
        if (bust < this.bust_M)
        {
            return 2;
        }
        if (bust < this.bust_L)
        {
            return 3;
        }
        if (bust < this.bust_XL)
        {
            return 4;
        }
        if (bust < this.bust_XXL)
        {
            return 5;
        }
        if (bust < this.bust_3XL)
        {
            return 6;
        }
        return 7;
    }

    private int getSizeIndexByWaist(double waist)
    {
        if (waist < this.waist_XS)
        {
            return 0;
        }
        if (waist < this.waist_S)
        {
            return 1;
        }
        if (waist < this.waist_M)
        {
            return 2;
        }
        if (waist < this.waist_L)
        {
            return 3;
        }
        if (waist < this.waist_XL)
        {
            return 4;
        }
        if (waist < this.waist_XXL)
        {
            return 5;
        }
        if (waist < this.waist_3XL)
        {
            return 6;
        }
        return 7;
    }

    private int getSizeIndexByHip(double hip)
    {
        if (hip < this.hip_XS)
        {
            return 0;
        }
        if (hip < this.hip_S)
        {
            return 1;
        }
        if (hip < this.hip_M)
        {
            return 2;
        }
        if (hip < this.hip_L)
        {
            return 3;
        }
        if (hip < this.hip_XL)
        {
            return 4;
        }
        if (hip < this.hip_XXL)
        {
            return 5;
        }
        if (hip < this.hip_3XL)
        {
            return 6;
        }
        return 7;
    }
}
