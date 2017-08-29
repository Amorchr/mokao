package com.linkhand.mokao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseFragment;
import com.linkhand.mokao.ui.activity.my.PayActivity;
import com.linkhand.mokao.ui.activity.my.SettingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JCY on 2017/8/18.
 * 说明：
 */

public class MyFragment extends BaseFragment {
    @Bind(R.id.header_iv)
    ImageView mHeaderTV;
    @Bind(R.id.name)
    TextView mNameTV;
    @Bind(R.id.total_time)
    TextView mTotalTimeTV;
    @Bind(R.id.useful_time)
    TextView mUsefulTimeTV;
    @Bind(R.id.userinfo)
    TextView mUserinfoTV;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.header_image_layout, R.id.userinfo, R.id.userinfo_layout, R.id.pay_layout, R.id.error_layout, R.id.setting_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_image_layout:
                break;
            case R.id.userinfo:
                break;
            case R.id.userinfo_layout:
                break;
            case R.id.pay_layout:
                go(PayActivity.class);
                break;
            case R.id.error_layout:
                break;
            case R.id.setting_layout:
                go(SettingActivity.class);
                break;
        }
    }
}
