package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import config.TestConfig;
import guru.qa.tests.helpers.AuthApi;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static TestConfig config = ConfigFactory.create(TestConfig.class, System.getProperties());
    String login = config.getLogin();
    String password = config.getPassword();
    AuthApi authApi = new AuthApi();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = config.getBaseUrl();
        RestAssured.baseURI = config.getBaseUrl();
    }
}
