package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.utils.KEY;

import java.util.ArrayList;

/**
 * Created by Faisal
 * @since  8/4/2016.
 */
public class MemberSearchAdapter extends BaseAdapter {

    private ViewHolder holder;
    private Activity activity;

    private LayoutInflater inflater;
    ArrayList<AssignDataModel> assignData = new ArrayList<AssignDataModel>();

    public MemberSearchAdapter(Activity activity, ArrayList<AssignDataModel> assignData) {
        this.activity = activity;
        this.assignData = assignData;
    }

    public MemberSearchAdapter() {

    }

    @Override
    public int getCount() {
         return assignData.size();
    }

    @Override
    public Object getItem(int position) {
        return assignData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final AssignDataModel memData = assignData.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_member_search, null);

            holder = new ViewHolder();

            holder.memberId = (TextView) row.findViewById(R.id.memS_row_memId);

            holder.tv_mmName = (TextView) row.findViewById(R.id.memS_row_mem_name);
          // holder.tv_age = (TextView) row.findViewById(R.id.mem_search_row_tv_assignView);
            holder.tv_LayR4Name = (TextView) row.findViewById(R.id.tv_LayR4Name);
            holder.tv_AddressName = (TextView) row.findViewById(R.id.memSear_tv_AddressName);
            holder.imgEdit = (ImageView) row.findViewById(R.id.ibtn_group_sear_edit);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }




        holder.memberId.setText(memData.getNewId());  // 15 digit

        holder.tv_mmName.setText(memData.getHh_mm_name());
       // holder.tv_age.setText(memData.getMember_age());
        holder.tv_LayR4Name.setText(memData.getVillageName());
        holder.tv_AddressName.setText(memData.getAddressName());



        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.finish();

                Intent intent= new Intent(activity, AssignActivity.class);

                intent.putExtra(KEY.COUNTRY_ID,memData.getCountryCode());
                intent.putExtra(KEY.MEMBER_ID,memData.getNewId());
                activity.startActivity(intent);



            }
        });
        // Change the color of background & text color Dynamically in list view
        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(activity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
            changeTextColor(activity.getResources().getColor(R.color.black));
        }
        return row;
    }

    class ViewHolder {

        TextView memberId;
        TextView tv_mmName;
       // TextView tv_age;
        TextView tv_LayR4Name;
        TextView tv_AddressName;
        ImageView imgEdit;


    }

    /**
     * The method change the color of the textView
     *
     * @param color Color of text View
     */
    private void changeTextColor(int color) {
        holder.memberId.setTextColor(color);
        holder.tv_mmName.setTextColor(color);
        holder.tv_LayR4Name.setTextColor(color);
        holder.tv_AddressName.setTextColor(color);
       // holder.tv_age.setTextColor(color);
    }
}
