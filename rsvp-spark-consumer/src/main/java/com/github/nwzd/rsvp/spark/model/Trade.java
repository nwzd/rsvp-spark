package com.github.nwzd.rsvp.spark.model;

import lombok.Data;

@Data
public class Trade {
    private String exchange;
    private String base;
    private String quote;
    private String direction;
    private String price;
    private String volume;
    private String timestamp;
    private String priceUsd;
}
