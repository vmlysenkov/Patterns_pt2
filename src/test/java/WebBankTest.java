import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

class WebBankTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithCorrectInfo() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(DataGenerator.requestSpec)
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
                .spec(DataGenerator.requestSpec)
                .body(new RegistrationData(data.getLogin(), data.getPassword(), "blocked"))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotLoginWithInvalidLogin() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(DataGenerator.requestSpec)
                .body(new RegistrationData("", data.getPassword(), data.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotLoginWithInvalidPassword() {
        RegistrationData data = DataGenerator
                .Registration
                .generateInfo("en-US");
        System.out.println(data);
        given()
                .spec(DataGenerator.requestSpec)
                .body(new RegistrationData(data.getLogin(), "", data.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
