package com.linkhand.mokao.ui.activity.module;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Exam;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class AnswerActivity extends BaseActivity {

    @Bind(R.id.back_text)
    TextView mBackText;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.countdown)
    CountdownView mCountdown;
    @Bind(R.id.answer_title)
    TextView mAnswerTitle;
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.current_answer)
    TextView mCurrentAnswer;
    @Bind(R.id.prev_answer)
    TextView mPrevAnswer;
    @Bind(R.id.next_answer)
    TextView mNextAnswer;
    @Bind(R.id.bottom_layout)
    LinearLayout mBottomLayout;
    CommonAdapter mAdapter;
    List<Exam> mList;
    List<Exam.Answer> mAnswerList;
    @Bind(R.id.right_answer)
    TextView mRightAnswerTV;
    @Bind(R.id.analysis)
    TextView mAnalysisTV;
    @Bind(R.id.analysis_layout)
    ScrollView mAnalysisLayout;
    //当前显示的题目
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();

    }

    private void initView() {
        mTitle.setText(R.string.examing);
    }

    private void initListener() {
        mCountdown.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                showToast("时间到");// 处理逻辑
            }
        });
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!mAnswerList.get(position).isFlag()) {
                    for (int i = 0; i < mAnswerList.size(); i++) {
                        if (i == position) {
                            mAnswerList.get(i).setFlag(true);
                        } else {
                            mAnswerList.get(i).setFlag(false);
                        }
                    }
                }
                mAdapter.notifyDataSetInvalidated();
            }
        });
    }

    private void initData() {
        current = 0;
        mList = new ArrayList<>();
        mAnswerList = new ArrayList<>();
        getData();
    }

    private void getData() {
        for (int i = 0; i < 5; i++) {
            Exam exam = new Exam();
            exam.setQuestion("题目" + i);
            List<Exam.Answer> answers = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Exam.Answer answer = new Exam.Answer("题目" + i + "选项" + j);
                answers.add(answer);
            }
            exam.setAnswers(answers);
            mList.add(exam);
        }
        mCountdown.start(60 * 60 * 60 * 1000L);
        mAnswerTitle.setText(mList.get(current).getQuestion());
        mCurrentAnswer.setText(Html.fromHtml("<font color = '#D52D2C'><big>" + (current + 1) + "</big></font>" + "/" + mList.size()));
        mAnswerList = mList.get(0).getAnswers();
        mAdapter = new MyAdapter(this, R.layout.item_answer, mAnswerList);
        mListview.setAdapter(mAdapter);
    }

    @OnClick({R.id.back_text, R.id.prev_answer, R.id.next_answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_text:
                showDialog();
                break;
            case R.id.prev_answer:
                jugePre();
                break;
            case R.id.next_answer:
                jugeNext();
                break;
        }
    }

    /**
     * 判断上一题
     */
    private void jugePre() {
        if (current > 0) {
            current--;
            if (current != mList.size() - 1)
                mNextAnswer.setText(R.string.pre_question);
            mAnswerTitle.setText(mList.get(current).getQuestion());
            mCurrentAnswer.setText(Html.fromHtml("<font color = '#D52D2C'><big>" + (current + 1) + "</big></font>" + "/" + mList.size()));
            mAnswerList = mList.get(current).getAnswers();
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 判断下一题
     */
    private void jugeNext() {
        boolean flag = false;  //判断该题是否选择了。
        for (int i = 0; i < mAnswerList.size(); i++) {
            if (mAnswerList.get(i).isFlag()) {
                flag = true;
            }
        }
        if (!flag) {
            showToast(getString(R.string.toast_select_option));
            return;
        }
        if (current < mList.size() - 1) {//是否允许下一题
            current++;
            if (current == mList.size() - 1)
                mNextAnswer.setText(R.string.submit_exam);
            mAnswerTitle.setText(mList.get(current).getQuestion());
            mCurrentAnswer.setText(Html.fromHtml("<font color = '#D52D2C'><big>" + (current + 1) + "</big></font>" + "/" + mList.size()));
            mAnswerList = mList.get(current).getAnswers();
            mAdapter.notifyDataSetChanged();
        } else if (current == mList.size() - 1) {
            go(FinishExamActivity.class);
        }

    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        dialog.setContentView(R.layout.dialog_prompt_common);
        ImageView iv = (ImageView) dialog.findViewById(R.id.image);
        iv.setImageDrawable(getDrawable(R.drawable.jieshu));
        TextView examTimeTV = (TextView) dialog.findViewById(R.id.dialog_exam_time);
        examTimeTV.setText("");
        TextView messageTV = (TextView) dialog.findViewById(R.id.dialog_message);
        messageTV.setText(R.string.finish_current_exam);
        TextView noTV = (TextView) dialog.findViewById(R.id.notextview);
        TextView yesTV = (TextView) dialog.findViewById(R.id.yestextview);
        yesTV.setText(R.string.yes);
        noTV.setText(R.string.continue_exam);
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
                finish();

            }
        });
        dialog.show();

    }


    class MyAdapter extends CommonAdapter {

        public MyAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            TextView t = holder.getView(R.id.title);
            TextView content = holder.getView(R.id.content);
            if (mAnswerList.get(position).isFlag()) {
                t.setTextColor(getResources().getColor(R.color.colorTopic));
                content.setTextColor(getResources().getColor(R.color.colorTopic));
            } else {
                t.setTextColor(getResources().getColor(R.color.black));
                content.setTextColor(getResources().getColor(R.color.blackText));
            }
            t.setText(position + ".");
            content.setText(mAnswerList.get(position).getAnswers());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
