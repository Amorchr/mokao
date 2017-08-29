package com.linkhand.mokao.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.ui.fragment.HomeFragment;
import com.linkhand.mokao.ui.fragment.MyFragment;
import com.linkhand.mokao.ui.fragment.StoreFragment;
import com.shcyd.lib.base.BaseAppManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final int HOME = 1;
    private static final int STORE = 2;
    private static final int USER = 3;
    @Bind(R.id.frameLayout)
    FrameLayout mFrameLayout;
    private FragmentManager fragmentManager;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private String[] name = {"首页", "商城", "我的"};
    private int[] image = {
            R.drawable.zhuye,
            R.drawable.zhuye1,
            R.drawable.shangcheng,
            R.drawable.shangcheng1,
            R.drawable.my_yes,
            R.drawable.wode1,
    };

    private HomeFragment homeFragment;
    private StoreFragment storeFragment;
    private MyFragment myFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseAppManager.getInstance().clearBackActivities();
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        linearLayout1 = (LinearLayout) findViewById(R.id.linearlayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearlayout3);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        showFragment(HOME);
    }

    @OnClick({R.id.linearlayout1, R.id.linearlayout2, R.id.linearlayout3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearlayout1:
                showFragment(HOME);
                break;
            case R.id.linearlayout2:
                showFragment(STORE);
                break;
            case R.id.linearlayout3:
                showFragment(USER);
                break;
        }
    }

    private void showFragment(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏所有的Fragment
        hideFragment(fragmentTransaction);
        setDefaultBottom();
        setName(index);
        //显示指定的Fragment

        switch (index) {
            //首页
            case HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.frameLayout, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            //预告
            case STORE:
                if (storeFragment == null) {
                    storeFragment = new StoreFragment();
                    fragmentTransaction.add(R.id.frameLayout, storeFragment);
                } else {
                    fragmentTransaction.show(storeFragment);
                }
                break;
            //发布
            case USER:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    fragmentTransaction.add(R.id.frameLayout, myFragment);
                } else {
                    fragmentTransaction.show(myFragment);
                }
                break;

        }

        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (storeFragment != null) {
            fragmentTransaction.hide(storeFragment);
        }
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }
    }

    private void setName(int index) {
        TextView textView;
        ImageView imageView;
        switch (index) {
            case HOME:
                textView = (TextView) linearLayout1.findViewById(R.id.textview_icon);
                imageView = (ImageView) linearLayout1.findViewById(R.id.imageview);
                textView.setText(name[0]);
                textView.setTextColor(getResources().getColor(R.color.colorTopic));
                imageView.setImageDrawable(getResources().getDrawable(image[1]));
                break;
            case STORE:
                textView = (TextView) linearLayout2.findViewById(R.id.textview_icon);
                imageView = (ImageView) linearLayout2.findViewById(R.id.imageview);
                textView.setTextColor(getResources().getColor(R.color.colorTopic));
                textView.setText(name[1]);
                imageView.setImageDrawable(getResources().getDrawable(image[3]));
                break;
            case USER:
                textView = (TextView) linearLayout3.findViewById(R.id.textview_icon);
                imageView = (ImageView) linearLayout3.findViewById(R.id.imageview);
                textView.setText(name[2]);
                textView.setTextColor(getResources().getColor(R.color.colorTopic));
                imageView.setImageDrawable(getResources().getDrawable(image[5]));
                break;
        }
    }


    private void setDefaultBottom() {
        TextView textView = (TextView) linearLayout1.findViewById(R.id.textview_icon);
        ImageView imageView = (ImageView) linearLayout1.findViewById(R.id.imageview);
        textView.setTextColor(getResources().getColor(R.color.grayText));
        textView.setText(name[0]);
        imageView.setImageDrawable(getResources().getDrawable(image[0]));

        textView = (TextView) linearLayout2.findViewById(R.id.textview_icon);
        imageView = (ImageView) linearLayout2.findViewById(R.id.imageview);
        textView.setTextColor(getResources().getColor(R.color.grayText));
        textView.setText(name[1]);
        imageView.setImageDrawable(getResources().getDrawable(image[2]));

        textView = (TextView) linearLayout3.findViewById(R.id.textview_icon);
        imageView = (ImageView) linearLayout3.findViewById(R.id.imageview);
        textView.setText(name[2]);
        textView.setTextColor(getResources().getColor(R.color.grayText));
        imageView.setImageDrawable(getResources().getDrawable(image[4]));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
