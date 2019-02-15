package com.stocker.stocker.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellOrders extends JpaRepository<com.stocker.stocker.Models.SellOrders,Long> {

    @Query(value = "SELECT * FROM stocker.sell_orders where sell_orders.stock=:stock and sell_orders.processed=0;", nativeQuery = true)
    List<com.stocker.stocker.Models.SellOrders> findAllUnProcessedSells(@Param("stock") String stock);


}



