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

import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.MessageUtil;

public class SizeCalculatorBottomForKid extends Fragment
{
    Button btnCalculate;
    RadioButton cbxIsMale;
    private final double hips_7 = 71.0d;
    private final double hips_7_male = 67.0d;
    private final double hips_8 = 74.0d;
    private final double hips_8_male = 70.0d;
    private final double hips_10 = 77.0d;
    private final double hips_10_male = 75.0d;
    private final double hips_12 = 82.0d;
    private final double hips_12_male = 80.0d;
    private final double hips_14 = 87.0d;
    private final double hips_14_male = 85.0d;
    private final double hips_1x = 93.0d;
    private final double hips_1x_male = 90.0d;
    private final double hips_2x = 99.0d;
    private final double hips_2x_male = 95.0d;

    private final double waist_7 = 58.0d;
    private final double waist_7_male = 59.0d;
    private final double waist_8 = 60.0d;
    private final double waist_8_male = 63.5d;
    private final double waist_10 = 62.0d;
    private final double waist_10_male = 66.0d;
    private final double waist_12 = 65.0d;
    private final double waist_12_male = 68.0d;
    private final double waist_14 = 67.0d;
    private final double waist_14_male = 71.0d;
    private final double waist_1x = 70.0d;
    private final double waist_1x_male = 74.0d;
    private final double waist_2x = 73.0d;
    private final double waist_2x_male = 77.0d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_bottom_for_kid, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_bottom_kid);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtWaist = (EditText) getActivity().findViewById(R.id.bottom_kid_waist);
                EditText txtHips = (EditText) getActivity().findViewById(R.id.bottom_kid_hips);
                cbxIsMale = (RadioButton) getActivity().findViewById(R.id.bottom_kid_male);

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
                if (((RadioButton) getActivity().findViewById(R.id.bottom_kid_asian)).isChecked() && selectedIndex > 0 && selectedIndex < 7)
                {
                    selectedIndex--;
                }

                String message = "";
                if (selectedIndex == 0)
                {
                    message = getString(R.string.result_message_smallSize, "7 (US)");
                } else if (selectedIndex == 1)
                {
                    message = "7 (US)";
                } else if (selectedIndex == 2)
                {
                    message = "8 (US)";
                } else if (selectedIndex == 3)
                {
                    message = "10 (US)";
                } else if (selectedIndex == 4)
                {
                    message = "12 (US)";
                } else if (selectedIndex == 5)
                {
                    message = "14 (US)";
                } else
                {
                    message = getString(R.string.result_message_overSize, "14 (US)");
                }
                MessageUtil.showMessage(getActivity(), message, null);
            }
        });
    }

    private int getSizeIndexByWaist(double waist)
    {
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_7_male : this.waist_7))
        {
            return 0;
        }
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_8_male : this.waist_8))
        {
            return 1;
        }
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_10_male : this.waist_10))
        {
            return 2;
        }
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_12_male : this.waist_12))
        {
            return 3;
        }
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_14_male : this.waist_14))
        {
            return 4;
        }
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_1x_male : this.waist_1x))
        {
            return 5;
        }
        if (waist < (this.cbxIsMale.isChecked() ? this.waist_2x_male : this.waist_2x))
        {
            return 6;
        }
        return 7;
    }

    private int getSizeIndexByHips(double hips)
    {
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_7_male : this.hips_7))
        {
            return 0;
        }
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_8_male : this.hips_8))
        {
            return 1;
        }
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_10_male : this.hips_10))
        {
            return 2;
        }
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_12_male : this.hips_12))
        {
            return 3;
        }
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_14_male : this.hips_14))
        {
            return 4;
        }
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_1x_male : this.hips_1x))
        {
            return 5;
        }
        if (hips < (this.cbxIsMale.isChecked() ? this.hips_2x_male : this.hips_2x))
        {
            return 6;
        }
        return 7;
    }
}
