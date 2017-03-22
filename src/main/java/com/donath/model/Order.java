package com.donath.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by cin_bdonath on 21/03/2017.
 */
@Entity(name = "PLACE_ORDER")
public class Order {

    @Id
    private String id;

    @Column
    private Order.Status status;

    @Column
    private BigDecimal finalPrice;

    @Column
    private Integer installments;

    @Column
    private String paymentId;

    @ManyToOne
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

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getCcHash() {
        return ccHash;
    }

    public void setCcHash(String ccHash) {
        this.ccHash = ccHash;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public enum Status {
        PROCESSING,
        DENIED,
        OK
    }
}
