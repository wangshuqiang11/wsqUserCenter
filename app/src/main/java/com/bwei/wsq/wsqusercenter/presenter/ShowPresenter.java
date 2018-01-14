package com.bwei.wsq.wsqusercenter.presenter;

import com.bwei.wsq.wsqusercenter.bean.ShowBean;
import com.bwei.wsq.wsqusercenter.model.IShowModel;
import com.bwei.wsq.wsqusercenter.model.ShowModel;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.view.IMainActivity;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class ShowPresenter {
    private IShowModel iShowModel;
    private IMainActivity iMainActivity;

    public ShowPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        iShowModel = new ShowModel();
    }

    public void getShow(){
        iShowModel.getData(new OnNetListener<ShowBean>() {
            @Override
            public void onSuccess(ShowBean showBean) {
                iMainActivity.onshow(showBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
