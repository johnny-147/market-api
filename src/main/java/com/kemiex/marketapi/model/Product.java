package com.kemiex.marketapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String description;
    private Type type;
    private BigDecimal price;
    private BigDecimal priceInUSD;
    private BigDecimal priceInEUR;
    private Date createdDate;
    private int itemsInStock;

}