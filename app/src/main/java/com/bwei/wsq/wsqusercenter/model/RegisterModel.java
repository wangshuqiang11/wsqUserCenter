package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.RegisterBean;
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

public class RegisterModel implements IRegisterModel {
    @Override
    public void register(String mobile, String password, final OnNetListener<RegisterBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServerApi();
        Observable<RegisterBean> register = serviceApi.register(mobile, password);
        register.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        onNetListener.onSuccess(registerBean);
                    }
                });
    }
}
