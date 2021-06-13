package com.hladchuk.internet_Shop.controller;

import com.hladchuk.internet_Shop.model.Orders;
import com.hladchuk.internet_Shop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api")
public class OrderController {
    OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")
    ResponseEntity<List<Orders>> findAllOrders() {
        try {
            List<Orders> orders = new ArrayList<>(ordersService.findAll());

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/orderById/{id}")
    public ResponseEntity<Orders> findOrderById(@PathVariable int id) {
        Optional<Orders> user = Optional.ofNullable(ordersService.findById(id).get());
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> saveOrder(@RequestBody Orders order) {
        try {
            Orders createdOrder = ordersService
                    .save(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders order) {
        Optional<Orders> orderData = Optional.ofNullable(ordersService.findById(id)).get();
        if (orderData.isPresent()) {
            Orders updatedOrder = orderData.get();
            updatedOrder.setCustomer(order.getCustomer());
            updatedOrder.setGoods(order.getGoods());
            updatedOrder.setOrderDate(order.getOrderDate());
            return new ResponseEntity<>(ordersService.save(updatedOrder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> removeOrder(@PathVariable int id) {
        try {
            Orders order = ordersService.findById(id).get();
            ordersService.removeById(order.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
