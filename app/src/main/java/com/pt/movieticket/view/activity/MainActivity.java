package com.pt.movieticket.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.pt.movieticket.R;
import com.pt.movieticket.base.view.BaseActivity;
import com.pt.movieticket.datastore.DataStoreManager;
import com.pt.movieticket.model.User;
import com.pt.movieticket.network.BaseRequest;
import com.pt.movieticket.network.VolleyRequestManager;
import com.pt.movieticket.social.facebook.FaceBookManager;
import com.pt.movieticket.util.AppUtil;
import com.pt.movieticket.util.DialogUtil;
import com.pt.movieticket.util.ImageUtil;
import com.pt.movieticket.view.fragment.AboutFragment;
import com.pt.movieticket.view.fragment.CinemasFragment;
import com.pt.movieticket.view.fragment.HomeFragment;
import com.pt.movieticket.view.fragment.ProfileFragment;
import com.pt.movieticket.view.fragment.PromotionFragment;
import com.pt.movieticket.view.fragment.SettingFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment mCurrentFragment;
    private Menu mMenu;
    private CircleImageView ivmAvatar;
    private TextView tvName;
    private User mUser;

    /**
     * Position of fragment selected. set when item of left menu clicked
     */
    private FragmentPosition mCurFragSelected = FragmentPosition.FRAGMENT_HOME;


    /**
     * List of fragment by position
     */
    private enum FragmentPosition {
        FRAGMENT_HOME,
        FRAGMENT_CINEMAS,
        FRAGMENT_PROMOTION,
        FRAGMENT_PROFILE,
        FRAGMENT_ABOUT,
        FRAGMENT_SETTING
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FaceBookManager.initSdk(this);
    }

    @Override
    protected ToolbarType getToolbarType() {
        return ToolbarType.MENU_LEFT;
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_home;
    }

    @Override
    protected void getExtraData(Intent intent) {

    }

    @Override
    protected void initialize() {
        mUser = DataStoreManager.getUser();
        VolleyRequestManager.init(self);
        BaseRequest.setActivity(this);
    }

    @Override
    protected void initView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        View header = navigationView.getHeaderView(0);
        ivmAvatar = (CircleImageView) header.findViewById(R.id.iv_avatar);
        tvName = (TextView) header.findViewById(R.id.tv_name);
        initNavigationView();
    }

    @Override
    protected void onViewCreated() {
        setToolbarTitle(R.string.home);
        switchScreen();
        if (mUser == null) {

        } else {
            tvName.setText(mUser.getName());
            ImageUtil.setImage(ivmAvatar, mUser.getAvatar());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initNavigationView() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
//                setMenu();
                switchScreen();
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu;
        mMenu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        if (mCurFragSelected == FragmentPosition.FRAGMENT_HOME) {
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setOnQueryTextListener(this);
        }
        return true;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        showToast("starting search");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("Tit", newText);
        return true;
    }

    /**
     * Menu left onClick and switch fragment
     */
    private void switchScreen() {
        String tag = "frag_" + mCurFragSelected;
        mCurrentFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (mCurrentFragment == null) {
            if (mCurFragSelected == FragmentPosition.FRAGMENT_HOME) {
                mCurrentFragment = HomeFragment.newInstance();
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_CINEMAS) {
                mCurrentFragment = CinemasFragment.newInstance();
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_SETTING) {
                mCurrentFragment = SettingFragment.newInstance();
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_PROMOTION) {
                mCurrentFragment = PromotionFragment.newInstance();
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_PROFILE) {
                mCurrentFragment = ProfileFragment.newInstance();
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_ABOUT) {
                mCurrentFragment = AboutFragment.newInstance();
            }
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, mCurrentFragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * Set menu for screen
     */
    private void setMenu() {
        if (mMenu != null) {
            mMenu.clear();
            if (mCurFragSelected == FragmentPosition.FRAGMENT_HOME) {
                getMenuInflater().inflate(R.menu.main, mMenu);
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_CINEMAS) {
                getMenuInflater().inflate(R.menu.profile, mMenu);
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_SETTING) {
                getMenuInflater().inflate(R.menu.profile, mMenu);
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_PROMOTION) {
                getMenuInflater().inflate(R.menu.main, mMenu);
            } else if (mCurFragSelected == FragmentPosition.FRAGMENT_PROFILE) {
                getMenuInflater().inflate(R.menu.main, mMenu);
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                setToolbarTitle(R.string.home);
                mCurFragSelected = FragmentPosition.FRAGMENT_HOME;
                break;
            case R.id.nav_cinemas:
                setToolbarTitle(R.string.cinemas);
                mCurFragSelected = FragmentPosition.FRAGMENT_CINEMAS;
                break;
            case R.id.nav_promotion:
                setToolbarTitle(R.string.promotion);
                mCurFragSelected = FragmentPosition.FRAGMENT_PROMOTION;
                break;
            case R.id.nav_profile:
                setToolbarTitle(R.string.profile);
                mCurFragSelected = FragmentPosition.FRAGMENT_PROFILE;
                break;
            case R.id.nav_setting:
                setToolbarTitle(R.string.settings);
                mCurFragSelected = FragmentPosition.FRAGMENT_SETTING;
                break;
            case R.id.nav_about_us:
                setToolbarTitle(R.string.about_us);
                mCurFragSelected = FragmentPosition.FRAGMENT_ABOUT;
                break;
            case R.id.nav_logout:
                DataStoreManager.removeToken();
                DataStoreManager.removeUser();
                DataStoreManager.removePassword();
                AppUtil.startActivityLTR(this, LoginActivity.class);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            DialogUtil.showAlertDialog(this, R.string.msg_confirm_exit, new DialogUtil.IDialogConfirm() {
                @Override
                public void onClickOk() {
                    finish();
                }
            });
        }
    }
}
