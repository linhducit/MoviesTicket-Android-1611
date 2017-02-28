package com.pt.movieticket.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.configs.Constant;
import com.pt.movieticket.listener.ICallbackBooking;
import com.pt.movieticket.model.Film;
import com.pt.movieticket.view.fragment.OneFragment;
import com.pt.movieticket.view.fragment.ThreeFragment;
import com.pt.movieticket.view.fragment.TwoFragment;


/**
 * Created by TitTit on 25/11/2016.
 */

public class BookingActivity extends BaseActivity implements ICallbackBooking {

    private FragmentPosition mCurrentSelected = FragmentPosition.FRAGMENT_ONE;
    private Fragment mCurrentFragment;
    private TextView tvOne, tvTwo, tvThree;
    private Film mFilm;

    public enum FragmentPosition {
        FRAGMENT_ONE,
        FRAGMENT_TWO,
        FRAGMENT_THREE
    }

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.NONE;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_booking;
    }

    @Override
    protected void getExtraData(Intent intent) {
        Bundle bundle = intent.getExtras();
        mFilm = bundle.getParcelable(Constant.KEY_FILM);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void initView() {
        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);
        tvThree = (TextView) findViewById(R.id.tv_three);

    }

    @Override
    protected void onViewCreated() {
        switchScreen();
    }

    public void switchScreen() {
        String tag = "tag" + mCurrentSelected;
        mCurrentFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (mCurrentFragment == null) {
            if (mCurrentSelected == FragmentPosition.FRAGMENT_ONE) {
                mCurrentFragment = OneFragment.newInstance(mFilm);
                tvOne.setBackgroundResource(R.drawable.custom_circle2);
                tvTwo.setBackgroundResource(R.drawable.custom_circle1);
                tvThree.setBackgroundResource(R.drawable.custom_circle1);
            } else if (mCurrentSelected == FragmentPosition.FRAGMENT_TWO) {
                mCurrentFragment = TwoFragment.newInstance();
                tvOne.setBackgroundResource(R.drawable.custom_circle1);
                tvTwo.setBackgroundResource(R.drawable.custom_circle2);
                tvThree.setBackgroundResource(R.drawable.custom_circle1);
            } else if (mCurrentSelected == FragmentPosition.FRAGMENT_THREE) {
                mCurrentFragment = ThreeFragment.newInstance();
                tvOne.setBackgroundResource(R.drawable.custom_circle1);
                tvTwo.setBackgroundResource(R.drawable.custom_circle1);
                tvThree.setBackgroundResource(R.drawable.custom_circle2);

            }
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frl_booking, mCurrentFragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void next(int key) {
        switch (key) {
            case Constant.FRAGMENT_TWO:
                mCurrentSelected = FragmentPosition.FRAGMENT_TWO;
                break;
            case Constant.FRAGMENT_THREE:
                mCurrentSelected = FragmentPosition.FRAGMENT_THREE;
                break;
            default:
                break;
        }
        switchScreen();
    }

    @Override
    public void back(int key) {
        switch (key) {
            case Constant.FRAGMENT_TWO:
                mCurrentSelected = FragmentPosition.FRAGMENT_TWO;
                break;
            case Constant.FRAGMENT_ONE:
                mCurrentSelected = FragmentPosition.FRAGMENT_ONE;
                break;
            case Constant.FRAGMENT_NULL:
                finish();
            default:
                break;
        }
        switchScreen();
    }

    @Override
    public void finishActivity() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (mCurrentSelected == FragmentPosition.FRAGMENT_ONE) {
            finish();
        } else if (mCurrentSelected == FragmentPosition.FRAGMENT_TWO) {
            getSupportFragmentManager().popBackStack();
            mCurrentSelected = FragmentPosition.FRAGMENT_ONE;
        } else if (mCurrentSelected == FragmentPosition.FRAGMENT_THREE) {
            getSupportFragmentManager().popBackStack();
            mCurrentSelected = FragmentPosition.FRAGMENT_TWO;

        }
    }
}
