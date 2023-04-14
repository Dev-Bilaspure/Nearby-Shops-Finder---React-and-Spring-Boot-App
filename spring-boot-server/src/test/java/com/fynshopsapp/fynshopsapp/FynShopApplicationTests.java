package com.fynshopsapp.fynshopsapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fynshopsapp.fynshopsapp.constants.PathConstants;
import com.fynshopsapp.fynshopsapp.entities.Shop;
import com.fynshopsapp.fynshopsapp.repository.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FynShopApplicationTests {
//    ObjectMapper om = new ObjectMapper();
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext context;
//
//    @MockBean
//    private ShopRepository shopRepository;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void addShopTest() throws Exception {
//        // Create a new Shop object
//        Shop shop = new Shop();
//        shop.setName("Flick Pharma");
//        shop.setArea("Banglore");
//        shop.setCategory("Pharma");
//
//        // Convert the Shop object to a JSON string
//        String jsonRequest = new ObjectMapper().writeValueAsString(shop);
//
//        // Configure the mock repository to return the shop object when save() is called
//        when(shopRepository.save(shop)).thenReturn(shop);
//
//        // Perform a POST request to the addShop endpoint with the JSON request body
//        MvcResult result = mockMvc.perform(post(PathConstants.ADD_SHOP).content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//
//        System.out.println(result);
//
//        // Get the response content as a string
//        String resultContent = result.getResponse().getContentAsString();
//
//        // Deserialize the response content to a Shop object
//        Shop addedShop = new ObjectMapper().readValue(resultContent, Shop.class);
//
//        // Check that the addedShop object has the same values as the original shop object
//        assertEquals(shop.getName(), addedShop.getName());
//        assertEquals(shop.getLanguage(), addedShop.getLanguage());
//        assertEquals(shop.getFramework(), addedShop.getFramework());
//
//        // Verify that the response status is 200 OK
//        assertEquals(200, result.getResponse().getStatus());
//    }
//
//    @Test
//    public void getAllShopsTest() throws Exception {
//        // Create a list of Shop objects to return from the mock repository
//        List<Shop> shops = new ArrayList<>();
//
//        Shop shop1 = new Shop();
//        shop1.setFramework("express");
//        shop1.setName("Shop11");
//        shop1.setLanguage("javascript");
//
//        Shop shop2 = new Shop();
//        shop2.setFramework("spring");
//        shop2.setName("Shop12");
//        shop1.setLanguage("java");
//
//        shops.add(shop1);
//        shops.add(shop2);
//
//
//        // Configure the mock repository to return the list of shops
//        when(shopRepository.findAll()).thenReturn(shops);
//
//        // Perform a GET request to the getAllShops endpoint
//        MvcResult result = mockMvc.perform(get(PathConstants.GET_ALL_SHOPS)).andExpect(status().isOk()).andReturn();
//
//        // Get the response content as a string
//        String resultContent = result.getResponse().getContentAsString();
//
//        // Deserialize the response content to a list of Shop objects
//        List<Shop> returnedShops = new ObjectMapper().readValue(resultContent, new TypeReference<List<Shop>>() {
//        });
//
//        // Verify that the returnedShops list has the same contents as the original shops list
//        assertEquals(shops.size(), returnedShops.size());
//        for (int i = 0; i < shops.size(); i++) {
//            assertEquals(shops.get(i).getName(), returnedShops.get(i).getName());
//            assertEquals(shops.get(i).getLanguage(), returnedShops.get(i).getLanguage());
//            assertEquals(shops.get(i).getFramework(), returnedShops.get(i).getFramework());
//        }
//    }
//
//    @Test
//    public void getShopByIDTest() throws Exception {
//        // Create a shop object to add to the repository
//        Shop shop = new Shop();
//        shop.setName("Inventory1");
//        shop.setLanguage("java");
//        shop.setFramework("spring");
//
//        // Configure the mock repository to return the shop object when save() is called
//        when(shopRepository.save(shop)).thenReturn(shop);
//
//        // Save the shop to the repository and get its ID
//        Shop savedShop = shopRepository.save(shop);
//        String shopId = savedShop.getShopId();
//        System.out.println("shopID: " + shopId);
//
//        // Configure the mock repository to return the saved shop when findById() is called with the shop ID
//        when(shopRepository.findById(shopId)).thenReturn(Optional.of(savedShop));
//
//        // Perform a GET request to the getShopByID endpoint with the shop ID as a path variable
//        MvcResult result = mockMvc.perform(get("/api/shops/" + shopId).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//
//        // Get the response content as a string
//        String resultContent = result.getResponse().getContentAsString();
//
//        // Deserialize the response content to a Shop object
//        Shop returnedShop = new ObjectMapper().readValue(resultContent, Shop.class);
//
//        // Verify that the returned shop matches the saved shop
//        assertEquals(savedShop, returnedShop);
//    }
//
//
//    @Test
//    public void getShopByName() throws Exception {
//        // Create a shop object to add to the repository
//        Shop shop = new Shop();
//        shop.setName("Inventory1");
//        shop.setLanguage("java");
//        shop.setFramework("spring");
//
//        List<Shop> shopList = new ArrayList<>();
//        shopList.add(shop);
//
//        // Configure the mock repository to return the shop object when save() is called
//        when(shopRepository.save(shop)).thenReturn(shop);
//
//        // Save the shop to the repository and get its ID
//        Shop savedShop = shopRepository.save(shop);
//        String shopName = savedShop.getName();
//
//        // Configure the mock repository to return the saved shop when findById() is called with the shop ID
//        when(shopRepository.findAllByName(shopName)).thenReturn(shopList);
//
//        // Perform a GET request to the getShopByID endpoint with the shop ID as a path variable
//        MvcResult result = mockMvc.perform(get("/api/shops/name/" + shopName).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//
//        // Get the response content as a string
//        String resultContent = result.getResponse().getContentAsString();
//
//        // Deserialize the response content to a Shop object
//        List<Shop> returnedShops = new ObjectMapper().readValue(resultContent, new TypeReference<List<Shop>>() {
//        });
//
//        // Verify that the returnedShops list has the same contents as the original shops list
//        assertEquals(shopList.size(), returnedShops.size());
//        for (int i = 0; i < shopList.size(); i++) {
//            assertEquals(shopList.get(i).getName(), returnedShops.get(i).getName());
//            assertEquals(shopList.get(i).getLanguage(), returnedShops.get(i).getLanguage());
//            assertEquals(shopList.get(i).getFramework(), returnedShops.get(i).getFramework());
//        }
//    }
//
//
//    @Test
//    public void updateShopTest() throws Exception {
//        // Create a shop object to add to the repository
//        Shop shop = new Shop();
//        shop.setName("Inventory1");
//        shop.setLanguage("java");
//        shop.setFramework("spring");
//
//
//        // Configure the mock repository to return the shop object when save() is called
//        when(shopRepository.save(shop)).thenReturn(shop);
//
//        // Save the shop to the repository and get its ID
//        Shop savedShop = shopRepository.save(shop);
//
//        savedShop.setName("Inventory-New-Named");
//
//        String requestBody = new ObjectMapper().writeValueAsString(savedShop);
//
//        MvcResult result = mockMvc.perform(put("/api/shops/").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody)).andExpect(status().isOk()).andReturn();
//
//        // Get the response content as a string
//        String resultContent = result.getResponse().getContentAsString();
//
//        // Deserialize the response content to a Shop object
//        Shop returnedShop = new ObjectMapper().readValue(resultContent, Shop.class);
//
//        // Verify that the returnedShop object has been updated
//        assertEquals(savedShop.getName(), returnedShop.getName());
//        assertEquals(savedShop.getLanguage(), returnedShop.getLanguage());
//        assertEquals(savedShop.getFramework(), returnedShop.getFramework());
//    }
//
//
//    @Test
//    public void deleteShopByIdTest() throws Exception {
//        // Create a shop object to add to the repository
//        Shop shop = new Shop();
//        shop.setName("Inventory1");
//        shop.setLanguage("java");
//        shop.setFramework("spring");
//
//
//        // Configure the mock repository to return the shop object when save() is called
//        when(shopRepository.save(shop)).thenReturn(shop);
//
//        // Save the shop to the repository and get its ID
//        Shop savedShop = shopRepository.save(shop);
//
//        // Configure the mock repository to return the shop object when findById() is called with the shop ID
//        when(shopRepository.findById(savedShop.getShopId())).thenReturn(Optional.of(shop));
//
//        // Perform a DELETE request to the deleteShopById endpoint with the shop ID as a path variable
//        MvcResult result = mockMvc.perform(delete("/api/shops/" + savedShop.getShopId()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
//    }
}
