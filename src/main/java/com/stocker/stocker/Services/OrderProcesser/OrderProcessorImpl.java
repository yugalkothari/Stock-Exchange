package com.stocker.stocker.Services.OrderProcesser;

import com.stocker.stocker.Repositories.BuyOrders;
import com.stocker.stocker.Repositories.SellOrders;
import com.stocker.stocker.Repositories.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.stocker.stocker.Repositories.*;
import com.stocker.stocker.Models.*;

import java.io.Serializable;
import java.util.List;


@Service
public class OrderProcessorImpl implements Serializable {

    @Autowired
    BuyOrders buyOrders;

    @Autowired
    SellOrders sellOrders;

    @Autowired
    Transactions transactions;

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void Processor() {

        List<com.stocker.stocker.Models.BuyOrders> UnProcessedBuys= buyOrders.findAllUnProcessedBuys();
        for(int i=0;i<UnProcessedBuys.size();i++){

        }


    }



}
