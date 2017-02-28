package com.pt.movieticket.view.adapter;

import android.content.Context;
import android.graphics.drawable.LevelListDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.model.Booking;
import com.pt.movieticket.widgets.textview.TextViewOpenSansLight;
import com.pt.movieticket.widgets.textview.TextViewRegular;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TitTit on 06/12/2016.
 */

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private Context mContext;
    private List<Booking> arrBooking = new ArrayList<>();
    private LevelListDrawable levelListDrawable;
    private IData mIData;

    public interface IData {
        void getChoosing(boolean isCheck);
    }

    public BookingAdapter(Context context, IData mIData) {
        this.mContext = context;
        this.mIData = mIData;
        getData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Booking booking = arrBooking.get(position);
        holder.tvName.setText(booking.getRow());
        if (position == 0) {
            holder.ivmRow1.setVisibility(View.INVISIBLE);
            holder.ivmRow2.setVisibility(View.INVISIBLE);
            holder.ivmRow6.setVisibility(View.INVISIBLE);
            holder.ivmRow7.setVisibility(View.INVISIBLE);

        }
        getState(holder.ivmRow1, booking.getSeats1());
        getState(holder.ivmRow2, booking.getSeats2());
        getState(holder.ivmRow3, booking.getSeats3());
        getState(holder.ivmRow4, booking.getSeats4());
        getState(holder.ivmRow5, booking.getSeats5());
        getState(holder.ivmRow6, booking.getSeats6());
        getState(holder.ivmRow7, booking.getSeats7());

        holder.ivmRow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow1);
            }
        });
        holder.ivmRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow2);
            }
        });
        holder.ivmRow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow3);
            }
        });
        holder.ivmRow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow4);
            }
        });
        holder.ivmRow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow5);
            }
        });
        holder.ivmRow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow6);
            }
        });
        holder.ivmRow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkState(holder.ivmRow7);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrBooking.size();
    }

    public void getState(ImageView ivm, int choseState) {
        levelListDrawable = (LevelListDrawable) ivm.getDrawable();
        levelListDrawable.setLevel(choseState);
    }

    public void checkState(ImageView ivm) {
        levelListDrawable = (LevelListDrawable) ivm.getDrawable();
        if (levelListDrawable.getLevel() == Constant.STATE_AVAILABLE) {
            levelListDrawable.setLevel(Constant.STATE_CHOOSING);
            mIData.getChoosing(true);
        } else if (levelListDrawable.getLevel() == Constant.STATE_CHOOSING) {
            levelListDrawable.setLevel(Constant.STATE_AVAILABLE);
            mIData.getChoosing(false);
        }
    }

    public void getData() {
        arrBooking.add(new Booking("A", 1, 1, 2, 2, 2, 1, 1));
        arrBooking.add(new Booking("B", 1, 1, 2, 2, 2, 1, 1));
        arrBooking.add(new Booking("C", 1, 1, 2, 2, 2, 1, 1));
        arrBooking.add(new Booking("D", 1, 1, 2, 2, 2, 1, 1));
        arrBooking.add(new Booking("E", 1, 1, 2, 2, 2, 1, 1));
        arrBooking.add(new Booking("F", 1, 1, 2, 2, 2, 1, 1));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivmRow1, ivmRow2, ivmRow3, ivmRow4, ivmRow5, ivmRow6, ivmRow7;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_row_name);
            ivmRow1 = (ImageView) itemView.findViewById(R.id.ivm_row_1);
            ivmRow2 = (ImageView) itemView.findViewById(R.id.ivm_row_2);
            ivmRow3 = (ImageView) itemView.findViewById(R.id.ivm_row_3);
            ivmRow4 = (ImageView) itemView.findViewById(R.id.ivm_row_4);
            ivmRow5 = (ImageView) itemView.findViewById(R.id.ivm_row_5);
            ivmRow6 = (ImageView) itemView.findViewById(R.id.ivm_row_6);
            ivmRow7 = (ImageView) itemView.findViewById(R.id.ivm_row_7);
        }
    }
}
