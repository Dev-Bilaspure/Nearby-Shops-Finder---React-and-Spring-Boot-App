package com.fynshopsapp.fynshopsapp.repository;

import java.util.*;

import com.fynshopsapp.fynshopsapp.entities.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepository extends MongoRepository<Shop, String> {

    public List<Shop> findAllByName(String name);
}
