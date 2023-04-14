package com.fynshopsapp.fynshopsapp.constants;

public class PathConstants {
    // Suppress default constructor for noninstantiability
    private PathConstants() {
        throw new AssertionError("No instances for you!");
    }

    public static final String GET_ALL_SHOPS = "/api/shops";
    public static final String GET_SHOP_BY_ID = "/api/shops/{shopId}";
    public static final String GET_SHOP_BY_NAME = "/api/shops/name/{name}";
    public static final String ADD_SHOP = "/api/shops";
    public static final String UPDATE_SHOP = "/api/shops";
    public static final String DELETE_SHOP = "/api/shops/{shopId}";
    public static final String GET_SERVER_BY_FILTERS = "/api/shops/filters";
}
