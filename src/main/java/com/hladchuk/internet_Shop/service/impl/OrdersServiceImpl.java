package com.hladchuk.internet_Shop.service.impl;

import com.hladchuk.internet_Shop.dao.OrdersDao;
import com.hladchuk.internet_Shop.model.Orders;
import com.hladchuk.internet_Shop.service.OrdersService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao;

    @Autowired
    public OrdersServiceImpl(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @Override
    public Orders save(@NonNull Orders object) {
        return ordersDao.save(object);
    }

    @Override
    public void removeById(@NonNull Integer id) {
        ordersDao.deleteById(id);
    }

    @Override
    public Optional<Orders> findById(@NonNull Integer id) {
        return ordersDao.findById(id);
    }

    @Override
    public @NonNull Collection<Orders> findAll() {
        return ordersDao.findAll();
    }
}
