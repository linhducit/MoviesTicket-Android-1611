package com.pt.movieticket.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.model.Rate;
import com.pt.movieticket.model.User;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.view.adapter.ActorAdapter;
import com.pt.movieticket.view.adapter.RateAdapter;
import com.pt.movieticket.view.dialog.RateDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TitTit on 23/11/2016.
 */

public class FilmDetailActivity extends BaseActivity implements View.OnClickListener, RateDialog.IData, MediaPlayer.OnPreparedListener {
    private VideoView videoView;
    private TextView tvTicket, tvStar, tvFilm, tvContent;
    private ImageView ivmPlay;
    private RatingBar ratingBar;
    private MediaController mediaController;
    private RecyclerView rcvActor, rcvRate;
    private FloatingActionButton btRate;
    private ActorAdapter mActorAdapter;
    private RateAdapter mRateAdapter;
    private List<Rate> arrRate = new ArrayList<>();
    private int position = 0;
    private Film mFilm;

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.NAVI;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_film_detail;
    }

    @Override
    protected void getExtraData(Intent intent) {
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        mFilm = bundle.getParcelable(Constant.KEY_FILM);
    }

    @Override
    protected void initialize() {
        setToolbarTitle("Film Detail");
        getData();
        mActorAdapter = new ActorAdapter(this, mFilm.getArrActor());
        mRateAdapter = new RateAdapter(this, mFilm.getArrRate());
    }

    @Override
    protected void initView() {
        ivmPlay = (ImageView) findViewById(R.id.ivm_play);
        tvTicket = (TextView) findViewById(R.id.tv_ticket);
        tvStar = (TextView) findViewById(R.id.tv_rate);
        tvContent = (TextView) findViewById(R.id.tv_film_content);
        tvFilm = (TextView) findViewById(R.id.tv_film);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btRate = (FloatingActionButton) findViewById(R.id.bt_rate);
        videoView = (VideoView) findViewById(R.id.video_trailer);
        rcvActor = (RecyclerView) findViewById(R.id.rcv_actor);
        rcvRate = (RecyclerView) findViewById(R.id.rcv_rate);

        rcvActor.setHasFixedSize(true);
        rcvRate.setHasFixedSize(true);

        rcvActor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvRate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ivmPlay.setOnClickListener(this);
        tvTicket.setOnClickListener(this);
        btRate.setOnClickListener(this);
    }

    @Override
    protected void onViewCreated() {
        tvFilm.setText(mFilm.getFilm());
        tvStar.setText(String.valueOf(mFilm.getRate()));
        tvContent.setText(mFilm.getContent());
        setAdapter();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        videoView.seekTo(position);
        if (position == 0) {
            videoView.start();
        }
        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                mediaController.setAnchorView(videoView);
            }
        });
    }

    public void playVideo() {
        if (mediaController == null) {
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

        }
        videoView.setVideoURI(Uri.parse(mFilm.getLink()));
        videoView.requestFocus();
        videoView.setOnPreparedListener(this);
    }
    public void stopVideo(){
        videoView.stopPlayback();
    }


    public void getData() {
        mFilm.setArrRate(arrRate);
    }

    public void setAdapter() {
        rcvActor.setAdapter(mActorAdapter);
        rcvRate.setAdapter(mRateAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ticket:
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_FILM, mFilm);
                AppUtil.startActivityForResultLTR(this, BookingActivity.class, bundle, Constant.REQUEST_CODE);
                ivmPlay.setVisibility(View.VISIBLE);
                stopVideo();
                break;
            case R.id.ivm_play:
                ivmPlay.setVisibility(View.GONE);
                playVideo();
                break;
            case R.id.bt_rate:
                RateDialog dialog = new RateDialog(this, this);
                dialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void sendMessage(String content, float rate) {
        User mUser = DataStoreManager.getUser();
        Rate mRate;
        if (mUser == null) {
            mRate = new Rate("", "Admin", content, rate);
        } else {
            mRate = new Rate(mUser.getAvatar(), mUser.getName(), content, rate);
        }
        mRateAdapter.addRate(mFilm.getArrRate().size(), mRate);
        rcvRate.setAdapter(mRateAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }

    }
}
