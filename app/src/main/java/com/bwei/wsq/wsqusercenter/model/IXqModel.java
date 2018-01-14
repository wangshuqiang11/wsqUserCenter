package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.AddCartBean;
import com.bwei.wsq.wsqusercenter.bean.XqBean;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public interface IXqModel {
    public void getShow(OnNetListener<XqBean> onNetListener, String pid);
    public void addCart(String uid, String pid, OnNetListener<AddCartBean> onNetListener);
}
