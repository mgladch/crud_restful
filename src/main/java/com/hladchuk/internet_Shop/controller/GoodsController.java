package com.hladchuk.internet_Shop.controller;

import com.hladchuk.internet_Shop.model.Goods;
import com.hladchuk.internet_Shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api")
public class GoodsController {
    GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/goods")
    ResponseEntity<List<Goods>> findAllGoods() {
        try {
            List<Goods> goods = new ArrayList<>(goodsService.findAll());

            if (goods.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(goods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/goodById/{id}")
    public ResponseEntity<Goods> findGoodsById(@PathVariable("id") int id) {
        Optional<Goods> goods = Optional.ofNullable(goodsService.findById(id).get());
        return goods.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/goodByTitle/{title}")
    public ResponseEntity<List<Goods>> findGoodsByTitle(@PathVariable("title") String title) {
        try {
            List<Goods> goodsWithTitle = goodsService.findAll().stream()
                    .filter(goods -> goods.getTitle().equals(title))
                    .collect(Collectors.toList());
            if (goodsWithTitle.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(goodsWithTitle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/goods")
    public ResponseEntity<Goods> saveGoods(@RequestBody Goods goods) {
        try {
            Goods createdGoods = goodsService
                    .save(goods);
            return new ResponseEntity<>(createdGoods, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/goods/{id}")
    public ResponseEntity<Goods> updateGoods(@PathVariable("id") int id, @RequestBody Goods goods) {
        Optional<Goods> goodsData = Optional.ofNullable(goodsService.findById(id)).get();

        if (goodsData.isPresent()) {
            Goods updatedGoods = goodsData.get();
            updatedGoods.setTitle(goods.getTitle());
            updatedGoods.setType(goods.getType());
            updatedGoods.setDescription(goods.getDescription());
            updatedGoods.setAmount(goods.getAmount());
            updatedGoods.setPrice(goods.getPrice());
            return new ResponseEntity<>(goodsService.save(updatedGoods), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/goods/{id}")
    public ResponseEntity<HttpStatus> removeGoods(@PathVariable("id") int id) {
        try {
            Goods goods = goodsService.findById(id).get();
            goodsService.removeById(goods.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
