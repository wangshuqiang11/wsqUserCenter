package com.bwei.wsq.wsqusercenter.view;

/**
 * Created by WSQ on 2018/1/10 0010.
 */

public interface IRegisterView {
    public String mobile();

    public String pwd();

    public void showSuccess(String str);

    public void showFailure(String str);
}
