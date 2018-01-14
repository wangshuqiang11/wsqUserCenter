package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.ShowBean;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public interface IShowModel {
    public void getData(OnNetListener<ShowBean> onNetListener);
}
