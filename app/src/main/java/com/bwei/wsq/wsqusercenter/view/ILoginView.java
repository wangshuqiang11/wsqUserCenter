package com.bwei.wsq.wsqusercenter.view;


/**
 * Created by WSQ on 2018/1/10 0010.
 */

public interface ILoginView {
    public String mobile();

    public String pwd();

    public void showSuccess(String str);

    public void show(String string);

    public void toRegisterAc();

    void Tosend();

}
