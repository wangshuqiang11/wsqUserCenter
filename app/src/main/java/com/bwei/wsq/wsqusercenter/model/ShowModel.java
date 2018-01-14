package com.bwei.wsq.wsqusercenter.model;

import com.bwei.wsq.wsqusercenter.bean.ShowBean;
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

public class ShowModel implements IShowModel {
    @Override
    public void getData(final OnNetListener<ShowBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServerApi();
        Observable<ShowBean> showbean = serviceApi.showbean();
        showbean.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowBean>() {
                    @Override
                    public void accept(ShowBean showBean) throws Exception {
                        onNetListener.onSuccess(showBean);
                    }
                });
    }
}
