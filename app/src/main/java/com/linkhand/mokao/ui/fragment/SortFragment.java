package com.linkhand.mokao.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseFragment;
import com.linkhand.mokao.entity.Sort;
import com.linkhand.mokao.ui.activity.module.QuestionsActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JCY on 2017/8/21.
 * 说明：分类
 */

public class SortFragment extends BaseFragment {

    @Bind(R.id.gridview)
    GridView mGridview;
    @Bind(R.id.layout_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    private View mView;
    List<Sort.SortContent> mList;
    CommonAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sort, null);
        ButterKnife.bind(this, mView);
        initView();
        initData();
        initListener();
        return mView;
    }


    private void initData() {
        Bundle bundle = getArguments();
        mList = (List<Sort.SortContent>) bundle.getSerializable("list");
        if (!adjustList(mList)) {
            mList = new ArrayList<>();
        }
        mAdapter = new GridviewAdapter(getActivity(), R.layout.item_sort_content, mList);
        mGridview.setAdapter(mAdapter);
    }

    private void initView() {

    }

    private void initListener() {
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mList.get(position).getName());
                go(QuestionsActivity.class, bundle);
            }
        });
    }


    class GridviewAdapter extends CommonAdapter {

        public GridviewAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);

        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            TextView title = holder.getView(R.id.title);
            title.setText(mList.get(position).getName());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
