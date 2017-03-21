package com.donath.model;

import javax.persistence.*;

/**
 * Created by cin_bdonath on 21/03/2017.
 */
@Entity
public class Order {

    @Id
    private String id;

    @Column
    private Order.Status status;

    @Column
    private Integer finalPrice;

    @Transient
    private Product product;

    @Transient
    private String ccHash;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCcHash() {
        return ccHash;
    }

    public void setCcHash(String ccHash) {
        this.ccHash = ccHash;
    }

    public enum Status {
        PROCESSING,
        DENIED,
        OK
    }
}
