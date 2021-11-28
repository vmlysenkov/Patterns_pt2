import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class WebBankTest {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://0.0.0.0")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    void shouldLoginWithCorrectInfo() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(requestSpec)
                .body(new RegistrationData(data.getLogin(), data.getPassword(), data.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldLoginWithBlockedStatus() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(requestSpec)
                .body(new RegistrationData(data.getLogin(), data.getPassword(), "blocked"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotLoginWithUserAbsent() {
        given()
                .spec(requestSpec)
                .body(new RegistrationData("", "", ""))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(500);
    }

    @Test
    void shouldNotLoginWithInvalidLogin() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(requestSpec)
                .body(new RegistrationData("", data.getPassword(), data.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(500);
    }

    @Test
    void shouldNotLoginWithInvalidPassword() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(requestSpec)
                .body(new RegistrationData(data.getLogin(), "", data.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(500);
    }
}
