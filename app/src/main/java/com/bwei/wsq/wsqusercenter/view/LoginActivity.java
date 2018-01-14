package com.bwei.wsq.wsqusercenter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.presenter.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    /**
     * 请输入手机号
     */

    /**
     * 请输入密码
     */

    /**
     * 登录
     */

    /**
     * 新用户注册
     */
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //EventBus.getDefault().register(this);
        initView();
        presenter = new LoginPresenter(this);

    }

    private void initView() {
        mEtMobile = (EditText) findViewById(R.id.et_mobile);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(this);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
    }
    @Override
    public String mobile() {
        return mEtMobile.getText().toString().trim();
    }

    @Override
    public String pwd() {
        return mEtPassword.getText().toString().trim();
    }

    @Override
    public void showSuccess(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(String string) {
        Toast.makeText(LoginActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toRegisterAc() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void Tosend() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.et_mobile, R.id.et_password, R.id.tv_register, R.id.bt_login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.et_mobile:
                break;
            case R.id.et_password:
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_login:
                presenter.login();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }
}
