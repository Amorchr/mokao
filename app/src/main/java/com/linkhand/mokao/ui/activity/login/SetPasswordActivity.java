package com.linkhand.mokao.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Questions;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPasswordActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.password_second)
    EditText mPasswordSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);
        if (extras!=null){
            Questions questions = (Questions) extras.getSerializable("q");
        }
    }

    private void initView() {
        mTitle.setText(R.string.setpassword);
    }

    @OnClick({R.id.back, R.id.subimt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.subimt:
                goAndFinish(PerfectInfoActivity.class);
                break;
        }
    }
}
