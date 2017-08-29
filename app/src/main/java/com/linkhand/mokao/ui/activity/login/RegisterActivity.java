package com.linkhand.mokao.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.LoginSend;

import com.shcyd.lib.utils.RegexUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.linkhand.mokao.api.ConnectUrl.LOGIN_MIMA;
import static com.linkhand.mokao.api.ConnectUrl.LOGIN_SENDPHONECODE;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.phone)
    EditText mPhoneET;
    @Bind(R.id.code_edit)
    EditText mCodeET;
    @Bind(R.id.btn_yanzheng)
    TextView mYanzhengTV;
    private String phone;
    private String password;
    private int code=0;


    private static Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        mTitle.setText(R.string.register);
    }

    @OnClick({R.id.back, R.id.btn_yanzheng, R.id.next_step})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_yanzheng:
                //发送验证码
                judgeData();
                break;
            case R.id.next_step:
                //下一步
                juData();
                break;
        }
    }
    //发送验证码，验证手机号
    private void judgeData() {
        phone = mPhoneET.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToast(getString(R.string.toast_phone_notnull));
            return;
        }
        if (!RegexUtils.isMobileExact(phone)) {
            showToast(getString(R.string.toast_phone_yes));
            return;
        }

        send(phone);
    }
    //下一步验证
    private void juData() {
        phone = mPhoneET.getText().toString();
        password = mCodeET.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToast(getString(R.string.toast_phone_notnull));
            return;
        }
        if (!RegexUtils.isMobileExact(phone)) {
            showToast(getString(R.string.toast_phone_yes));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast(getString(R.string.toast_code_notnull));
            return;
        }
        if (password.length() < 3) {
            showToast(getString(R.string.toast_code_six));
            return;
        }
        //验证 ：验证码是否正确
        if(code!=0&&password.toString().equals(code+"")){
//            进入下一步
            goAndFinish(SetPasswordActivity.class);
        }else{
            showToast(getString(R.string.toast_code_six));

        }

    }

    private void send(String phone){
//第一步：创建Nohttp请求对列（如果是本类使用的比较频繁，在onCreate的时候初始化一次就行了，这里是为了怕忘记这个步骤）
        final RequestQueue requestQueues = NoHttp.newRequestQueue();
        //第二步：创建请求对象（url是请求路径， RequestMethod.POST是请求方式）
        Request<String> stringPostRequest = NoHttp.createStringRequest(LOGIN_SENDPHONECODE, RequestMethod.POST);
        // 添加请求参数例如"http://www.sciencenet.cn/xml/iphoneinterface.aspx?type=news&nums=20"
        stringPostRequest.add("phone", phone);
        //第三步：加入到请求对列中，requestQueues.add()分别是请求列的请求标志，请求对象，监听回调
        requestQueues.add(2, stringPostRequest, new SimpleResponseListener<String>() {
            @Override//请求成功的回调
            public void onSucceed(int i, Response<String> response) {
                Log.i("s", "onSucceed: " + response);
                //  Toast.makeText(LoginActivity.this, "noHttpPostString请求成功" + response.get(), Toast.LENGTH_LONG).show();

                LoginSend jsonTest= mGson.fromJson(response.get(),LoginSend.class);
                if(jsonTest.getCode()==100){
                    //没有注册
                    Toast.makeText(RegisterActivity.this, jsonTest.getMsg(), Toast.LENGTH_LONG).show();

                }else if(jsonTest.getCode()==200){
                    // 登录成功，保存用户信息
                    Toast.makeText(RegisterActivity.this,jsonTest.getMsg() , Toast.LENGTH_LONG).show();
                    code=jsonTest.getInfo();
                }

            }

            //请求失败的回调
            public void onFailed(int i, String s, Object o, Exception e, int i1, long l) {
                Log.e("failed", "onFailed: " + e);
            }
        });
    }
}
