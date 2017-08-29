package com.linkhand.mokao.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseFragment;
import com.linkhand.mokao.customview.NoScrollGridView;
import com.linkhand.mokao.entity.Getbanner;
import com.linkhand.mokao.entity.HomeSort;
import com.linkhand.mokao.entity.Loginresult;
import com.linkhand.mokao.ui.activity.MainActivity;
import com.linkhand.mokao.ui.activity.login.LoginActivity;
import com.linkhand.mokao.ui.activity.module.SortActivity;
import com.linkhand.mokao.utils.ImageUtils;
import com.linkhand.mokao.utils.NetworkImageHolderView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.linkhand.mokao.api.ConnectUrl.LOGIN_MIMA;

/**
 * Created by JCY on 2017/8/18.
 * 说明：
 */

public class HomeFragment extends BaseFragment implements OnItemClickListener, ViewPager.OnPageChangeListener {
    @Bind(R.id.back)
    ImageView mBack;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.convenientBanner)
    ConvenientBanner mBanner;
    @Bind(R.id.gridview)
    NoScrollGridView mGridview;
    @Bind(R.id.layout_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;


    private View mView;
    private List<String> mPictureList;
    private List<HomeSort> mSortList;
    private CommonAdapter mAdapter;
    private static Gson mGson = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mView);
        initView();
        initData();
        initBanner();
        initListener();

        return mView;
    }


    private void initView() {
        mTitle.setText("省安检模拟考试");
        mBack.setVisibility(View.GONE);
    }

    private void initData() {
        mPictureList = new ArrayList<>();
        mPictureList.add("");

        mSortList = new ArrayList<>();
        mAdapter = new GridviewAdapter(getActivity(), R.layout.home_gridview_item, mSortList);
        mGridview.setAdapter(mAdapter);
        httpGetData();
    }

    private void initListener() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
            }
        });
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mSortList.get(position).getName());
                go(SortActivity.class,bundle);
            }
        });
    }

    private void initBanner() {
        mBanner.setPages(new CBViewHolderCreator() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, mPictureList)
                .setPageIndicator(new int[]{R.drawable.circle_grey, R.drawable.circle_red})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(this).setOnItemClickListener(this);
    }

    private void httpGetData() {
        for (int i = 0; i < 5; i++) {
            HomeSort homeSort = new HomeSort("名字", "");
            mSortList.add(homeSort);
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(int position) {

    }


    class GridviewAdapter extends CommonAdapter {

        public GridviewAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);

        }

        @Override
        protected void convert(ViewHolder holder, Object item, int position) {
            ImageView mIconIV = holder.getView(R.id.icon);
            TextView mNameTV = holder.getView(R.id.iconName);
            ImageUtils.setDefaultNormalImage(mIconIV, mSortList.get(position).getImageurl(), R.drawable.default_pic_show);
            mNameTV.setText(mSortList.get(position).getName());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    //获取首页banner信息
    private void getbanner(){

        //第一步：创建Nohttp请求对列（如果是本类使用的比较频繁，在onCreate的时候初始化一次就行了，这里是为了怕忘记这个步骤）
        final RequestQueue requestQueues = NoHttp.newRequestQueue();
        //第二步：创建请求对象（url是请求路径， RequestMethod.POST是请求方式）
        Request<JSONObject> stringPostRequest = NoHttp.createJsonObjectRequest(LOGIN_MIMA, RequestMethod.GET);

        //第三步：加入到请求对列中，requestQueues.add()分别是请求列的请求标志，请求对象，监听回调
        requestQueues.add(0, stringPostRequest, new SimpleResponseListener<String>() {
            @Override//请求成功的回调
            public void onSucceed(int i, Response<JSONObject> response) {
                Log.i("s", "onSucceed: " + response);
                //  Toast.makeText(LoginActivity.this, "noHttpPostString请求成功" + response.get(), Toast.LENGTH_LONG).show();

                Getbanner jsonTest= mGson.fromJson(response.get().toString(),Getbanner.class);
                if(jsonTest.getCode()==100){

                }else if(jsonTest.getCode()==200){

                }else if(jsonTest.getCode()==300){

                }

            }

            //请求失败的回调
            public void onFailed(int i, String s, Object o, Exception e, int i1, long l) {
                Log.e("failed", "onFailed: " + e);
            }
        });

    }
}
