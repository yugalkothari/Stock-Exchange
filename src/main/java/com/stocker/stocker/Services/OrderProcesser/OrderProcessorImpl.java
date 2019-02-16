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

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void Processor() {
        System.out.println("Iterating Again");
                List <com.stocker.stocker.Models.BuyOrders> UnProcessedBuys = buyOrders.findAllUnProcessedBuys();
               for (int i=0;i<UnProcessedBuys.size();i++){
                   com.stocker.stocker.Models.SellOrders  UnProcessedSells = sellOrders.findAllUnProcessedSells(UnProcessedBuys.get(i).getStock(), UnProcessedBuys.get(i).getTime(), UnProcessedBuys.get(i).getPrice());
                   if(UnProcessedBuys.get(i).getContinued()==1){
                       UnProcessedSells = sellOrders.findAllUnProcessedSellse(UnProcessedBuys.get(i).getStock(),UnProcessedBuys.get(i).getPrice());
                    }
                   if(UnProcessedSells!=null){
                       if(UnProcessedBuys.get(i).getQuantity()==UnProcessedSells.getQuantity()){
                           com.stocker.stocker.Models.Transactions transactions=new com.stocker.stocker.Models.Transactions();
                           transactions.setSell_transaction_id(UnProcessedSells.getTransaction_id());
                           transactions.setBuy_transaction_id(UnProcessedBuys.get(i).getTransaction_id());
                           transactions.setQuantity(UnProcessedSells.getQuantity());
                           transactions.setPrice(UnProcessedSells.getPrice());
                           transactions.setStock(UnProcessedSells.getStock());
                           transaction.save(transactions);
                           UnProcessedBuys.get(i).setProcessed(1);
                           buyOrders.save(UnProcessedBuys.get(i));
                           UnProcessedSells.setProcessed(1);
                           sellOrders.save(UnProcessedSells);
                       }else if(UnProcessedBuys.get(i).getQuantity() < UnProcessedSells.getQuantity()){
                           com.stocker.stocker.Models.Transactions transactions=new com.stocker.stocker.Models.Transactions();
                           transactions.setSell_transaction_id(UnProcessedSells.getTransaction_id());
                           transactions.setBuy_transaction_id(UnProcessedBuys.get(i).getTransaction_id());
                           transactions.setQuantity(UnProcessedBuys.get(i).getQuantity());
                           transactions.setPrice(UnProcessedSells.getPrice());
                           transactions.setStock(UnProcessedSells.getStock());
                           transaction.save(transactions);
                           UnProcessedBuys.get(i).setProcessed(1);
                           buyOrders.save(UnProcessedBuys.get(i));
                           UnProcessedSells.setQuantity(UnProcessedSells.getQuantity()-UnProcessedBuys.get(i).getQuantity());
                           sellOrders.save(UnProcessedSells);
                       }else{
                           com.stocker.stocker.Models.Transactions transactions=new com.stocker.stocker.Models.Transactions();
                           transactions.setSell_transaction_id(UnProcessedSells.getTransaction_id());
                           transactions.setBuy_transaction_id(UnProcessedBuys.get(i).getTransaction_id());
                           transactions.setQuantity(UnProcessedSells.getQuantity());
                           transactions.setPrice(UnProcessedSells.getPrice());
                           transactions.setStock(UnProcessedSells.getStock());
                           transaction.save(transactions);
                           UnProcessedSells.setProcessed(1);
                           sellOrders.save(UnProcessedSells);
                           UnProcessedBuys.get(i).setQuantity(UnProcessedBuys.get(i).getQuantity()-UnProcessedSells.getQuantity());
                           buyOrders.save(UnProcessedBuys.get(i));
                       }

                   }else{
                      UnProcessedBuys.get(i).setContinued(1);
                      buyOrders.save(UnProcessedBuys.get(i));
                      continue;
                   }

               }






    }



}