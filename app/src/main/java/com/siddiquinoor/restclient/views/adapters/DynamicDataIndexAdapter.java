package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;

import java.util.ArrayList;

/**
 * Created by USER on 9/26/2016.
 */
public class DynamicDataIndexAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<DynamicDataIndexDataModel> datas = new ArrayList<DynamicDataIndexDataModel>();

    public DynamicDataIndexAdapter(Activity activity, ArrayList<DynamicDataIndexDataModel> datas) {
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public DynamicDataIndexDataModel getDynamicDataIndex(int pos) {
        return (DynamicDataIndexDataModel) getItem(pos);
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        final DynamicDataIndexDataModel data = getDynamicDataIndex(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_dynamic_data_index, null);

            holder = new ViewHolder();
            /**
             * view reference
             */

            holder.tv_dtTitle = (TextView) row.findViewById(R.id.dt_index_row_tv_dtTitle);
            holder.tv_awardName = (TextView) row.findViewById(R.id.dt_index_row_tv_AwardName);
            holder.tv_progName = (TextView) row.findViewById(R.id.dt_index_row_tv_ProgramName);
            holder.tv_ActivityName = (TextView) row.findViewById(R.id.dt_index_row_tv_ActivityTitle);
            holder.iv_Go = (ImageView) row.findViewById(R.id.dt_index_row_ibtn_go);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.tv_dtTitle.setText("DT Title \t\t\t: " + data.getDtTittle());
        holder.tv_awardName.setText("Award Name \t: " + data.getAwardName());
        holder.tv_progName.setText("Program Name : " + data.getProgramName());
        holder.tv_ActivityName.setText("Activity Title \t : " + data.getPrgActivityTitle());


        holder.iv_Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// // TODO: 9/27/2016
            }
        });

/**
 *  Change the color of background & text color Dynamically in list view
 */
        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(activity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
            changeTextColor(activity.getResources().getColor(R.color.black));
        }


        return row;
    }

    private void changeTextColor(int color) {
        holder.tv_dtTitle.setTextColor(color);
        holder.tv_awardName.setTextColor(color);
        holder.tv_progName.setTextColor(color);
        holder.tv_ActivityName.setTextColor(color);
    }

    private static class ViewHolder {
        TextView tv_dtTitle;
        TextView tv_awardName;
        TextView tv_progName;
        TextView tv_ActivityName;
        ImageView iv_Go;
    }
}
