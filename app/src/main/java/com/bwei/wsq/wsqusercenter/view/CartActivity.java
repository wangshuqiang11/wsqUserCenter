package com.bwei.wsq.wsqusercenter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.adapter.MyElvAdapter;
import com.bwei.wsq.wsqusercenter.bean.CartBean;
import com.bwei.wsq.wsqusercenter.eventbus.MessageEvent;
import com.bwei.wsq.wsqusercenter.eventbus.PriceAndCountEvent;
import com.bwei.wsq.wsqusercenter.presenter.CartPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements ICartView{

    private ExpandableListView mElv;
    private CheckBox mCbAll;
    /**
     * 全选
     */
    private TextView mTvQuxuan;
    /**
     * 合计 ：￥550.90
     */
    private TextView mTvPrice;
    /**
     * 总额:582.70  立减：￥31.80
     */
    private TextView mTvCartsPrice;
    /**
     * 去结算(0)
     */
    private TextView mTvNum;
    private List<List<CartBean.DataBean.ListBean>> lists;
    private MyElvAdapter myElvAdapter;
    private CartPresenter cartPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        EventBus.getDefault().register(this);

        cartPresenter = new CartPresenter(this);
        cartPresenter.getCarts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getCarts(CartBean cartBean) {
        List<CartBean.DataBean> dataBeans = cartBean.getData();
        lists = new ArrayList<>();
        for (int i = 0; i < dataBeans.size(); i++) {
            List<CartBean.DataBean.ListBean> list = dataBeans.get(i).getList();
            lists.add(list);
        }
        //设置适配器
        myElvAdapter = new MyElvAdapter(this, dataBeans, lists);
        mElv.setAdapter(myElvAdapter);

        for (int i = 0; i < dataBeans.size(); i++) {
            //默认二级列表展开
            mElv.expandGroup(i);
        }
        //取消小箭头
        mElv.setGroupIndicator(null);
    }


    private void initView() {
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mCbAll = (CheckBox) findViewById(R.id.cb_All);
        mTvQuxuan = (TextView) findViewById(R.id.tv_quxuan);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvCartsPrice = (TextView) findViewById(R.id.tv_carts_price);
        mTvNum = (TextView) findViewById(R.id.tv_num);
        //全选
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myElvAdapter.changeAllListCbState(mCbAll.isChecked());
            }
        });
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCbAll.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvPrice.setText("合计：￥" + event.getPrice() * event.getCount());
        mTvCartsPrice.setText("总额：￥" + event.getPrice() * event.getCount());
        mTvNum.setText("去结算(" + event.getCount() + ")");
    }
}
