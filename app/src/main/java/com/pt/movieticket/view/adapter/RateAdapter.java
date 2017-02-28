package com.pt.movieticket.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.model.Rate;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.widgets.textview.TextViewBold;
import com.pt.movieticket.widgets.textview.TextViewLight;
import com.pt.movieticket.widgets.textview.TextViewOpenSansLight;
import com.pt.movieticket.widgets.textview.TextViewOpenSansSemibold;
import com.pt.movieticket.widgets.textview.TextViewRegular;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TitTit on 14/12/2016.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {
    private Context mContext;
    private List<Rate> arrRate;

    public RateAdapter(Context context, List<?> arrRate) {
        this.mContext = context;
        this.arrRate = (List<Rate>) arrRate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_rate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rate rate = arrRate.get(position);
        holder.tvName.setText(rate.getName());
        holder.tvContent.setText(rate.getContent());
        holder.ratingBar.setRating(rate.getRating());
        ImageUtil.setImage(holder.ivmAvatar, rate.getImage());
        if (position % 2 != 0) {
            holder.llRate.setBackgroundColor(Color.parseColor("#f6f6f6"));
        }
    }

    @Override
    public int getItemCount() {
        return arrRate.size();
    }

    public void addRate(int position, Rate rate) {
        arrRate.add(position, rate);
        notifyItemInserted(position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ivmAvatar;
        TextView tvContent,tvName;
        RatingBar ratingBar;
        LinearLayout llRate;

        public ViewHolder(View itemView) {
            super(itemView);
            ivmAvatar = (CircleImageView) itemView.findViewById(R.id.ivm_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingbar);
            llRate = (LinearLayout) itemView.findViewById(R.id.ll_rate);
        }
    }
}
