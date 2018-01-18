package com.bwei.wsq.wsqusercenter.presenter;

import com.bwei.wsq.wsqusercenter.bean.LoginBean;
import com.bwei.wsq.wsqusercenter.model.ILoginModel;
import com.bwei.wsq.wsqusercenter.model.LoginModel;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.view.ILoginView;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public class LoginPresenter {
    private final ILoginModel iLoginModel;
    private final ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModel();
    }

    public void login() {
        String mobile = iLoginView.mobile();
        String pwd = iLoginView.pwd();

        iLoginModel.login(mobile, pwd, new OnNetListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                iLoginView.showSuccess(loginBean.getMsg());
               /* LoginBean.DataBean data = loginBean.getData();
                data.getUid();*/
                if (loginBean.getCode().equals("0")){
                    iLoginView.toRegisterAc();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
    public void register(){
        iLoginView.toRegisterAc();
    }
}
