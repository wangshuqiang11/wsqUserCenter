package com.bwei.wsq.wsqusercenter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.presenter.RegisterPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.bt_register)
    Button mBtRegister;
    /**
     * 请输入手机号
     */

    /**
     * 请输入密码
     */

    /**
     * 注册
     */
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        registerPresenter = new RegisterPresenter(this);
        EventBus.getDefault().register(this);
    }

    private void initView() {

        mEtMobile = (EditText) findViewById(R.id.et_mobile);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtRegister = (Button) findViewById(R.id.bt_register);
        mBtRegister.setOnClickListener(this);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mEtMobile.setOnClickListener(this);
        mEtPassword.setOnClickListener(this);
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
    public void showFailure(String str) {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toLoginAc() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.back, R.id.et_mobile, R.id.et_password, R.id.bt_register})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.et_mobile:
                break;
            case R.id.et_password:
                break;
            case R.id.bt_register:
                registerPresenter.register();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}




