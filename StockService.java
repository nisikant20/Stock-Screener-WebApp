package com.stockapp.stockapp.service;

import java.io.IOException;
import java.time.LocalDate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockapp.stockapp.model.Stock;
import com.stockapp.stockapp.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    private static final String NSE_URL = "https://www.nseindia.com/get-quotes/equity?symbol=";

    public Stock fetchStockData(String symbol) {
        try {
            // Connect to NSE website
            Document doc = Jsoup.connect(NSE_URL + symbol)
                    .userAgent("Mozilla/5.0")  // Required to bypass NSE security
                    .referrer("http://www.google.com")
                    .timeout(6000)
                    .get();

            // Extract stock price
            Element priceElement = doc.selectFirst(".ltp"); // NSE uses 'ltp' class for price
            String priceText = priceElement.text();
            double price = Double.parseDouble(priceText.replace(",", ""));

            // Extract volume (Modify based on NSE's structure)
            Element volumeElement = doc.selectFirst(".volume");
            long volume = Long.parseLong(volumeElement.text().replace(",", ""));

            // Store data in database
            Stock stock = new Stock(symbol, price, volume, LocalDate.now());
            stockRepository.save(stock);

            return stock;
        } catch (IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
            return null;
        }
    }
}
