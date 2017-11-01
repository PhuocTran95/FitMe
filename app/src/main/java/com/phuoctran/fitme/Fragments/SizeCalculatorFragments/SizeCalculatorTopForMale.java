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

import com.phuoctran.fitme.Models.ChartDetail;
import com.phuoctran.fitme.Models.ChartConstants;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.MessageUtil;

public class SizeCalculatorTopForMale extends Fragment
{
    Button btnCalculate;

    private final double chest_S = 89.0d;
    private final double chest_M = 94.0d;
    private final double chest_L = 99.0d;
    private final double chest_XL = 104.0d;
    private final double chest_XXL = 109.0d;
    private final double chest_3XL = 114.0d;
    private final double chest_4XL = 119.0d;

    private final double neck_S = 37.5d;
    private final double neck_M = 39.5d;
    private final double neck_L = 42.0d;
    private final double neck_XL = 44.0d;
    private final double neck_XXL = 45.5d;
    private final double neck_3XL = 47.5d;
    private final double neck_4XL = 49.0d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_size_calculator_top_for_male, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_top_male);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtNeck = (EditText) getActivity().findViewById(R.id.top_male_neck);
                EditText txtChest = (EditText) getActivity().findViewById(R.id.top_male_chest);

                if (txtNeck.getText().toString().equals("") && txtChest.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), getResources().getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                int neckIndex;
                int chestIndex;
                int selectedIndex;
                try
                {
                    neckIndex = getSizeIndexByNeck(Double.parseDouble(txtNeck.getText().toString()));
                } catch (Exception e)
                {
                    neckIndex = 0;
                }
                try
                {
                    chestIndex = getSizeIndexByChest(Double.parseDouble(txtChest.getText().toString()));
                } catch (Exception e2)
                {
                    chestIndex = 0;
                }

                //Get the largest index
                if (neckIndex < chestIndex)
                {
                    selectedIndex = chestIndex;
                } else
                {
                    selectedIndex = neckIndex;
                }

                //Minus one size if Asian
                if (((RadioButton) getActivity().findViewById(R.id.top_male_asian)).isChecked() && selectedIndex > 0 && selectedIndex < 7)
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
            ChartDetail detail = ChartConstants.lstTopsMale.get(selectedIndex);
            return String.format("%1$s (US) - %2$s (UK) - %3$s (EU) - %4$s (KR) - %5$s (JP)", detail.US, detail.UK, detail.EU, detail.KR, detail.JP);
        } else
            return null;
    }


    private int getSizeIndexByChest(double chest)
    {
        if (chest < this.chest_S)
        {
            return 0;
        }
        if (chest < this.chest_M)
        {
            return 1;
        }
        if (chest < this.chest_L)
        {
            return 2;
        }
        if (chest < this.chest_XL)
        {
            return 3;
        }
        if (chest < this.chest_XXL)
        {
            return 4;
        }
        if (chest < this.chest_3XL)
        {
            return 5;
        }
        if (chest < this.chest_4XL)
        {
            return 6;
        }
        return 7;
    }

    private int getSizeIndexByNeck(double neck)
    {
        if (neck < this.neck_S)
        {
            return 0;
        }
        if (neck < this.neck_M)
        {
            return 1;
        }
        if (neck < this.neck_L)
        {
            return 2;
        }
        if (neck < this.neck_XL)
        {
            return 3;
        }
        if (neck < this.neck_XXL)
        {
            return 4;
        }
        if (neck < this.neck_3XL)
        {
            return 5;
        }
        if (neck < this.neck_4XL)
        {
            return 6;
        }
        return 7;
    }
}
