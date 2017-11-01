package com.phuoctran.fitme.Util;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.phuoctran.fitme.R;

public final class MessageUtil
{

    public static void showMessage(Context context, String message, String help)
    {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_message);

        ((TextView) dialog.findViewById(R.id.result_message_size)).setText(message);

        if (help != null)
            ((TextView) dialog.findViewById(R.id.result_message_help)).setText(help);
        else
        {
            ((TextView) dialog.findViewById(R.id.result_message_help)).setVisibility(View.GONE);
            ((TextView) dialog.findViewById(R.id.result_message_unit)).setVisibility(View.GONE);
        }

        ((Button) dialog.findViewById(R.id.btn_dialog_ok)).setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
    }
}
