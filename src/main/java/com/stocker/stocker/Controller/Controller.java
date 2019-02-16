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
    public void addTransactions(@RequestParam(value = "type",required = true) String type,@RequestParam(value = "price",required = true) Float price,@RequestParam(value = "quantity",required = true) Long quantity,@RequestParam(value = "transactionid",required = true) Long transactionid,@RequestParam(value = "stock",required = true) String stock,@RequestParam(value = "processed",required = true) int processed,@RequestParam(value = "time",required = true) Time time) {


    }

}







