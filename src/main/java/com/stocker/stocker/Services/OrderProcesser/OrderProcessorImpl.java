package com.stocker.stocker.Services.OrderProcesser;

import com.stocker.stocker.Repositories.BuyOrders;
import com.stocker.stocker.Repositories.SellOrders;
import com.stocker.stocker.Repositories.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.List;


@Service
public class OrderProcessorImpl implements Serializable {

    @Autowired
    BuyOrders buyOrders;

    @Autowired
    SellOrders sellOrders;

    @Autowired
    Transactions transaction;

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void Processor() {
        System.out.println("IN Service ");
        List < com.stocker.stocker.Models.BuyOrders > UnProcessedBuys = buyOrders.findAllUnProcessedBuys();
        for (int i = 0; i < UnProcessedBuys.size(); i++) {
            List < com.stocker.stocker.Models.SellOrders > UnProcessedSells = sellOrders.findAllUnProcessedSells(UnProcessedBuys.get(i).getStock(), UnProcessedBuys.get(i).getTime(), UnProcessedBuys.get(i).getPrice());
            for (int j = 0; j < UnProcessedSells.size(); j++) {

                    System.out.println("sell: " + UnProcessedSells.get(j).getPrice());
                    System.out.println("buy: " + UnProcessedBuys.get(i).getPrice());

                    if (UnProcessedBuys.get(i).getQuantity() == UnProcessedSells.get(j).getQuantity()) {
                        UnProcessedBuys.get(i).setProcessed(1);
                        UnProcessedSells.get(j).setProcessed(1);
                        buyOrders.save(UnProcessedBuys.get(i));
                        sellOrders.save(UnProcessedSells.get(j));
                        com.stocker.stocker.Models.Transactions transactions = new com.stocker.stocker.Models.Transactions();
                        transactions.setBuy_transaction_id(UnProcessedBuys.get(i).getTransaction_id());
                        transactions.setSell_transaction_id(UnProcessedSells.get(j).getTransaction_id());
                        transactions.setPrice(UnProcessedSells.get(j).getPrice());
                        transactions.setStock(UnProcessedBuys.get(i).getStock());
                        transactions.setQuantity(UnProcessedSells.get(j).getQuantity());
                        transaction.save(transactions);

                    } else if ((UnProcessedBuys.get(i).getQuantity() < UnProcessedSells.get(j).getQuantity())) {
                             UnProcessedBuys.get(i).setProcessed(1);
                             buyOrders.save(UnProcessedBuys.get(i));
                             UnProcessedSells.get(j).setQuantity(UnProcessedSells.get(j).getQuantity()-UnProcessedBuys.get(i).getQuantity());
                             sellOrders.save(UnProcessedSells.get(j));
                        com.stocker.stocker.Models.Transactions transactions = new com.stocker.stocker.Models.Transactions();
                        transactions.setBuy_transaction_id(UnProcessedBuys.get(i).getTransaction_id());
                        transactions.setSell_transaction_id(UnProcessedSells.get(j).getTransaction_id());
                        transactions.setPrice(UnProcessedSells.get(j).getPrice());
                        transactions.setStock(UnProcessedBuys.get(i).getStock());
                        transactions.setQuantity(UnProcessedSells.get(j).getQuantity());
                        transaction.save(transactions);
                    } else {
                        UnProcessedSells.get(j).setProcessed(1);
                        sellOrders.save(UnProcessedSells.get(j));
                        UnProcessedBuys.get(i).setQuantity(UnProcessedBuys.get(i).getQuantity()-UnProcessedSells.get(j).getQuantity());
                        buyOrders.save(UnProcessedBuys.get(i));
                        com.stocker.stocker.Models.Transactions transactions = new com.stocker.stocker.Models.Transactions();
                        transactions.setBuy_transaction_id(UnProcessedBuys.get(i).getTransaction_id());
                        transactions.setSell_transaction_id(UnProcessedSells.get(j).getTransaction_id());
                        transactions.setPrice(UnProcessedSells.get(j).getPrice());
                        transactions.setStock(UnProcessedBuys.get(i).getStock());
                        transactions.setQuantity(UnProcessedSells.get(j).getQuantity());
                        transaction.save(transactions);
                    }


            }
        }


    }



}