package com.bwei.wsq.wsqusercenter.eventbus;

/**
 * Created by dell on 2018-01-09  15:07
 */

public class PriceAndCountEvent {
    private int price;
    private int count;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
