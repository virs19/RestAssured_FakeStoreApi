package testcases;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import routes.Routes;
import utils.ConfigReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BaseClass {
    ConfigReader configReader;
    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;
    public String token;

    @BeforeClass
    public void setup() throws IOException {
        RestAssured.baseURI = Routes.BASE_URL;
        configReader = new ConfigReader();

        // Setup filters for logging
        FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.txt");
        PrintStream log = new PrintStream(fos, true);
        requestLoggingFilter = new RequestLoggingFilter(log);
        responseLoggingFilter = new ResponseLoggingFilter(log);
        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);
    }

    boolean isSortedDescending(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    boolean isSortedAscending(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    //Check dates are within specified range
    public static final DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, Formatter);
        LocalDate end = LocalDate.parse(endDate, Formatter);
        for (String dateTime : cartDates) {
            LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), Formatter);
            if (cartDate.isBefore(start) || cartDate.isAfter(end)) {
                return false;
            }
        }
        return true;
    }
}
