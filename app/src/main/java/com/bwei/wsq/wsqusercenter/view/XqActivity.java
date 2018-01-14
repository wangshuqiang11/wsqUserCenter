package com.bwei.wsq.wsqusercenter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.app.MyApp;
import com.bwei.wsq.wsqusercenter.bean.AddCartBean;
import com.bwei.wsq.wsqusercenter.bean.XqBean;
import com.bwei.wsq.wsqusercenter.presenter.XqPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XqActivity extends AppCompatActivity implements IXqActivity {

    @BindView(R.id.de_sdv)
    SimpleDraweeView mDeSdv;
    @BindView(R.id.de_tv1)
    TextView mDeTv1;
    @BindView(R.id.de_price)
    TextView mDePrice;
    @BindView(R.id.de_pj)
    TextView mDePj;
    @BindView(R.id.show_cart)
    Button mShowCart;
    @BindView(R.id.addcar)
    Button mAddcar;
    private XqPresenter xqPresenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        xqPresenter = new XqPresenter(this);
        xqPresenter.getxq(pid);
    }

    @OnClick({R.id.de_sdv, R.id.de_tv1, R.id.de_price, R.id.de_pj, R.id.show_cart, R.id.addcar})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.de_sdv:
                break;
            case R.id.de_tv1:
                break;
            case R.id.de_price:
                break;
            case R.id.de_pj:
                break;
            case R.id.show_cart:
                //跳转到购物车
                Intent intent = new Intent(XqActivity.this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.addcar:
                //添加购物车
                xqPresenter.addCart(pid);
                break;
        }
    }


    @Override
    public void addCart(AddCartBean addCartBean) {
        if ("0".equals(addCartBean.getCode())) {
            Toast.makeText(getApplicationContext(), addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void xqshow(XqBean xqBean) {
        XqBean.DataBean data = xqBean.getData();
        String images = data.getImages();
        String[] split = images.split("\\|");
        mDeSdv.setImageURI(split[0]);
        mDeTv1.setText(data.getTitle());
        mDePrice.setText("¥"+data.getPrice());
    }
}
