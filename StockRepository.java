package com.stockapp.stockapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockapp.stockapp.model.Stock;
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findBySymbolOrderByDateAsc(String symbol);


}
