package com.il.tech.hqtask;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class ListAdapter extends BaseAdapter {
    private String[] m_DataArray;
    private Context m_Context;

    public ListAdapter(Context context, String[] dataArray) {
        this.m_Context = context;
        this.m_DataArray = dataArray;
    }

    @Override
    public int getCount() {
        return m_DataArray.length;
    }

    @Override
    public String getItem(int position) {
        return m_DataArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) m_Context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textView_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtTitle.setText(m_DataArray[position]);

        return convertView;
    }

    public class ViewHolder {
        public TextView txtTitle;
    }
}
