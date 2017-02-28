package com.pt.movieticket.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pt.movieticket.AppController;
import com.pt.movieticket.R;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.view.activity.CinemasDetailActivity;

import java.util.List;

/**
 * Created by TitTit on 28/11/2016.
 */

public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.ViewHolder> {

    private List<Cinemas> arrCinemas;
    private Context mContext;

    public CinemasAdapter(Context context, List<?> arrCinemas) {
        this.mContext = context;
        this.arrCinemas = (List<Cinemas>) arrCinemas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_cinemas, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Cinemas cinemas = arrCinemas.get(position);
        holder.tvCinemas.setText(cinemas.getCinemas());
        ImageUtil.setImage(holder.ivmCinemas, cinemas.getImage());
        holder.ivmCinemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppController myApp = AppController.getInstance();
                myApp.setCinemas(arrCinemas.get(holder.getAdapterPosition()).getCinemas());
                AppUtil.startActivityLTR((Activity) mContext, CinemasDetailActivity.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrCinemas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivmCinemas;
        public TextView tvCinemas;

        public ViewHolder(View itemView) {
            super(itemView);
            ivmCinemas = (ImageView) itemView.findViewById(R.id.ivm_cinemas);
            tvCinemas = (TextView) itemView.findViewById(R.id.tv_cinemas);

        }
    }
}
