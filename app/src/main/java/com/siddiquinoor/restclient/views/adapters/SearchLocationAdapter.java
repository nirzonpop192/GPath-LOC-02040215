package com.siddiquinoor.restclient.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.MapActivity;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Faisal on 7/24/2016.
 */
public class SearchLocationAdapter extends BaseAdapter {

    private Activity activity;

    private LayoutInflater inflater;
    private String countryCode;


    List<SpinnerHelper> list = new ArrayList<SpinnerHelper>();

    public SearchLocationAdapter(Activity activity, List<SpinnerHelper> list,String countryCode) {
        this.activity = activity;
        this.list = list;
        this.countryCode = countryCode;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     *
     * @param position index
     * @return position of the selected item
     */

    @Override
    public long getItemId(int position) {
         return position;
    }

    ViewHolder holder;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            row = inflater.inflate(R.layout.list_row_search_location, null);
            holder = new ViewHolder();
            holder.tv_locationName = (TextView) row.findViewById(R.id.tv_location);
            holder.im_btnGoToMap = (ImageButton) row.findViewById(R.id.imgbtnGoMap);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        final SpinnerHelper data = list.get(position);


        holder.tv_locationName.setText(data.getValue());

        if (position % 2 == 0) {
            row.setBackgroundColor(Color.WHITE);
            changeTextColor(activity.getResources().getColor(R.color.blue));
        } else {
            row.setBackgroundColor(activity.getResources().getColor(R.color.list_divider));
            changeTextColor(activity.getResources().getColor(R.color.black));
        }


        holder.im_btnGoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imap = new Intent(activity, MapActivity.class);
                imap.putExtra(KEY.COUNTRY_ID, countryCode);
                imap.putExtra(KEY.DIR_CLASS_NAME_KEY, "SearchSubGroup");
                imap.putExtra(KEY.LOCATION_CODE, data.getId());
                imap.putExtra(KEY.LOCATION_NAME, data.getValue());

                activity.finish();
                activity.startActivity(imap);
            }
        });


        return row;

    }

    private void changeTextColor(int color) {
        holder.tv_locationName.setTextColor(color);
    }

    /**
     *
     */

    class ViewHolder {
        TextView tv_locationName;
        ImageButton im_btnGoToMap;

    }
}
