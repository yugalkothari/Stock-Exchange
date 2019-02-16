package com.stocker.stocker.Controller;

import com.stocker.stocker.Models.Transactions;
import com.stocker.stocker.Repositories.BuyOrders;
import com.stocker.stocker.Repositories.SellOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;



@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api")

public class Controller implements Serializable {
    @Autowired
    BuyOrders buyOrders;

    @Autowired
    SellOrders sellOrders;

    @Autowired
    com.stocker.stocker.Repositories.Transactions transaction;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        return new ResponseEntity<List<Transactions>>(transaction.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addorder")
    public void addTransactions(@RequestParam(value = "type",required = true) String type,@RequestParam(value = "price",required = true) Float price,@RequestParam(value = "quantity",required = true) Long quantity,@RequestParam(value = "transactionid",required = true) Long transactionid,@RequestParam(value = "stock",required = true) String stock,@RequestParam(value = "time",required = true) Time time) {
     if(type=="buy"){
         com.stocker.stocker.Models.BuyOrders buyOrder = new com.stocker.stocker.Models.BuyOrders();
         buyOrder.setProcessed(0);
         buyOrder.setQuantity(quantity);
         buyOrder.setPrice(price);
         buyOrder.setStock(stock);
         buyOrder.setTime(time);
         buyOrder.setTransaction_id(transactionid);
         buyOrders.save(buyOrder);
     }
     else{
         com.stocker.stocker.Models.SellOrders sellOrder = new com.stocker.stocker.Models.SellOrders();
         sellOrder.setQuantity(quantity);
         sellOrder.setProcessed(0);
         sellOrder.setPrice(price);
         sellOrder.setStock(stock);
         sellOrder.setTime(time);
         sellOrder.setTransaction_id(transactionid);
         sellOrders.save(sellOrder);
        }

    }

}







