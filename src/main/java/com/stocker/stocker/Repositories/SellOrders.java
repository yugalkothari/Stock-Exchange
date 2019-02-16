package com.stocker.stocker.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;

public interface SellOrders extends JpaRepository<com.stocker.stocker.Models.SellOrders,Long> {

    @Query(value = "SELECT * FROM stocker.sell_orders WHERE sell_orders.stock = :stock AND sell_orders.processed = 0 AND sell_orders.time <= :time and sell_orders.price<=:price order by sell_orders.price asc limit 1;", nativeQuery = true)
    com.stocker.stocker.Models.SellOrders findAllUnProcessedSells(@Param("stock") String stock,@Param("time") Time time,@Param("price") Float price);

    @Query(value = "SELECT * FROM stocker.sell_orders WHERE sell_orders.stock = :stock AND sell_orders.processed = 0 AND sell_orders.price<=:price order by sell_orders.time, sell_orders.price asc limit 1;", nativeQuery = true)
    com.stocker.stocker.Models.SellOrders findAllUnProcessedSellse(@Param("stock") String stock,@Param("price") Float price);


}



