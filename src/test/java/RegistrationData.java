import lombok.Value;

@Value
public class RegistrationData {
    String login;
    String password;
    String status;

    @Override
    public String toString() {
        return "RegistrationData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}