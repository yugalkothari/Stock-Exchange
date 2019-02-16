package com.stocker.stocker.Controller;

import com.stocker.stocker.Models.Transactions;
import com.stocker.stocker.Repositories.BuyOrders;
import com.stocker.stocker.Repositories.SellOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
    public ResponseEntity<List<Transactions>> getAllClassUrl() {
        return new ResponseEntity<List<Transactions>>(transaction.findAll(), HttpStatus.OK);
    }



}







