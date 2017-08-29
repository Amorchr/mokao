package com.linkhand.mokao.ui.activity.module;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.ui.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 考试结束
 */
public class FinishExamActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.back_text)
    TextView mBackText;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.score)
    TextView mScoreTV;
    @Bind(R.id.error_count)
    TextView mErrorCountTV;
    @Bind(R.id.use_time)
    TextView mUseTimeTV;
    @Bind(R.id.retest)
    TextView mRetest;
    @Bind(R.id.look_error)
    TextView mLookError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_exam);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTitle.setText(R.string.finish_exam_ac);
        mBackText.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back, R.id.back_text, R.id.retest, R.id.look_error})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                go(MainActivity.class);
                break;
            case R.id.back_text:
                go(MainActivity.class);
                break;
            case R.id.retest:
                break;
            case R.id.look_error:
                go(LookErrorQuestionActivity.class);
                break;
        }
    }

}
