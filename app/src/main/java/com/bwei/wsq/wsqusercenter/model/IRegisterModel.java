package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.RegisterBean;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public interface IRegisterModel {
    public void register(String mobile, String password, OnNetListener<RegisterBean> onNetListener);
}
