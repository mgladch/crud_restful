package com.hladchuk.internet_Shop.dao;

import com.hladchuk.internet_Shop.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsDao extends JpaRepository<Goods, Integer> {
    public Optional<Goods> findByTitle(String title);
}
