package com.donath.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscriber {

    @Id
    private String id;

    public Subscriber() {
    }

    public Subscriber(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
