package com.pt.movieticket.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.widgets.textview.TextViewRegular;

import java.util.List;

/**
 * Created by TitTit on 09/12/2016.
 */

public class ArraySpinnerAdapter extends android.widget.ArrayAdapter<Cinemas> {
    private List<Cinemas> mDatas;
    private LayoutInflater mInflater;

    public ArraySpinnerAdapter(Context context, List<Cinemas> mDatas) {
        super(context, R.layout.spinner_dropdown_item, mDatas);
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            mInflater = LayoutInflater.from(getContext());
            view = mInflater.inflate(R.layout.simple_spinner_item, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextViewRegular) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Cinemas object = mDatas.get(position);
        holder.tvName.setText(object.getCinemas());
        return view;
    }

    @Nullable
    @Override
    public Cinemas getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            mInflater = LayoutInflater.from(getContext());
            view = mInflater.inflate(R.layout.simple_spinner_item, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Cinemas mCinemas = mDatas.get(position);
        holder.tvName.setText(mCinemas.getCinemas());
        return view;
    }

    public class ViewHolder {
        public TextView tvName;

    }
}
