package com.stockapp.stockapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.stockapp.model.Stock;
import com.stockapp.stockapp.repository.StockRepository;
import com.stockapp.stockapp.service.StockService;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "*") // Allow frontend requests
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    // Fetch all stocks from MySQL
    @GetMapping("/all")
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/")
    public String home() {
        return "index";  // Renders index.html
    }

    @GetMapping("/stock")
    public String getStockData(@RequestParam String symbol, Model model) {
        Stock stock = stockService.fetchStockData(symbol);
        model.addAttribute("stock", stock);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // This must match login.html in templates/
    }
}
