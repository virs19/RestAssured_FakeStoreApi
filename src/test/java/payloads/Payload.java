package payloads;

import com.github.javafaker.Faker;
import pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Payload {
    private static final Faker faker = new Faker();
    private static final String categories[] = {"electronics", "furniture", "clothing", "books", "beauty"};
    private static Random random = new Random();

    public static Product productPayload() {
        String name = faker.commerce().productName();
        double price = Double.parseDouble(faker.commerce().price());
        String Description = faker.lorem().sentence();
        String imageUrl = "https://i.pravatar.cc/100";
        String category = categories[random.nextInt(categories.length)];
        return new Product(name, price, Description, imageUrl, category);
    }

    public static Carts cartsPayload(int userId) {
        List<CartProduct> products = new ArrayList<>();
        int productId = random.nextInt(100);
        int quantity = random.nextInt(10) + 1;
        CartProduct cartProduct = new CartProduct(productId, quantity);
        products.add(cartProduct);
        Date date = new Date();
        return new Carts(userId, date, products);
    }

    public static Users usersPayload() {
        //Name
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Name name = new Name(firstName, lastName);

        //Address
        String city = faker.address().city();
        String street = faker.address().streetName();
        int number = random.nextInt(100);
        String zipcode = faker.address().zipCode();
        //geolocation
        String latitude = faker.address().latitude();
        String langitude = faker.address().longitude();
        Geolocation geolocation = new Geolocation(langitude, latitude);
        Address address = new Address(city, street, number, zipcode, geolocation);
        String email = faker.internet().emailAddress();
        String userName = faker.name().username();
        String password = faker.internet().password();
        String phoneNumber = faker.phoneNumber().cellPhone();
        Users users = new Users(userName, email, password, name, address, phoneNumber);
        return users;
    }

    public static Login loginPayload() {
        String username = faker.name().username();
        String password = faker.internet().password();
        Login login = new Login(username, password);
        return login;
    }
}
