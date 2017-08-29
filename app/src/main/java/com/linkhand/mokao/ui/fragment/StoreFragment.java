package com.linkhand.mokao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JCY on 2017/8/18.
 * 说明：
 */

public class StoreFragment extends BaseFragment {
    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.right_text)
    TextView mRightText;
    @Bind(R.id.null_iv)
    ImageView mNullIv;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_store, null);
        ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        mBack.setVisibility(View.GONE);
        mTitle.setText(R.string.store);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
