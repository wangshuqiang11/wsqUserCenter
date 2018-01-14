package com.bwei.wsq.wsqusercenter.net;

import com.bwei.wsq.wsqusercenter.bean.AddCartBean;
import com.bwei.wsq.wsqusercenter.bean.CartBean;
import com.bwei.wsq.wsqusercenter.bean.LoginBean;
import com.bwei.wsq.wsqusercenter.bean.RegisterBean;
import com.bwei.wsq.wsqusercenter.bean.ShowBean;
import com.bwei.wsq.wsqusercenter.bean.XqBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public interface ServiceApi {
    //登录
    @GET(URL_API.LOGIN_URL)
    Observable<LoginBean> login(@Query("mobile") String mobile, @Query("password") String password);

    //注册
    @GET(URL_API.REGISTER_URL)
    Observable<RegisterBean> register(@Query("mobile") String mobile, @Query("password") String password);
    //首页列表
    @GET(URL_API.URL)
    Observable<ShowBean> showbean();
    //详情
    @GET(URL_API.XQ_URL)
    Observable<XqBean> xqbean(@Query("pid") String pid);
    //添加购物车
    @GET(URL_API.ADDCART)
    Observable<AddCartBean> getAddCart(@Query("uid") String uid, @Query("pid") String pid);

    //查询购物车
    @GET(URL_API.GETCARTS)
    Observable<CartBean> getCarts(@Query("uid") String uid);

}
