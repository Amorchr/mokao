package com.linkhand.mokao.ui.activity.module;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Sort;
import com.linkhand.mokao.ui.fragment.SortFragment;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 考题分类
 */

public class SortActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.sort_title)
    ListView mSortListview;
    @Bind(R.id.right_framelayout)
    FrameLayout mFramelayout;
    CommonAdapter mAdapter;

    List<Sort> mSortList;
    private SortFragment mSortFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);
        if (extras != null) {
            title = extras.getString("title");
        }
    }

    private void initView() {
        mTitle.setText(title);
    }


    private void initData() {
        mFragmentManager = getSupportFragmentManager();

        mSortList = new ArrayList<>();
        mAdapter = new MyAdapter(this, R.layout.item_sort_title, mSortList);
        mSortListview.setAdapter(mAdapter);
        getData();
    }

    private void initListener() {
        mSortListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!mSortList.get(position).isFlag()) {
                    for (int i = 0; i < mSortList.size(); i++) {
                        if (i == position) {
                            mSortList.get(i).setFlag(true);
                        } else {
                            mSortList.get(i).setFlag(false);
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();

                mSortFragment = new SortFragment();
                mTransaction = mFragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) mSortList.get(position).getList());
                mSortFragment.setArguments(bundle);
                mTransaction.replace(R.id.right_framelayout, mSortFragment);
                mTransaction.commit();
            }
        });
    }

    public void getData() {
        for (int i = 0; i < 8; i++) {
            Sort sort = new Sort();
            sort.setTitle("标题" + i);
            List<Sort.SortContent> list = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Sort.SortContent content = new Sort.SortContent();
                content.setName("标题" + i + " 内容" + j);
                list.add(content);
            }
            sort.setList(list);
            mSortList.add(sort);
        }
        mSortList.get(0).setFlag(true);
        mAdapter.notifyDataSetChanged();
        mSortFragment = new SortFragment();
        mTransaction = mFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) mSortList.get(0).getList());
        mSortFragment.setArguments(bundle);
        mTransaction.replace(R.id.right_framelayout, mSortFragment);
        mTransaction.commit();
    }


    class MyAdapter extends CommonAdapter {

        public MyAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            LinearLayout bg = holder.getView(R.id.bg);
            TextView title = holder.getView(R.id.title);
            View line = holder.getView(R.id.line);
            title.setText(mSortList.get(position).getTitle());
            Sort sort = mSortList.get(position);
            if (position == mSortList.size())
                line.setVisibility(View.GONE);
            if (sort.isFlag()) {
                line.setBackground(getDrawable(R.color.colorTopic));
                bg.setBackground(getDrawable(R.color.defaultBackgroundGray));
            } else {
                line.setBackground(getDrawable(R.color.grayeDAD));
                bg.setBackground(getDrawable(R.color.graye7e7));
            }
        }
    }
}
