package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static guru.qa.tests.helpers.AuthApi.authCookieKey;
import static io.restassured.RestAssured.given;

public class EditProfileTest extends TestBase {

    @Test
    void editUserProfileTest(){
        String valueId = "3121228",
                valueFirstName = "Eduard",
                valueLastName = "Tibov",
                valueEmail = "rasul@mail.ru",
                valueCompany = "Dom",
                valueCountryId = "66",
                valueStateProvinceId = "0",
                valueCity = "Stavropole",
                valueAddress1 = "Lenina",
                valueAddress2 = "Mira",
                valueZipPostalCode = "562014",
                valuePhoneNumber = "583902",
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
                .post("/customer/addressedit/3121228")
                .then()
                .log().all()
                .assertThat()
                .statusCode(302);

    }
}
