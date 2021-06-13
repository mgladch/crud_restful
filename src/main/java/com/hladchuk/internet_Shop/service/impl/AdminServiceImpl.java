package com.hladchuk.internet_Shop.service.impl;

import com.hladchuk.internet_Shop.dao.AdminDao;
import com.hladchuk.internet_Shop.model.Administrator;
import com.hladchuk.internet_Shop.service.AdminService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {
    AdminDao adminDao;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Administrator save(@NonNull Administrator object) {
        return adminDao.save(object);
    }

    @Override
    public void removeById(@NonNull Integer id) {
        adminDao.deleteById(id);
    }

    @Override
    public Optional<Administrator> findById(@NonNull Integer id) {
        return adminDao.findById(id);
    }

    @Override
    public @NonNull Collection<Administrator> findAll() {
        return adminDao.findAll();
    }
}
