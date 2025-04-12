package com.stockapp.stockapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private double price;
    private double changePercent;
    private long volume;
    private double marketCap;
    private LocalDate date;

    public Stock() {}

    public Stock(String symbol, double price, long volume, LocalDate date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }

    public Stock(String symbol, double price, double changePercent, long volume, double marketCap, LocalDate date) {
        this.symbol = symbol;
        this.price = price;
        this.changePercent = changePercent;
        this.volume = volume;
        this.marketCap = marketCap;
        this.date = date;
    }

    // Getter for symbol
    public String getSymbol() {
        return symbol;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Getter for changePercent
    public double getChangePercent() {
        return changePercent;
    }

    // Getter for volume
    public long getVolume() {
        return volume;
    }

    // Getter for marketCap
    public double getMarketCap() {
        return marketCap;
    }

    // Getter for date
    public LocalDate getDate() {
        return date;
    }

    // Setter for symbol
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Setter for changePercent
    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    // Setter for volume
    public void setVolume(long volume) {
        this.volume = volume;
    }

    // Setter for marketCap
    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    // Setter for date
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
