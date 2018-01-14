package com.bwei.wsq.wsqusercenter.presenter;

import com.bwei.wsq.wsqusercenter.bean.AddCartBean;
import com.bwei.wsq.wsqusercenter.bean.XqBean;
import com.bwei.wsq.wsqusercenter.model.IXqModel;
import com.bwei.wsq.wsqusercenter.model.XqModel;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.view.IXqActivity;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class XqPresenter {
    private IXqModel iXqModel;
    private IXqActivity iXqActivity;

    public XqPresenter(IXqActivity iXqActivity) {
        this.iXqActivity = iXqActivity;
        iXqModel = new XqModel();
    }
    public void getxq(String pid){
        iXqModel.getShow(new OnNetListener<XqBean>() {
            @Override
            public void onSuccess(XqBean xqBean) {
                iXqActivity.xqshow(xqBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        },pid);
    }
    public void addCart(String pid) {
        iXqModel.addCart("5408", pid, new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                iXqActivity.addCart(addCartBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
