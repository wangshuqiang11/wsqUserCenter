package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.LoginBean;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public interface ILoginModel {
    public void login(String mobile, String password, OnNetListener<LoginBean> onNetListener);
}
