package com.bwei.wsq.wsqusercenter.presenter;

import com.bwei.wsq.wsqusercenter.bean.CartBean;
import com.bwei.wsq.wsqusercenter.model.CartModel;
import com.bwei.wsq.wsqusercenter.model.ICartModel;
import com.bwei.wsq.wsqusercenter.net.OnNetListener;
import com.bwei.wsq.wsqusercenter.view.ICartView;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class CartPresenter{
    private final ICartModel iCartModel;
    private ICartView iCartView;

    public CartPresenter(ICartView iCartView) {
        this.iCartView = iCartView;
        iCartModel = new CartModel();
    }

    public void getCarts() {
        iCartModel.getCart("5408", new OnNetListener<CartBean>() {
            @Override
            public void onSuccess(CartBean cartBean) {
                iCartView.getCarts(cartBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
