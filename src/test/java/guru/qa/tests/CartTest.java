package guru.qa.tests;
import guru.qa.tests.models.AddToCartResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.helpers.AuthApi.authCookieKey;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class CartTest extends TestBase{
    String authCookieValue = authApi.getAuthCookie(login,password);
    int countOfItems;
    int quantity = 1;
    String data = "product_attribute_74_5_26=81" +
            "&product_attribute_74_6_27=83" +
            "&product_attribute_74_3_28=86" +
            "&addtocart_74.EnteredQuantity=" + quantity;

    @Test
    void addItemToCartTest() {
        step("Check number of items in cart", () -> {
            String page = given()
                    .cookie(authCookieKey, authCookieValue)
                    .when()
                    .get("/cart")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .asString();

            Document document = Jsoup.parse(page);
            String count = document.select(".cart-qty").text();
            countOfItems = Integer.parseInt(count.replaceAll("[()]", ""));
        });
        AddToCartResponse response = step("Add item to cart", () -> given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(data)
                .when()
                .post("/addproducttocart/details/74/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(AddToCartResponse.class));

        step("Check adding items to cart and quantity", () -> {
            assertThat(response.getSuccess()).isEqualTo("true");
            assertThat(response.getMessage()).isEqualTo("The product has been added to your <a href=\"/cart\">shopping cart</a>");
            assertThat(response.getUpdatetopcartsectionhtml()).isEqualTo("(" + (countOfItems + quantity) + ")");
        });

    }
}
