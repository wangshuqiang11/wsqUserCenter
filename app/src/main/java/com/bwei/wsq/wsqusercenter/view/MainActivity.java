package com.bwei.wsq.wsqusercenter.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwei.wsq.wsqusercenter.R;
import com.bwei.wsq.wsqusercenter.adapter.MyAdapter;
import com.bwei.wsq.wsqusercenter.bean.ShowBean;
import com.bwei.wsq.wsqusercenter.presenter.ShowPresenter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @BindView(R.id.rc)
    RecyclerView mRc;
    private ShowPresenter presenter;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new ShowPresenter(this);
        presenter.getShow();
        mRc.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onshow(ShowBean showBean) {
        List<ShowBean.TuijianBean.ListBean> list = showBean.getTuijian().getList();

        myAdapter = new MyAdapter(list, this);
        mRc.setAdapter(myAdapter);

        myAdapter.setOnclick(new MyAdapter.Onclick() {
            @Override
            public void Onclik(String pid) {
                Intent intent = new Intent(MainActivity.this, XqActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
}
