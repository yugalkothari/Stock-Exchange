package com.stocker.stocker.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface BuyOrders extends JpaRepository<com.stocker.stocker.Models.BuyOrders,Long> {

    @Query(value = "SELECT * FROM stocker.buy_orders where buy_orders.processed=0 order by buy_orders.time asc", nativeQuery = true)
    List<com.stocker.stocker.Models.BuyOrders> findAllUnProcessedBuys();


}



