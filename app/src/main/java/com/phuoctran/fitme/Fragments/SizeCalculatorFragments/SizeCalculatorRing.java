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
import com.phuoctran.fitme.BuildConfig;
import com.phuoctran.fitme.Models.ChartConstants;
import com.phuoctran.fitme.Models.ChartDetail;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.MessageUtil;

public class SizeCalculatorRing extends Fragment {
    Button btnCalculate;

    private final double size_3=44.2d;
    private final double size_3_5=44.8d;
    private final double size_4=46.1d;
    private final double size_4_5=47.4d;
    private final double size_5=48.0d;
    private final double size_5_5=49.3d;
    private final double size_6=50.6d;
    private final double size_6_5=51.2d;
    private final double size_6_5_2=52.5d;
    private final double size_7=53.1d;
    private final double size_7_2=54.4d;
    private final double size_7_5=55.7d;
    private final double size_8=56.3d;
    private final double size_8_5=57.6d;
    private final double size_9=58.9d;
    private final double size_9_5=59.5d;
    private final double size_10=60.8d;
    private final double size_10_5=62.1d;
    private final double size_10_5_2=62.7d;
    private final double size_11=64.0d;
    private final double size_11_5=64.6d;
    private final double size_12=65.9d;
    private final double size_13=67.2d;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_size_calculator_ring, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btnCalculate = (Button) getActivity().findViewById(R.id.btn_ring);
        this.btnCalculate.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                EditText txtLength = (EditText) getActivity().findViewById(R.id.ring_cm);
                if (txtLength.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.result_message_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                int lengthIndex;
                try {
                    lengthIndex = getSizeIndexByLength(Double.parseDouble(txtLength.getText().toString()));
                } catch (Exception e) {
                    lengthIndex = 0;
                }

                String message = "";
                if (lengthIndex == 0) {
                    message = getString(R.string.result_message_smallSize, "3 (US)");
                } else if (lengthIndex == 1) {
                    message = "3 (US)";
                } else if (lengthIndex == 2) {
                    message = "3.5 (US)";
                } else if (lengthIndex == 3) {
                    message = "4 (US)";
                } else if (lengthIndex == 4) {
                    message = "4.5 (US)";
                } else if (lengthIndex == 5) {
                    message = "5 (US)";
                } else if (lengthIndex == 6) {
                    message = "5.5 (US)";
                } else if (lengthIndex == 7) {
                    message = "6 (US)";
                } else if (lengthIndex == 8) {
                    message = "6.5 (US)";
                } else if (lengthIndex == 9) {
                    message = "6.5 (US)";
                } else if (lengthIndex == 10) {
                    message = "7 (US)";
                } else if (lengthIndex == 11) {
                    message = "7 (US)";
                } else if (lengthIndex == 12) {
                    message = "7.5 (US)";
                } else if (lengthIndex == 13) {
                    message = "8 (US)";
                } else if (lengthIndex == 14) {
                    message = "8.5 (US)";
                } else if (lengthIndex == 15) {
                    message = "9 (US)";
                } else if (lengthIndex == 16) {
                    message = "9.5 (US)";
                } else if (lengthIndex == 17) {
                    message = "10 (US)";
                } else if (lengthIndex == 18) {
                    message = "10.5 (US)";
                } else if (lengthIndex == 19) {
                    message = "10.5 (US)";
                } else if (lengthIndex == 20) {
                    message = "11 (US)";
                } else if (lengthIndex == 21) {
                    message = "11.5 (US)";
                } else if (lengthIndex == 22)
                {
                    message = "12 (US)";
                }else {
                    message = getString(R.string.result_message_overSize, "12 (US)");
                }
                MessageUtil.showMessage(getActivity(), message,checkOtherSizeCategories(lengthIndex));
            }
        });
    }

    private String checkOtherSizeCategories(int selectedIndex)
    {
        if (selectedIndex>0 && selectedIndex <= 22)
        {
            ChartDetail detail = ChartConstants.lstRing.get(selectedIndex-1);
            return String.format("%1$s (EU) - %2$s (UK) - %3$s (KR) - %4$s (JP)", detail.EU, detail.UK, detail.KR, detail.JP);
        } else
            return null;
    }

    private int getSizeIndexByLength(double length) {
        if (length < this.size_3) {
            return 0;
        }
        if (length < this.size_3_5) {
            return 1;
        }
        if (length < this.size_4) {
            return 2;
        }
        if (length < this.size_4_5) {
            return 3;
        }
        if (length < this.size_5) {
            return 4;
        }
        if (length < this.size_5_5) {
            return 5;
        }
        if (length < this.size_6) {
            return 6;
        }
        if (length < this.size_6_5) {
            return 7;
        }
        if (length < this.size_6_5_2) {
            return 8;
        }
        if (length < this.size_7) {
            return 9;
        }
        if (length < this.size_7_2) {
            return 10;
        }
        if (length < this.size_7_5) {
            return 11;
        }
        if (length < this.size_8) {
            return 12;
        }
        if (length < this.size_8_5) {
            return 13;
        }
        if (length < this.size_9) {
            return 14;
        }
        if (length < this.size_9_5) {
            return 15;
        }
        if (length < this.size_10) {
            return 16;
        }
        if (length < this.size_10_5) {
            return 17;
        }
        if (length < this.size_10_5_2) {
            return 18;
        }
        if (length < this.size_11) {
            return 19;
        }
        if (length < this.size_11_5) {
            return 20;
        }
        if (length < this.size_12) {
            return 21;
        }
        if (length < this.size_13) {
            return 22;
        }
        return 23;
    }
}
