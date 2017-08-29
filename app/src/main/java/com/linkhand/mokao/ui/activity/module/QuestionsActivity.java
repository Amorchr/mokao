package com.linkhand.mokao.ui.activity.module;

import android.content.Context;
import android.os.Bundle;
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

/**
 * 题库
 */

public class QuestionsActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.listview)
    ListView mListview;
    String title = "";
    CommonAdapter mAdapter;
    List<Questions> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
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
                bundle.putString("title",mList.get(position).getName());
                go(ExaminationTopActivity.class,bundle);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);
        if (extras != null) {
            title = extras.getString("title");
        }
    }

    private void initView() {
        mTitle.setText(title + "考题库");
    }

    private void initData() {
        mList = new ArrayList<>();
        mAdapter = new MyAdapter(this, R.layout.item_questions, mList);
        mListview.setAdapter(mAdapter);
        getData();
    }

    private void getData() {
        for (int i = 0; i < 9; i++) {
            mList.add(new Questions("标题" + i, "100分钟", 333));
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    class MyAdapter extends CommonAdapter {

        public MyAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            TextView join = holder.getView(R.id.join);
            TextView time = holder.getView(R.id.time);
            TextView name = holder.getView(R.id.name);

            join.setText(mList.get(position).getJoin() + "人参加");
            time.setText(mList.get(position).getTime());
            name.setText(mList.get(position).getName());

        }
    }
}
