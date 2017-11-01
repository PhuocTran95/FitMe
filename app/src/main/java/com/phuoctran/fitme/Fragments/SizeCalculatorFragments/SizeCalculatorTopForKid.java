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

public class SizeCalculatorTopForKid extends Fragment
{
    Button btnCalculate;
    RadioButton cbxIsMale;
    private final double chest_7_female = 67.0d;
    private final double chest_7_male = 65.0d;
    private final double chest_8_female = 70.0d;
    private final double chest_8_male = 68.0d;
    private final double chest_10_female = 74.0d;
    private final double chest_10_male = 72.0d;
    private final double chest_12_female = 77.0d;
    private final double chest_12_male = 76.0d;
    private final double chest_14_female = 81.0d;
    private final double chest_14_male = 81.0d;
    private final double chest_1x_male = 85.0d;
    private final double chest_2x_male = 89.0d;

    private final double height_7_male = 127.0d;
    private final double height_8_female = 134.0d;
    private final double height_8_male = 132.0d;
    private final double height_10_female = 139.0d;
    private final double height_10_male = 137.0d;
    private final double height_12_female = 147.0d;
    private final double height_12_male = 147.0d;
    private final double height_14_female = 152.0d;
    private final double height_14_male = 154.0d;
    private final double height_1x_female = 160.0d;
    private final double height_1x_male = 162.0d;
    private final double height_2x_male = 165.0d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_top_for_kid, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_top_kid);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtHeight = (EditText) getActivity().findViewById(R.id.top_kid_height);
                EditText txtChest = (EditText) getActivity().findViewById(R.id.top_kid_chest);
                cbxIsMale = (RadioButton) getActivity().findViewById(R.id.top_kid_male);
                if (txtHeight.getText().toString().equals("") && txtChest.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), getResources().getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                int heightIndex;
                int chestIndex;
                int selectedIndex;

                try
                {
                    heightIndex = getSizeIndexByHeight(Double.parseDouble(txtHeight.getText().toString()));
                } catch (Exception e)
                {
                    heightIndex = 0;
                }
                try
                {
                    chestIndex = getSizeIndexByChest(Double.parseDouble(txtChest.getText().toString()));
                } catch (Exception e2)
                {
                    chestIndex = 0;
                }

                //Get the largest index
                if (heightIndex < chestIndex)
                {
                    selectedIndex = chestIndex;
                } else
                {
                    selectedIndex = heightIndex;
                }

                //Minus one size if Asian
                if (((RadioButton) getActivity().findViewById(R.id.top_kid_asian)).isChecked() && selectedIndex > 0 && selectedIndex < 7)
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
                MessageUtil.showMessage(getActivity(), message,null);
            }
        });
    }

    private int getSizeIndexByChest(double chest)
    {
        if (chest < (this.cbxIsMale.isChecked() ? this.chest_7_male : this.chest_7_female))
        {
            return 0;
        }
        if (chest < (this.cbxIsMale.isChecked() ? this.chest_8_male : this.chest_8_female))
        {
            return 1;
        }
        if (chest < (this.cbxIsMale.isChecked() ? this.chest_10_male : this.chest_10_female))
        {
            return 2;
        }
        if (chest < (this.cbxIsMale.isChecked() ? this.chest_12_male : this.chest_12_female))
        {
            return 3;
        }
        if (chest < (this.cbxIsMale.isChecked() ? this.chest_14_male : this.chest_14_female))
        {
            return 4;
        }
        if (chest < this.chest_1x_male)
        {
            return 5;
        }
        if (chest < this.chest_2x_male)
        {
            return 6;
        }
        return 7;
    }

    private int getSizeIndexByHeight(double height)
    {
        if (height < this.height_7_male)
        {
            return 0;
        }
        if (height < (this.cbxIsMale.isChecked() ? this.height_8_male : this.height_8_female))
        {
            return 1;
        }
        if (height < (this.cbxIsMale.isChecked() ? this.height_10_male : this.height_10_female))
        {
            return 2;
        }
        if (height < (this.cbxIsMale.isChecked() ? this.height_12_male : this.height_12_female))
        {
            return 3;
        }
        if (height < (this.cbxIsMale.isChecked() ? this.height_14_male : this.height_14_female))
        {
            return 4;
        }
        if (height < (this.cbxIsMale.isChecked() ? this.height_1x_male : this.height_1x_female))
        {
            return 5;
        }
        if (height < this.height_2x_male)
        {
            return 6;
        }
        return 7;
    }
}
