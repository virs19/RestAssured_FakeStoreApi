package pojo;

/**
 * fields:
 * {
 * id:20,
 * email:String,
 * username:String,
 * password:String,
 * name:{
 * firstname:String,
 * lastname:String
 * },
 * address:{
 * city:String,
 * street:String,
 * number:Number,
 * zipcode:String,
 * geolocation:{
 * lat:String,
 * long:String
 * }
 * },
 * phone:String
 * }
 */
public class Users {
    private String username;
    private String email;
    private String password;
    private Name name;
    private Address address;

    private String phonenumber;

    // No-args constructor
    public Users() {
    }

    // All-args constructor
    public Users(String username, String email, String password, Name name, Address address, String phonenumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getphoneNumber() {
        return phonenumber;
    }


    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Name getName() {
        return name;
    }

}
