package test;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import page.AuthService;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {

    @Test
    public void test04_onlinerFullChain() {
        AuthService authService = new AuthService();
        SoftAssertions softAssertions = new SoftAssertions();

        Map<String, String> credentials = new HashMap<>();
        credentials.put("login", "test@test.com");
        credentials.put("password", "123456");

        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        authService.setHeaders(headers);
        authService.setBody(credentials);

        authService.doLogin();
        softAssertions.assertThat(authService.getStatusCode())
                .as("Login Status").isEqualTo(400);

    }
}