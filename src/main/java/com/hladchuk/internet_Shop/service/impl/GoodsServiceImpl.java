    package com.hladchuk.internet_Shop.service.impl;

    import com.hladchuk.internet_Shop.dao.GoodsDao;
    import com.hladchuk.internet_Shop.model.Goods;
    import com.hladchuk.internet_Shop.service.GoodsService;
    import lombok.NonNull;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.Collection;
    import java.util.Optional;
    @Service
    public class GoodsServiceImpl implements GoodsService {
        GoodsDao goodsDao;

        @Autowired
        public GoodsServiceImpl(GoodsDao goodsDao) {
            this.goodsDao = goodsDao;
        }

        @Override
        public Goods save(@NonNull Goods object) {
            return goodsDao.save(object);
        }

        @Override
        public void removeById(@NonNull Integer id) {
            goodsDao.deleteById(id);
        }

        @Override
        public Optional<Goods> findById(@NonNull Integer id) {
            return goodsDao.findById(id);
        }

        @Override
        public @NonNull Collection<Goods> findAll() {
            return goodsDao.findAll();
        }
    }
