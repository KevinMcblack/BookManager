package com.example.verge.bookmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class GridViewBookShelfAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;

    public GridViewBookShelfAdapter(Context context,List<String> data) {
        super();
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_bookshelf, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView
                    .findViewById(R.id.bookImage);
            convertView.setTag(holder);// 如果convertView为空就 把holder赋值进去
        } else {
            holder = (ViewHolder) convertView.getTag();// 如果convertView不为空，那么就在convertView中getTag()拿出来
        }
        String current = data.get(position);
//        holder.iv.setImageResource(current);
        return convertView;
    }

    static class ViewHolder {
        ImageView iv;
    }
}
