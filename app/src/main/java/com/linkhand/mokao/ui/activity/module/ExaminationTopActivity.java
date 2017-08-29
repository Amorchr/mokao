package com.linkhand.mokao.ui.activity.module;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 考前说明
 */

public class ExaminationTopActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.release_time)
    TextView mReleaseTime;
    @Bind(R.id.content)
    TextView mContent;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.score)
    TextView mScore;
    @Bind(R.id.cishu)
    TextView mCishu;
    @Bind(R.id.submit)
    TextView mSubmit;
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_top);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mTitle.setText(title);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);
        if (extras != null) {
            title = extras.getString("title");
        }
    }

    @OnClick({R.id.back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        dialog.setContentView(R.layout.dialog_prompt_common);
        TextView examTimeTV = (TextView) dialog.findViewById(R.id.dialog_exam_time);
        String html = "消耗" + "<font color='#D52D2C' >" + 10 + "</font> 次考试次数";
        examTimeTV.setText(Html.fromHtml(html));
        TextView noTV = (TextView) dialog.findViewById(R.id.notextview);
        TextView yesTV = (TextView) dialog.findViewById(R.id.yestextview);
        noTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                go(AnswerActivity.class);

            }
        });
        dialog.show();

    }
}
