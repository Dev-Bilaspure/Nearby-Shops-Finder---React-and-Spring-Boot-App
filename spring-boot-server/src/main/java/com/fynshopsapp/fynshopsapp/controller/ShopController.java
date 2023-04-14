package com.fynshopsapp.fynshopsapp.controller;

import java.util.List;

import com.fynshopsapp.fynshopsapp.entities.Shop;
import com.fynshopsapp.fynshopsapp.constants.PathConstants;
import com.fynshopsapp.fynshopsapp.requestbodies.ShopFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fynshopsapp.fynshopsapp.services.ShopService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ShopController {
	
	@Autowired
	private ShopService shopService;


	// to quick check if the shop is up and running or not
	@GetMapping("/check")
	public String checkingShop() {
		return "Shop is running...";
	}


	// This endpoint returns a list of all shops in the system.
	@RequestMapping(method = RequestMethod.GET, value = PathConstants.GET_ALL_SHOPS)
	public ResponseEntity<List<Shop>> getAllShops() {
		return shopService.getAllShops();
	}


	// This endpoint returns a single shop by its ID.
	@RequestMapping(method = RequestMethod.GET, value = PathConstants.GET_SHOP_BY_ID)
	public ResponseEntity<Shop> getShopById(@PathVariable String shopId) {
		return shopService.getShopById((shopId));
	}


	// This endpoint returns a single shop by its name.
	@RequestMapping(method = RequestMethod.GET, value = PathConstants.GET_SHOP_BY_NAME)
	public ResponseEntity<List<Shop>> getShopsByName(@PathVariable String name) {
		return shopService.getShopByName(name);
	}


	// This endpoint adds a new shop to the system.
	@RequestMapping(method = RequestMethod.POST, value = PathConstants.ADD_SHOP)
	public ResponseEntity<Shop> addShop(@RequestBody Shop shop) {
		return shopService.addShop(shop);
	}


	// This endpoint updates an existing shop.
	@RequestMapping(method = RequestMethod.PUT, value = PathConstants.UPDATE_SHOP)
	public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
		return shopService.updateShop(shop);
	}


	// This endpoint deletes a shop by its ID.
	@RequestMapping(method = RequestMethod.DELETE, value = PathConstants.DELETE_SHOP)
	public ResponseEntity<String> deleteShop(@PathVariable String shopId) {
		return shopService.deleteShopById(shopId);
	}

	@RequestMapping(method = RequestMethod.POST, value = PathConstants.GET_SERVER_BY_FILTERS)
	public ResponseEntity<List<Shop>> getServerByFilter(@RequestBody ShopFilter shopFilter) {
		return shopService.getServerByFilter(shopFilter);
	}
}
