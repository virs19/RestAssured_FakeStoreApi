package pojo;

public class Geolocation {
    private String lat;
    private String longitude;

    // No-args constructor (useful for JSON libraries like Jackson)
    public Geolocation() {
    }

    // All-args constructor
    public Geolocation(String lat, String longitude) {
        this.lat = lat;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Geolocation{" +
                "lat='" + lat + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

}
