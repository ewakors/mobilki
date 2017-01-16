package com.example.us.imagedb;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    public List<Product> mProductList;
    public LayoutInflater mInflater;
    public boolean mShowQuantity;


    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return convertView;
    }

    public class ViewItem {
        ImageView productImageView;
        TextView productTitle;
        TextView productQuantity;
    }

}
