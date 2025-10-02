package routes;

public class Routes {
    public static final String BASE_URL = "https://fakestoreapi.com";

    /**
     * Product Module Endpoints
     **/
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCTS_WITH_LIMIT = "/products?limit={limit}";
    public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
    public static final String GET_ALL_CATEGORIES = "/products/categories";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{category}";
    public static final String CREATE_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = "/products/{id}";
    public static final String DELETE_PRODUCT = "/products/{id}";

    /**
     * Cart Module Endpoints
     **/
    public static final String GET_ALL_CARTITEMS = "/carts";
    public static final String GET_CARTITEMS_BY_CART_ID = "/carts/{id}";
    public static final String GET_CART_WITH_LIMIT = "/carts?limit={limit}";
    public static final String GET_CART_SORTED = "/carts?sort={order}";
    public static final String GET_CART_DATE_RANGE = "/carts?startDate={startdate}&endDate={enddate}";
    public static final String GET_CART_BY_USERID = "/carts/user/{userId}";
    public static final String CREATE_CART = "/carts";
    public static final String UPDATE_CART = "/carts/{id}";
    public static final String PARTIAL_UPDATE_CART = "/carts/{id}";
    public static final String DELETE_CART = "/carts/{id}";

    /**
     * User Module Endpoints
     **/

    public static final String GET_USER_BY_ID = "/users/{id}";
    public static final String GET_ALL_USERS = "/users";
    public static final String GET_USERS_WITH_LIMIT = "/users?limit={limit}";
    public static final String GET_USERS_SORTED = "/users?sort={order}";
    public static final String CREATE_USER = "/users";
    public static final String UPDATE_USER = "/users/{id}";
    public static final String PARTIAL_UPDATE_USERS = "/users/{id}";
    public static final String DELETE_USER = "/users/{id}";

    /**
     * Login
     **/
    public static final String CREATE_AUTH_TOKEN = "/auth/login";

}
