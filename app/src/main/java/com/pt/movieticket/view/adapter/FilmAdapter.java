package com.pt.movieticket.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.view.activity.FilmDetailActivity;
import com.pt.movieticket.view.dialog.CinemasDialog;

import java.util.List;

/**
 * Created by TitTit on 23/11/2016.
 */

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    private List<Film> arrFilm;
    private Context mContext;
    private String cinemas;
    private CinemasDialog mDialog;

    public FilmAdapter(Context context, List<?> data, String cinemas) {
        this.mContext = context;
        this.arrFilm = (List<Film>) data;
        this.cinemas = cinemas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Film film = arrFilm.get(position);
        holder.tvFilm.setText(film.getFilm());
        holder.tvDate.setText(film.getDate());
        ImageUtil.setImage(holder.ivmProduct, film.getImage());

        holder.ivmProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cinemas.equals("")) {
                    mDialog = new CinemasDialog(mContext, film);
                    mDialog.show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constant.KEY_FILM, film);
                    bundle.putString(Constant.KEY_CINEMAS, cinemas);
                    AppUtil.startActivityLTR((Activity) mContext, FilmDetailActivity.class, bundle);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrFilm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivmProduct;
        public TextView tvDate,tvFilm;

        public ViewHolder(View itemView) {
            super(itemView);
            ivmProduct = (ImageView) itemView.findViewById(R.id.ivm_product);
            tvFilm = (TextView) itemView.findViewById(R.id.tv_film);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);

        }
    }
}
