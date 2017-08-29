package com.linkhand.mokao.ui.activity.my;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Pay;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.right_text)
    TextView mSetCardTV;
    @Bind(R.id.gridview)
    GridView mGridview;
    @Bind(R.id.submit)
    TextView mSubmitTV;
    CommonAdapter mAdapter;
    List<Pay> mPayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }


    private void initView() {
        mTitle.setText(R.string.pay_title);
        mSetCardTV.setText(R.string.set_card);
        mSetCardTV.setVisibility(View.VISIBLE);
    }

    private void initData() {
        mPayList = new ArrayList<>();
        mAdapter = new MyAdapter(this, R.layout.item_grid_pay, mPayList);
        mGridview.setAdapter(mAdapter);
        getData();
    }

    private void getData() {
        Pay pay1 = new Pay("15", 5, 30, true);
        Pay pay2 = new Pay("30", 11, 60, false);
        Pay pay3 = new Pay("60", 25, 90, false);

        mPayList.add(pay1);
        mPayList.add(pay2);
        mPayList.add(pay3);
    }

    private void initListener() {
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position", "onItemClick: " + position);
                if (!mPayList.get(position).isFlag()) {
                    for (int i = 0; i < mPayList.size(); i++) {
                        if (i == position) {
                            mPayList.get(i).setFlag(true);
                        } else {
                            mPayList.get(i).setFlag(false);
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.back, R.id.right_text, R.id.submit, R.id.xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_text:
                break;
            case R.id.submit:
                break;
            case R.id.xieyi:
                break;
        }
    }


    class MyAdapter extends CommonAdapter {

        public MyAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            RelativeLayout layout = holder.getView(R.id.layout);
            TextView price = holder.getView(R.id.price);
            TextView time = holder.getView(R.id.time);
            TextView youxiao = holder.getView(R.id.youxiao);
            Pay pay = mPayList.get(position);
            price.setText(pay.getPrice() + "元");
            time.setText("/" + pay.getTime() + "次");
            youxiao.setText("(" + pay.getDay() + "有效)");
            if (pay.isFlag()) {
                layout.setBackground(getDrawable(R.drawable.bg_textview_small_corner_redtopic));
                price.setTextColor(getResources().getColor(R.color.colorWhite));
                time.setTextColor(getResources().getColor(R.color.colorWhite));
                youxiao.setTextColor(getResources().getColor(R.color.colorWhite));
            } else {
                layout.setBackground(getDrawable(R.drawable.bg_textview_small_corner_white));
                price.setTextColor(getResources().getColor(R.color.grayText));
                time.setTextColor(getResources().getColor(R.color.grayText));
                youxiao.setTextColor(getResources().getColor(R.color.grayText));
            }
        }
    }
}
