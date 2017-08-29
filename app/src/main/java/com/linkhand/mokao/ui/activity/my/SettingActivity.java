package com.linkhand.mokao.ui.activity.my;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.ui.activity.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.right_text)
    TextView mRightText;
    @Bind(R.id.back_layout)
    RelativeLayout mBackLayout;
    @Bind(R.id.signout_layout)
    RelativeLayout mSignoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mTitle.setText(R.string.setting);
    }

    @OnClick({R.id.back, R.id.back_layout, R.id.signout_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.back_layout:
                break;
            case R.id.signout_layout:
                goAndFinish(LoginActivity.class);
                break;
        }
    }
}
