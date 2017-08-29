package com.linkhand.mokao.ui.activity.module;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.widget.ListView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Error;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 错题库
 */

public class ErrorQuestionActivity extends BaseActivity {
    String title = "";
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.listview)
    ListView mListview;
    CommonAdapter mAdapter;
    List<Error> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_exam);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        mAdapter = new MyAdapter(this, R.layout.item_error_list, mList);
        mListview.setAdapter(mAdapter);
        getData();
    }

    private void getData() {
        for (int i = 0; i < 5; i++) {
            mList.add(new Error("题目水电费时的发生的发生的发生大法师打发斯蒂芬撒地方发生" + i, i, 100));
        }
        mAdapter.notifyDataSetChanged();
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
            TextView count = holder.getView(R.id.num);
            TextView name = holder.getView(R.id.exam_name);
            count.setText(Html.fromHtml( "<font color='#D52D2C'><big>" + mList.get(position).getCurrent() + "</big></font>" + "/"+mList.get(position).getCount()));
            name.setText(mList.get(position).getName());
        }
    }
}
