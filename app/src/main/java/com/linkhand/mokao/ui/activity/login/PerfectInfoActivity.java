package com.linkhand.mokao.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.ui.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerfectInfoActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.name)
    EditText mNameET;
    @Bind(R.id.age_edit)
    EditText mAgeET;
    @Bind(R.id.radiogroup)
    RadioGroup mRadiogroup;
    @Bind(R.id.hangye_edit)
    EditText mHangyeET;
    @Bind(R.id.company_name)
    EditText mCompanyET;
    @Bind(R.id.email)
    EditText mEmailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitle.setText(R.string.perfectinfo);
    }

    @OnClick({R.id.back, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.finish:
                go(MainActivity.class);
                break;
        }
    }
}
