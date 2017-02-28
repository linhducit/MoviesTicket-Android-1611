package com.pt.movieticket.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.model.Order;
import com.pt.movieticket.widgets.textview.TextViewOpenSansLight;
import com.pt.movieticket.widgets.textview.TextViewOpenSansRegular;
import com.pt.movieticket.widgets.textview.TextViewRegular;

import java.util.List;

/**
 * Created by TitTit on 27/11/2016.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context mContext;
    private List<Order> arrOrder;

    public OrderAdapter(Context context, List<?> arrOrder) {
        this.mContext = context;
        this.arrOrder = (List<Order>) arrOrder;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position % 2 != 0) {
            holder.llOrder.setBackgroundColor(Color.parseColor("#f6f6f6"));
        }
        Order order = arrOrder.get(position);
        holder.tvTheater.setText(order.getTheater());
        holder.tvMovie.setText(order.getMovie());
        holder.tvTicket.setText(order.getTicket());
        holder.tvTotal.setText(order.getTotal());
        holder.tvRoom.setText(order.getRoom());
        holder.tvDataTime.setText(order.getDateTime());

    }

    @Override
    public int getItemCount() {
        return arrOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRoom, tvDataTime,tvTheater, tvMovie, tvTicket, tvTotal;
        LinearLayout llOrder;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTheater = (TextView) itemView.findViewById(R.id.tv_theater);
            tvMovie = (TextView) itemView.findViewById(R.id.tv_movie);
            tvTicket = (TextView) itemView.findViewById(R.id.tv_ticket);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_total);
            tvRoom = (TextView) itemView.findViewById(R.id.tv_room);
            tvDataTime = (TextView) itemView.findViewById(R.id.tv_datetime);
            llOrder = (LinearLayout) itemView.findViewById(R.id.ll_order);

        }
    }
}
