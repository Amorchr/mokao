package com.linkhand.mokao.ui.activity.module;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Questions;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookErrorQuestionActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.search_iv)
    ImageView mSearchIv;
    @Bind(R.id.listview)
    ListView mListview;
    List<Questions> mList;
    CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_error_question);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mList.get(position).getName());
                go(ErrorQuestionActivity.class, bundle);
            }
        });
    }


    private void initView() {
        mTitle.setText(R.string.look_error_exam);
        mSearchIv.setVisibility(View.VISIBLE);
    }

    private void initData() {
        mList = new ArrayList<>();
        mAdapter = new MyAdapter(this, R.layout.item_look_error, mList);
        mListview.setAdapter(mAdapter);
        getData();
    }


    @OnClick({R.id.back, R.id.search_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search_iv:

                break;
        }
    }

    public void getData() {
        for (int i = 0; i < 5; i++) {
            Questions questions = new Questions();
            questions.setError(i + 1);
            questions.setName("错题" + (i + 1));
            mList.add(questions);
        }
        mAdapter.notifyDataSetChanged();
    }

    class MyAdapter extends CommonAdapter {

        public MyAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            TextView count = holder.getView(R.id.error_count);
            TextView name = holder.getView(R.id.exam_name);
            count.setText(Html.fromHtml("错题" + "<font color='#D52D2C'>" + mList.get(position).getError() + "</font>" + "（点击查看详情）"));
            name.setText(mList.get(position).getName());
        }
    }
}
