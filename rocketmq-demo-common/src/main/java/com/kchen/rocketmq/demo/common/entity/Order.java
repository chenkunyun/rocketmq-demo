package com.kchen.rocketmq.demo.common.entity;

/**
 * Created by kchen on 2017/7/2.
 */
public class Order {

    // 订单id
    private String orderId;

    // 创建时间
    private Long createDate = System.currentTimeMillis();

    // 买家id
    private String buyerId;

    // 付款价格
    private String price;

    // 折扣
    private String discount = "1.0";

    public Order() {
    }

    public Order(String orderId, String buyerId, String price) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", buyerId='" + buyerId + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }
}
