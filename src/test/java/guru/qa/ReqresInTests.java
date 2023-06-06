package guru.qa;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ReqresInTests extends TestBase {

    @Test
    void successfulLoginTest() {

        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        Integer userId = 4;
        String userToken = "QpwL5tke4Pnpja7X4";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(userId))
                .body("token", is(userToken));
    }

    @Test
    void successfulJsonSchemaTest() {

        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/status-response-scheme.json"));
    }

    @Test
    void failedUserRegistrationTest() {

        String requestBody = "{\n" +
                "    \"email\": \"registration@mail.com\"\n" +
                "}";
        String errorMessage = "Missing password";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is(errorMessage));
    }

    @Test
    void successfulGetUserIdTest() {

        Integer userId = 3;

        given()
                .log().uri()
                .log().body()
                .when()
                .get("/user/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(userId));
    }

    @Test
    void successfulUpdateTest() {

        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        String userName = "morpheus";
        String userJob = "zion resident";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .put("/user/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(userName))
                .body("job ", is(userJob));
    }
}
