package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.AddCartBean;
import com.bwei.wsq.wsqusercenter.bean.XqBean;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.net.RetrofitHelper;
import com.bwei.wsq.wsqusercenter.net.ServiceApi;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class XqModel implements IXqModel {
    @Override
    public void getShow(final OnNetListener<XqBean> onNetListener, String pid) {
        ServiceApi serviceApi = RetrofitHelper.getServerApi();
        Observable<XqBean> xqbean = serviceApi.xqbean(pid);
        xqbean.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XqBean>() {
                    @Override
                    public void accept(XqBean xqBean) throws Exception {
                        onNetListener.onSuccess(xqBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure((Exception) throwable);
                    }
                });
    }

    @Override
    public void addCart(String uid, String pid, final OnNetListener<AddCartBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServerApi();
        serviceApi.getAddCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCartBean>() {
                    @Override
                    public void accept(AddCartBean addCartBean) throws Exception {
                        onNetListener.onSuccess(addCartBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure((Exception) throwable);
                    }
                });
    }
}
