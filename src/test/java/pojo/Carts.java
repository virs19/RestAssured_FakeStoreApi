package pojo;

import java.util.Date;
import java.util.List;

public class Carts {

    private int userId;

    private Date date;
    private List<CartProduct> cartProduct;

    // No-args constructor
    public Carts() {
    }

    // All-args constructor
    public Carts(int userId, Date date, List<CartProduct> cartProduct) {

        this.userId = userId;
        this.date = date;
        this.cartProduct = cartProduct;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CartProduct> getProductItem() {
        return cartProduct;
    }

    public void setProductItem(List<CartProduct> cartProduct) {
        this.cartProduct = cartProduct;
    }
}