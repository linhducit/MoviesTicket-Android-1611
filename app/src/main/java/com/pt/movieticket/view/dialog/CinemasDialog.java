package com.pt.movieticket.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pt.movieticket.AppController;
import com.pt.movieticket.R;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.model.Cinemas;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.view.activity.FilmDetailActivity;
import com.pt.movieticket.view.adapter.ArraySpinnerAdapter;
import com.pt.movieticket.widgets.textview.TextViewBold;

import java.util.ArrayList;

/**
 * Created by TitTit on 30/12/2016.
 */

public class CinemasDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spCinemas;
    private TextView tvSubmit;
    private Context mContext;
    private Film mFilm;
    private ArraySpinnerAdapter mAdapter;
    private ArrayList<Cinemas> mDatas;

    public CinemasDialog(Context context, Film mFilm) {
        super(context);
        this.mContext = context;
        this.mFilm = mFilm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_cinemas);
        initView();
        getData();
    }

    public void initView() {
        spCinemas = (Spinner) findViewById(R.id.sp_cinemas);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);

        tvSubmit.setOnClickListener(this);

    }

    public void getData() {
        mDatas = new ArrayList<>();
        mDatas.add(new Cinemas("CGV"));
        mDatas.add(new Cinemas("Quốc Gia"));
        mDatas.add(new Cinemas("Lotte"));

        mAdapter = new ArraySpinnerAdapter(mContext, mDatas);
        spCinemas.setAdapter(mAdapter);
        spCinemas.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_FILM, mFilm);
                AppUtil.startActivityLTR((Activity) mContext, FilmDetailActivity.class, bundle);
                dismiss();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Cinemas mCinemas = (Cinemas) parent.getAdapter().getItem(position);
        AppController.getInstance().setCinemas(mCinemas.getCinemas());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
