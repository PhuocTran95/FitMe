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

public class SizeCalculatorShoes extends Fragment
{
    Button btnCalculate;
    boolean isMale;

    private final double female_36 = 22.0d;
    private final double female_36_5 = 22.5d;
    private final double female_37 = 23.0d;
    private final double female_37_5 = 23.5d;
    private final double female_38 = 24.0d;
    private final double female_38_5 = 24.5d;
    private final double female_39 = 25.0d;
    private final double female_39_5 = 25.5d;
    private final double female_40 = 26.0d;
    private final double female_40_5 = 26.5d;
    private final double female_41 = 27.0d;
    private final double female_41_5 = 27.5d;

    private final double male_39_5 = 24.5d;
    private final double male_40 = 25.0d;
    private final double male_40_5 = 25.5d;
    private final double male_41 = 26.0d;
    private final double male_41_5 = 26.5d;
    private final double male_42 = 27.0d;
    private final double male_42_5 = 27.5d;
    private final double male_43 = 28.0d;
    private final double male_43_5 = 28.5d;
    private final double male_44 = 29.0d;
    private final double male_44_5 = 29.5d;
    private final double male_45 = 30.0d;
    private final double male_46 = 30.5d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_size_calculator_shoes, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_shoes);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText txtLength = (EditText) getActivity().findViewById(R.id.shoes_cm);
                RadioButton cbx = (RadioButton) getActivity().findViewById(R.id.shoes_male);

                isMale = cbx.isChecked();

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
                if (isMale)
                {
                    if (lengthIndex == 0)
                    {
                        message = getString(R.string.result_message_smallSize, "39.5 (EU)");
                    } else if (lengthIndex == 1)
                    {
                        message = "39.5 (EU)";
                    } else if (lengthIndex == 2)
                    {
                        message = "40 (EU)";
                    } else if (lengthIndex == 3)
                    {
                        message = "40.5 (EU)";
                    } else if (lengthIndex == 4)
                    {
                        message = "41 (EU)";
                    } else if (lengthIndex == 5)
                    {
                        message = "41.5 (EU)";
                    } else if (lengthIndex == 6)
                    {
                        message = "42 (EU)";
                    } else if (lengthIndex == 7)
                    {
                        message = "42.5 (EU)";
                    } else if (lengthIndex == 8)
                    {
                        message = "43 (EU)";
                    } else if (lengthIndex == 9)
                    {
                        message = "43.5 (EU)";
                    } else if (lengthIndex == 10)
                    {
                        message = "44 (EU)";
                    } else if (lengthIndex == 11)
                    {
                        message = "44.5 (EU)";
                    } else if (lengthIndex == 12)
                    {
                        message = "45 (EU)";
                    } else
                    {
                        message = getString(R.string.result_message_overSize, "45 (EU)");
                    }
                } else
                {
                    if (lengthIndex == 0)
                    {
                        message = getString(R.string.result_message_smallSize, "36 (EU)");
                    } else if (lengthIndex == 1)
                    {
                        message = "36 (EU)";
                    } else if (lengthIndex == 2)
                    {
                        message = "36.5 (EU)";
                    } else if (lengthIndex == 3)
                    {
                        message = "37 (EU)";
                    } else if (lengthIndex == 4)
                    {
                        message = "37.5 (EU)";
                    } else if (lengthIndex == 5)
                    {
                        message = "38 (EU)";
                    } else if (lengthIndex == 6)
                    {
                        message = "38.5 (EU)";
                    } else if (lengthIndex == 7)
                    {
                        message = "39 (EU)";
                    } else if (lengthIndex == 8)
                    {
                        message = "39.5 (EU)";
                    } else if (lengthIndex == 9)
                    {
                        message = "40 (EU)";
                    } else if (lengthIndex == 10)
                    {
                        message = "40.5 (EU)";
                    } else if (lengthIndex == 11)
                    {
                        message = "41 (EU)";
                    } else
                    {
                        message = getString(R.string.result_message_overSize, "41 (EU)");
                    }
                }
                MessageUtil.showMessage(getActivity(), message, checkOtherSizeCategories(lengthIndex));
            }
        });
    }

    private String checkOtherSizeCategories(int selectedIndex)
    {
        if (isMale)
        {
            if (selectedIndex > 0 && selectedIndex <= 12)
            {
                ChartDetail detail = ChartConstants.lstShoesMale.get(selectedIndex - 1);
                return String.format("%1$s (US) - %2$s (UK) - %3$s (KR) - %4$s (JP)", detail.US, detail.UK, detail.KR, detail.JP);
            } else
                return null;
        } else
        {
            if (selectedIndex > 0 && selectedIndex <= 11)
            {
                ChartDetail detail = ChartConstants.lstShoesFemale.get(selectedIndex - 1);
                return String.format("%1$s (US) - %2$s (UK) - %3$s (KR) - %4$s (JP)", detail.US, detail.UK, detail.KR, detail.JP);
            } else
                return null;
        }
    }

    private int getSizeIndexByLength(double length)
    {
        if (this.isMale)
        {
            if (length < this.male_39_5)
            {
                return 0;
            }
            if (length < this.male_40)
            {
                return 1;
            }
            if (length < this.male_40_5)
            {
                return 2;
            }
            if (length < this.male_41)
            {
                return 3;
            }
            if (length < this.male_41_5)
            {
                return 4;
            }
            if (length < this.male_42)
            {
                return 5;
            }
            if (length < this.male_42_5)
            {
                return 6;
            }
            if (length < this.male_43)
            {
                return 7;
            }
            if (length < this.male_43_5)
            {
                return 8;
            }
            if (length < this.male_44)
            {
                return 9;
            }
            if (length < this.male_44_5)
            {
                return 10;
            }
            if (length < this.male_45)
            {
                return 11;
            }
            if (length < this.male_46)
            {
                return 12;
            }
            return 13;
        } else
        {
            if (length < this.female_36)
            {
                return 0;
            }
            if (length < this.female_36_5)
            {
                return 1;
            }
            if (length < this.female_37)
            {
                return 2;
            }
            if (length < this.female_37_5)
            {
                return 3;
            }
            if (length < this.female_38)
            {
                return 4;
            }
            if (length < this.female_38_5)
            {
                return 5;
            }
            if (length < this.female_39)
            {
                return 6;
            }
            if (length < this.female_39_5)
            {
                return 7;
            }
            if (length < this.female_40)
            {
                return 8;
            }
            if (length < this.female_40_5)
            {
                return 9;
            }
            if (length < this.female_41)
            {
                return 10;
            }
            if (length < this.female_41_5)
            {
                return 11;
            }
            return 12;
        }
    }
}
