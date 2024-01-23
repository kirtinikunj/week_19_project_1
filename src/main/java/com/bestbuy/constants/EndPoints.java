package com.bestbuy.constants;

public class EndPoints {

    //below end points are for store
    public static final String POST = "/stores";
    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "stores/{storeID}";
    public static final String UPDATE_STORE_BY_ID = "stores/{storeID}";
    public static final String DELETE_STORE_BY_ID = "stores/{storeID}";

    //below end points are for product

    public static final String PRODUCT_POST = "/products";
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{id}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{id}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{id}";

}
