import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class DataGenerator {
    static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @UtilityClass
    public static class Registration {
        public static RegistrationData generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationData(faker.name().firstName(),
                    faker.lorem().characters(8, 16),
                    faker.options().option("active", "blocked"));
        }
    }
}
