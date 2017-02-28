package com.pt.movieticket.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.model.Content;
import com.pt.movieticket.util.ImageUtil;

/**
 * Created by TitTit on 29/11/2016.
 */

public class PromotionDetailActivity extends BaseActivity implements View.OnClickListener {

    private Content mContent;
    private ImageView ivmPromotion, ivmShare;
    private TextView tvContent;

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.NAVI;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_promotion_detail;
    }

    @Override
    protected void getExtraData(Intent intent) {
        Bundle bundle = intent.getExtras();
        mContent = bundle.getParcelable(Constant.KEY_PROMOTION);
    }

    @Override
    protected void initialize() {
        setToolbarTitle("News Details");
    }

    @Override
    protected void initView() {
        ivmShare = (ImageView) findViewById(R.id.ivm_share);
        ivmPromotion = (ImageView) findViewById(R.id.ivm_promotion);
        tvContent = (TextView) findViewById(R.id.tv_content);

        ivmShare.setOnClickListener(this);
    }

    @Override
    protected void onViewCreated() {
        ImageUtil.setImage(ivmPromotion, mContent.getImage());
        tvContent.setText(mContent.getContent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void shareData() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Content");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivm_share:
                shareData();
                break;
        }
    }
}
