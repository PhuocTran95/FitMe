package com.phuoctran.fitme.Fragments.SizeChartFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.phuoctran.fitme.Models.ChartDetail;
import com.phuoctran.fitme.Models.ChartConstants;
import com.phuoctran.fitme.R;

import java.util.ArrayList;

public class HatChart extends Fragment {
    ListView lstView;

    class ChartAdapter extends BaseAdapter {
        Context context;
        ArrayList<ChartDetail> lst;

        public ChartAdapter(Context context, ArrayList<ChartDetail> lst) {
            this.lst = lst;
            this.context = context;
        }

        public int getCount() {
            return this.lst.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_chart_item_hat, null);
            TextView col2 = (TextView) convertView.findViewById(R.id.txtCol2);
            TextView col3 = (TextView) convertView.findViewById(R.id.txtCol3);
            ((TextView) convertView.findViewById(R.id.txtCol1)).setText(((ChartDetail) this.lst.get(position)).INT);
            col2.setText(((ChartDetail) this.lst.get(position)).US);
            col3.setText(((ChartDetail) this.lst.get(position)).UK);
            return convertView;
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_chart_list_hat, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lstView = (ListView) getActivity().findViewById(R.id.listView_chart_hat);
        this.lstView.setAdapter(new ChartAdapter(getActivity(), ChartConstants.lstHat));
    }
}
