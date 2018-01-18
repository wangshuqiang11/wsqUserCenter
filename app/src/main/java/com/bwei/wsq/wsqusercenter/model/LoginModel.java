package com.bwei.wsq.wsqusercenter.model;


import com.bwei.wsq.wsqusercenter.bean.LoginBean;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.net.RetrofitHelper;
import com.bwei.wsq.wsqusercenter.net.ServiceApi;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public class LoginModel implements ILoginModel{
    @Override
    public void login(String mobile, String password, final OnNetListener<LoginBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServerApi();
        Observable<LoginBean> login = serviceApi.login(mobile, password);
        login.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        onNetListener.onSuccess(loginBean);
                    }
                });
    }
}

