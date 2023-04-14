package com.fynshopsapp.fynshopsapp.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import com.fynshopsapp.fynshopsapp.entities.Shop;
import com.fynshopsapp.fynshopsapp.controller.ShopController;
import com.fynshopsapp.fynshopsapp.repository.ShopRepository;
import com.fynshopsapp.fynshopsapp.requestbodies.ShopFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopRepository shopRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

	/**
	 * Retrieve a list of all shop objects currently stored in the system.
	 *
	 * @return a list of all Shop objects in the system
	 */
	@Override
	public ResponseEntity<List<Shop>> getAllShops()  {
		try {
			List<Shop> shops = shopRepository.findAll();
			if (!shops.isEmpty()) {
				LOGGER.info("Returning the list of shops with success status code");
				return ResponseEntity.ok(shops); // Return the list of Shop objects with a success status code
			} else {
				LOGGER.info("No shops found, returning failure status code");
				return ResponseEntity.notFound().build(); // Return a failure status code if no shops are found
			}
		} catch (Exception e) {
			// handling exception
			LOGGER.error("An error occurred while retrieving the list of shops: {}", e.getMessage(), e);
			System.out.println("An error occurred while retrieving the list of shops: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return an error status code
		}
	}


	/**
	 * Retrieve a shop object from the system with the specified shop ID.
	 *
	 * @param shopId a String value indicating the ID of the shop to retrieve
	 * @return the Shop object with the specified ID, or null if no such shop exists
	 */
	@Override
	public ResponseEntity<Shop> getShopById(String shopId) {
		try {
			LOGGER.info("Retrieving the shop with ID {}", shopId);
			Shop shop = shopRepository.findById(shopId).orElse(null);
			if (shop != null) {
				LOGGER.info("Returning the shop with ID {} and success status code", shopId);
				return ResponseEntity.ok(shop); // Return the Shop object with a success status code
			} else {
				LOGGER.info("No shop found with ID {}, returning failure status code", shopId);
				return ResponseEntity.notFound().build(); // Return a failure status code if no shop with the specified ID is found
			}
		} catch (Exception e) {
			// handling exception
			LOGGER.error("An error occurred while retrieving the shop with ID {}: {}", shopId, e.getMessage(), e);
			System.out.println("An error occurred while retrieving the shop: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return an error status code
		}
	}


	/**
	 * Add a new shop object to the system.
	 *
	 * @param shop a Shop object containing the data for the shop to add
	 * @return the newly added Shop object
	 */
	@Override
	public ResponseEntity<Shop> addShop(Shop shop) {
		try {
			LOGGER.info("Adding a new shop with name '{}'", shop.getName());
			Shop addedShop = shopRepository.save(shop); // Add the Shop object to the repository and return it
			LOGGER.info("New shop added with ID '{}'", addedShop.getShopId());
			return ResponseEntity.ok(addedShop);
		} catch (Exception e) {
			// handling exception
			LOGGER.error("An error occurred while adding the shop: {}", e.getMessage(), e);
			System.out.println("An error occurred while adding the shop: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



	/**
	 * Update an existing shop in the system with the given shop object.

	 * @param shop the updated Shop object to be saved
	 * @return the updated Shop object that has been saved
	 */
	@Override
	public ResponseEntity<Shop> updateShop(Shop shop) {
		Shop existingShop = null; // Declare a variable to store the existing shop object
		try {
			// Try to find the existing shop object in the repository by its ID
			LOGGER.info("Updating shop with ID '{}'", shop.getShopId());
			existingShop = shopRepository.findById(shop.getShopId()).orElse(null);
			if (existingShop != null) { // If the existing shop is found, update its properties
				existingShop.setName(shop.getName());
				existingShop.setArea(shop.getArea());
				existingShop.setOpen(shop.getOpen());
				existingShop.setCategory(shop.getCategory());
				shopRepository.save(existingShop); // Save the updated shop object to the repository
				LOGGER.info("Shop with ID '{}' updated", shop.getShopId());
				return ResponseEntity.ok(existingShop);
			} else {
				LOGGER.warn("Shop with ID '{}' not found", shop.getShopId());
				return ResponseEntity.notFound().build();
			}
		} catch(Exception e) { // If an error occurs, catch it and log a message
			// handle exception
			LOGGER.error("An error occurred while updating the shop with ID '{}': {}", shop.getShopId(), e.getMessage(), e);
			System.out.println("An error occurred while updating the shop: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}



	/**
	 * Delete the shop object with the specified ID from the system.
	 *
	 * @param shopId a String value indicating the ID of the shop to delete
	 * @return a String message indicating the status of the operation
	 */
	@Override
	public ResponseEntity<String> deleteShopById(String shopId) {
		try {
			Optional<Shop> existingShop = shopRepository.findById(shopId); // Retrieve the Shop object with the specified ID from the repository
			if (existingShop.isPresent()) {
				shopRepository.delete(existingShop.get()); // Delete the Shop object from the repository
				LOGGER.info("Shop with ID {} has been deleted", shopId);
				return ResponseEntity.ok("Shop with ID " + shopId + " has been deleted"); // Return a success message
			} else {
				LOGGER.warn("Shop with ID {} not found", shopId);
				return ResponseEntity.notFound().build(); // Return a failure message
			}
		} catch (Exception e) {
			// handling exception
			LOGGER.error("An error occurred while deleting the shop with ID {}", shopId, e);
			System.out.println("An error occurred while deleting the shop: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * Retrieves all the shops with the given name from the data source.
	 *
	 * @param name The name of the shop to retrieve.
	 * @return The List of shops with the given name, or null if no such shop exists.
	 */
	@Override
	public ResponseEntity<List<Shop>> getShopByName(String name) {
		try {
			// Retrieve all Shop objects with the specified name from the repository
			LOGGER.info("Retrieving shops with name: {}", name);
			List<Shop> shops = shopRepository.findAllByName(name);
			if (!shops.isEmpty()) {
				LOGGER.info("Shops with name {} retrieved successfully", name);
				return ResponseEntity.ok(shops);
			} else {
				LOGGER.info("No shops found with name: {}", name);
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			// Handle any exceptions
			LOGGER.error("An error occurred while retrieving the shop: {}", e.getMessage());
			System.err.println("An error occurred while retrieving the shop: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public ResponseEntity<List<Shop>> getServerByFilter(ShopFilter shopFilter) {
		LocalDate currentDate = LocalDate.now();
		DayOfWeek weekDay = currentDate.getDayOfWeek();
		try {
			List<Shop> shops = shopRepository.findAll();
			List<Shop> filteredShops = new ArrayList<>();

			for(Shop shop:shops) {
				if((shopFilter.getCategoryFilter()=="" ? true : shopFilter.getCategoryFilter().equals(shop.getCategory())) &&
						(shopFilter.getAreaFilter()=="" ? true : shopFilter.getAreaFilter().equals(shop.getArea())) &&
						(shopFilter.getOpenCloseFilter()=="" ? true : (shopFilter.getOpenCloseFilter()=="open" ? shop.getOpen().contains(weekDay.getValue()%7) : !shop.getOpen().contains(weekDay.getValue()%7))))
					filteredShops.add(shop);
			}
			if (!shops.isEmpty()) {
				LOGGER.info("Shops retrieved successfully");
				return ResponseEntity.ok(filteredShops);
			} else {
				LOGGER.info("No shops found with this set of filters");
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			// Handle any exceptions
			LOGGER.error("An error occurred while retrieving the shop: {}", e.getMessage());
			System.err.println("An error occurred while retrieving the shop: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
