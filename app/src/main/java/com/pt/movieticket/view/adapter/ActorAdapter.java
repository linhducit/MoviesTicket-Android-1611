package com.pt.movieticket.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.model.Actor;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.widgets.textview.TextViewOpenSansSemibold;
import com.pt.movieticket.widgets.textview.TextViewRegular;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TitTit on 24/11/2016.
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {
    private Context mContext;
    private List<Actor> arrActor;

    public ActorAdapter(Context context, List<?> arrActor) {
        this.mContext = context;
        this.arrActor = (List<Actor>) arrActor;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_actor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actor actor = arrActor.get(position);
        holder.tvName.setText(actor.getName());
        ImageUtil.setImage(holder.ivmActor, actor.getImage());
    }

    @Override
    public int getItemCount() {
        return arrActor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivmActor;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivmActor = (ImageView) itemView.findViewById(R.id.ivm_actor);
            tvName = (TextView) itemView.findViewById(R.id.tv_actor_name);
        }
    }
}
