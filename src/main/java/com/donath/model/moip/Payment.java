package com.donath.model.moip;

public class Payment {

    private String status;
    private String id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
