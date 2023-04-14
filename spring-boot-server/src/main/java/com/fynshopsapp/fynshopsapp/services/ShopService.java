package com.fynshopsapp.fynshopsapp.services;

import java.util.List;

import com.fynshopsapp.fynshopsapp.entities.Shop;
import com.fynshopsapp.fynshopsapp.requestbodies.ShopFilter;
import org.springframework.http.ResponseEntity;

public interface ShopService {

	public ResponseEntity<List<Shop>> getAllShops();
	public ResponseEntity<Shop> getShopById(String shopId);
	public ResponseEntity<Shop> addShop(Shop shop);
	public ResponseEntity<Shop> updateShop(Shop shop);
	public ResponseEntity<String> deleteShopById(String shopId);
	public ResponseEntity<List<Shop>> getShopByName(String name);
	public ResponseEntity<List<Shop>> getServerByFilter(ShopFilter shopFilter);

}
