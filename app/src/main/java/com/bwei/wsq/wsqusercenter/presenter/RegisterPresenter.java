package com.bwei.wsq.wsqusercenter.presenter;

import com.bwei.wsq.wsqusercenter.bean.RegisterBean;
import com.bwei.wsq.wsqusercenter.model.IRegisterModel;
import com.bwei.wsq.wsqusercenter.model.RegisterModel;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.view.IRegisterView;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public class RegisterPresenter {
    private final IRegisterModel iRegisterModel;
    private final IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        iRegisterModel = new RegisterModel();
    }

    public void register() {
        String mobile = iRegisterView.mobile();
        String pwd = iRegisterView.pwd();
        iRegisterModel.register(mobile, pwd, new OnNetListener<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean registerBean) {
                iRegisterView.showSuccess(registerBean.getMsg());
                if(registerBean.getCode().equals("0")){
                    iRegisterView.toLoginAc();
                }
            }

            @Override
            public void onFailure(Exception e) {
                iRegisterView.showFailure(e+"");
            }
        });
    }
}
