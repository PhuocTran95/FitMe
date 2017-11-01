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

public class ShoesChartForMale extends Fragment {
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
            convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_chart_item_shoes, null);
            TextView col2 = (TextView) convertView.findViewById(R.id.txtCol2);
            TextView col3 = (TextView) convertView.findViewById(R.id.txtCol3);
            TextView col4 = (TextView) convertView.findViewById(R.id.txtCol4);
            TextView col5 = (TextView) convertView.findViewById(R.id.txtCol5);
            ((TextView) convertView.findViewById(R.id.txtCol1)).setText(((ChartDetail) this.lst.get(position)).EU);
            col2.setText(((ChartDetail) this.lst.get(position)).US);
            col3.setText(((ChartDetail) this.lst.get(position)).UK);
            col4.setText(((ChartDetail) this.lst.get(position)).KR);
            col5.setText(((ChartDetail) this.lst.get(position)).JP);
            return convertView;
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart_list_shoes_male, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.lstView = (ListView) getActivity().findViewById(R.id.listView_chart_shoes_male);
        this.lstView.setAdapter(new ChartAdapter(getActivity(), ChartConstants.lstShoesMale));
    }
}
