package com.hladchuk.internet_Shop.dao;

import com.hladchuk.internet_Shop.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Administrator, Integer> {

}
