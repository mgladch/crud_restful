package com.hladchuk.internet_Shop.dao;

import com.hladchuk.internet_Shop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer> {

}
