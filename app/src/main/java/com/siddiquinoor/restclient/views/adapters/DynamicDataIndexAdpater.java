package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
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
public class DynamicDataIndexAdpater extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<DynamicDataIndexDataModel> datas = new ArrayList<DynamicDataIndexDataModel>();

    public DynamicDataIndexAdpater(Activity activity, ArrayList<DynamicDataIndexDataModel> datas) {
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

            holder.tv_dtTitle = (TextView) row.findViewById(R.id.dt_index_row_tv_dtTitle);

            holder.tv_awardName = (TextView) row.findViewById(R.id.dt_index_row_tv_AwardName);

            holder.tv_progName = (TextView) row.findViewById(R.id.dt_index_row_tv_ProgramName);

            holder.tv_ActivityName = (TextView) row.findViewById(R.id.dt_index_row_tv_ActivityTitle);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.tv_dtTitle.setText(data.getDtTittle());
        holder.tv_awardName.setText(data.getAwardName());
        holder.tv_progName.setText(data.getProgramName());
        holder.tv_ActivityName.setText(data.getPrgActivityTitle());

/*
        holder.tv_GroupName.setText(data.getCommunityGroupName());  // 15 digit

        holder.tv_CatShortName.setText(data.getCommuCategoriesShortName());

        holder.tv_ProgShortName.setText(data.getProgramShortName());*/


        return row;
    }

    private static class ViewHolder {
        TextView tv_dtTitle;
        TextView tv_awardName;
        TextView tv_progName;
        TextView tv_ActivityName;
    }
}
