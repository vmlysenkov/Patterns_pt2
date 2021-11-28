import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

import static org.checkerframework.checker.units.qual.Prefix.one;
import static org.hamcrest.Matchers.array;

@UtilityClass
public class DataGenerator {

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
