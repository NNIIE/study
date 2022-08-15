package com.example.concurrency.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long productId;

    private long quantity;

    public Stock() {

    }

    public Stock(long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    @Transactional
    public void decrease(long quantity) {
        if (this.quantity - quantity < 0) throw new RuntimeException("재고가 0개 이하입니다.");
        this.quantity = this.quantity - quantity;
    }
}
