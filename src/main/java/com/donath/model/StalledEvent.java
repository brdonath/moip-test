package com.donath.model;

import javax.persistence.*;

@Entity
public class StalledEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String paymentId;

    @Column
    private Order.Status status;

    @Column
    private boolean sent = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "StalledEvent{" +
                "id=" + id +
                ", paymentId='" + paymentId + '\'' +
                ", status=" + status +
                ", sent=" + sent +
                '}';
    }
}
