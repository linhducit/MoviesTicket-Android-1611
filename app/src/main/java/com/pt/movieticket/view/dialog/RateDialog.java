package com.pt.movieticket.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.view.activity.FilmDetailActivity;
import com.pt.movieticket.widgets.textview.TextViewBold;
import com.pt.movieticket.widgets.textview.TextViewOpenSansBold;

/**
 * Created by TitTit on 30/12/2016.
 */

public class RateDialog extends Dialog {
    private RatingBar ratingBar;
    private EditText edtContent;
    private TextView tvSubmit;
    private Context mContext;
    private IData sendM;

    public interface IData {
        void sendMessage(String content, float rate);
    }

    public RateDialog(Context context, IData sendM) {
        super(context);
        this.mContext = context;
        this.sendM = sendM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_rate);
        initView();
    }

    public void initView() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        edtContent = (EditText) findViewById(R.id.edt_content);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);


        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating() == 0 || edtContent.getText().toString().equals("")) {
                    ((FilmDetailActivity) mContext).showSnackBar("Bạn chưa đánh giá");
                } else {
                    sendM.sendMessage(edtContent.getText().toString(), ratingBar.getRating());
                    edtContent.setText("");
                    ratingBar.setRating(0);
                    dismiss();
                }
                edtContent.requestFocus();
            }
        });
    }
}
