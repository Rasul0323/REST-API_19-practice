package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static guru.qa.tests.helpers.AuthApi.authCookieKey;
import static io.restassured.RestAssured.given;

public class EditProfileTest extends TestBase {

    @Test
    void editUserProfileTest(){
        String valueId = "3116110",
                valueFirstName = "John",
                valueLastName = "Ivanov",
                valueEmail = "ivanov@test.ru",
                valueCompany = "Test",
                valueCountryId = "66",
                valueStateProvinceId = "0",
                valueCity = "Moscow",
                valueAddress1 = "Lenina",
                valueAddress2 = "RedSquare",
                valueZipPostalCode = "345",
                valuePhoneNumber = "777888",
                valueFaxNumber = "";

        String authCookieValue = authApi.getAuthCookie(login,password);

        given()
                .contentType("application/x-www-form-urlencoded")
                .cookie(authCookieKey,authCookieValue)
                .formParam("Address.Id", valueId)
                .formParam("Address.FirstName", valueFirstName)
                .formParam("Address.LastName", valueLastName)
                .formParam("Address.Email", valueEmail)
                .formParam("Address.Company", valueCompany)
                .formParam("Address.CountryId", valueCountryId)
                .formParam("Address.StateProvinceId", valueStateProvinceId)
                .formParam("Address.City", valueCity)
                .formParam("Address.Address1", valueAddress1)
                .formParam("Address.Address2", valueAddress2)
                .formParam("Address.ZipPostalCode", valueZipPostalCode)
                .formParam("Address.PhoneNumber", valuePhoneNumber)
                .formParam("Address.FaxNumber", valueFaxNumber)
                .when()
                .post("/customer/addressedit/3116110")
                .then()
                .log().all()
                .assertThat()
                .statusCode(302);

    }
}
