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
import com.pt.movieticket.model.Content;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.view.activity.PromotionDetailActivity;

import java.util.List;

/**
 * Created by TitTit on 28/11/2016.
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {

    private List<Content> arrPromotion;
    private Context mContext;

    public PromotionAdapter(Context context, List<?> arrPromotion) {
        this.mContext = context;
        this.arrPromotion = (List<Content>) arrPromotion;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_promotion, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Content content = arrPromotion.get(position);
        ImageUtil.setImage(holder.ivmPromotion, content.getImage());
        holder.tvContent.setText(content.getContent());
        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_PROMOTION, arrPromotion.get(holder.getAdapterPosition()));
                AppUtil.startActivityLTR((Activity) mContext, PromotionDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPromotion.size();
    }

    public void updateAdapter(List<Content> listContent) {
        arrPromotion.clear();
        arrPromotion.addAll(listContent);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivmPromotion, ivmShare, ivmSetting;
        public TextView tvReadMore, tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ivmPromotion = (ImageView) itemView.findViewById(R.id.ivm_promotion);
            ivmShare = (ImageView) itemView.findViewById(R.id.ivm_share);
            ivmSetting = (ImageView) itemView.findViewById(R.id.ivm_settings);
            tvReadMore = (TextView) itemView.findViewById(R.id.tv_read_more);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
