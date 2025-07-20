import api.clients.User;
import api.clients.UserApi;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

@Epic("Регистрация")
@Feature("Функциональность регистрации пользователя")
@DisplayName("Тесты регистрации пользователя")
public class RegistrationTest extends BaseTest {
    private User user;
    private String accessToken;

    @Test
    @Story("Успешная регистрация")
    @DisplayName("Регистрация нового пользователя")
    @Severity(SeverityLevel.BLOCKER)
    public void testSuccessfulUserRegistration() {
        user = new User()
                .setEmail("testuser" + System.currentTimeMillis() + "@example.com")
                .setPassword("password123")
                .setName("Test User");

        Response response = UserApi.createUser(user);
        assertEquals(SC_OK, response.getStatusCode());

        accessToken = response.jsonPath().getString("accessToken");
        assertNotNull("Access token не получен", accessToken);
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistrationWithInvalidPassword() {
        user = new User()
                .setEmail("testuser" + System.currentTimeMillis() + "@example.com")
                .setPassword("12345")
                .setName("Test User");

        Response response = UserApi.createUser(user);
        assertEquals(SC_FORBIDDEN, response.getStatusCode());
        assertEquals("Ожидается ошибка при коротком пароле",
                "Пароль должен быть не менее 6 символов",
                response.jsonPath().getString("message"));
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            Response deleteResponse = UserApi.deleteUser(accessToken);
            assertEquals(SC_ACCEPTED, deleteResponse.getStatusCode());
        }
    }
}