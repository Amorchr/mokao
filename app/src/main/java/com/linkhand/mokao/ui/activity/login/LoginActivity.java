package com.linkhand.mokao.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.linkhand.mokao.R;
import com.linkhand.mokao.base.BaseActivity;
import com.linkhand.mokao.entity.Loginresult;
import com.linkhand.mokao.ui.activity.MainActivity;
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
import static com.linkhand.mokao.api.ConnectUrl.PRODUCT_INDEX;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.username)
    EditText mUsernameET;
    @Bind(R.id.password)
    EditText mPasswordET;
    private String username;
    private String password;
    private static Gson mGson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login, R.id.register, R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                judgeData();
                break;
            case R.id.register:
                go(RegisterActivity.class);
                break;
            case R.id.forget_password:
                go(ForgetPassActivity.class);
                break;
        }
    }

    private void judgeData() {
        username = mUsernameET.getText().toString();
        password = mPasswordET.getText().toString();
        if (TextUtils.isEmpty(username)) {
            showToast(getString(R.string.toast_phone_notnull));
            return;
        }
        if (!RegexUtils.isMobileExact(username)) {
            showToast(getString(R.string.toast_phone_yes));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast(getString(R.string.toast_pass_notnull));
            return;
        }
        if (password.length() < 6) {
            showToast(getString(R.string.toast_pass_six));
            return;
        }
        httpLogin(username,password);
    }
    //密码登录
    private void httpLogin(String username,String password){

        //第一步：创建Nohttp请求对列（如果是本类使用的比较频繁，在onCreate的时候初始化一次就行了，这里是为了怕忘记这个步骤）
        final RequestQueue requestQueues = NoHttp.newRequestQueue();
               //第二步：创建请求对象（url是请求路径， RequestMethod.POST是请求方式）
               Request<String> stringPostRequest = NoHttp.createStringRequest( LOGIN_MIMA, RequestMethod.POST);
              // 添加请求参数例如"http://www.sciencenet.cn/xml/iphoneinterface.aspx?type=news&nums=20"
             stringPostRequest.add("phone", username);
                stringPostRequest.add("password", password);
               //第三步：加入到请求对列中，requestQueues.add()分别是请求列的请求标志，请求对象，监听回调
                requestQueues.add(2, stringPostRequest, new SimpleResponseListener<String>() {
            @Override//请求成功的回调
           public void onSucceed(int i, Response<String> response) {
                              Log.i("s", "onSucceed: " + response);
              //  Toast.makeText(LoginActivity.this, "noHttpPostString请求成功" + response.get(), Toast.LENGTH_LONG).show();

                Loginresult jsonTest= mGson.fromJson(response.get(),Loginresult.class);
                            if(jsonTest.getCode()==100){
                                //没有注册
                                Toast.makeText(LoginActivity.this, "此号码没有注册", Toast.LENGTH_LONG).show();

                            }else if(jsonTest.getCode()==200){
                               // 登录成功，保存用户信息
                                go(MainActivity.class);

                            }else if(jsonTest.getCode()==300){
                                //密码不正确
                                Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_LONG).show();

                            }

                             }

        //请求失败的回调
       public void onFailed(int i, String s, Object o, Exception e, int i1, long l) {
                              Log.e("failed", "onFailed: " + e);
                            }
        });

    }

}
