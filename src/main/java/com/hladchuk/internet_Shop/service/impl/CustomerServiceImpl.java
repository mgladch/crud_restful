package com.hladchuk.internet_Shop.service.impl;

import com.hladchuk.internet_Shop.dao.CustomerDao;
import com.hladchuk.internet_Shop.model.Customer;
import com.hladchuk.internet_Shop.service.CustomerService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    @Override
    public Customer save(@NonNull Customer object) {
        return customerDao.save(object);
    }

    @Override
    public void removeById(@NonNull Integer id) {
        customerDao.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(@NonNull Integer id) {
        return customerDao.findById(id);
    }

    @Override
    public @NonNull Collection<Customer> findAll() {
        return customerDao.findAll();
    }
}
