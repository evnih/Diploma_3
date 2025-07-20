import api.clients.User;
import api.clients.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    private User user;
    private String accessToken;


    @Before
    public void setUp() {
        user = new User()
                .setEmail("testuser" + System.currentTimeMillis() + "@example.com")
                .setPassword("password123")
                .setName("Test User");


        Response registerResponse = UserApi.createUser(user);
        accessToken = registerResponse.jsonPath().getString("accessToken");
    }


    @Test
    @DisplayName("Обновление данных пользователя с авторизацией")
    public void testUpdateUserProfileWithAuth() {

        User updatedUser = new User()
                .setEmail("updated" + user.getEmail())
                .setName("Updated Name")
                .setPassword("newpassword123");

        Response updateResponse = UserApi.updateUser(updatedUser, accessToken);

        assertEquals("Статус код должен быть 200 OK",
                SC_OK, updateResponse.getStatusCode());

        assertEquals("Имя должно обновиться",
                updatedUser.getName(),
                updateResponse.jsonPath().getString("user.name"));

        assertEquals("Email должен обновиться",
                updatedUser.getEmail(),
                updateResponse.jsonPath().getString("user.email"));
    }


    @Test
    @DisplayName("Получение данных пользователя с авторизацией")
    public void testGetUserProfileWithAuth() {
        Response getResponse = UserApi.getUser(accessToken);

        assertEquals("Статус код должен быть 200 OK",
                SC_OK, getResponse.getStatusCode());

        assertEquals("Email должен соответствовать исходному",
                user.getEmail(),
                getResponse.jsonPath().getString("user.email"));

        assertEquals("Имя должно соответствовать исходному",
                user.getName(),
                getResponse.jsonPath().getString("user.name"));
    }


    @Test
    @DisplayName("Обновление данных пользователя без авторизации")
    public void testUpdateUserProfileWithoutAuth() {
        User updatedUser = new User()
                .setEmail("unauthorized@example.com")
                .setName("Unauthorized");

        Response updateResponse = UserApi.updateUser(updatedUser, "");

        assertEquals("Должна быть ошибка 401 Unauthorized",
                SC_UNAUTHORIZED,
                updateResponse.getStatusCode());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            Response deleteResponse = UserApi.deleteUser(accessToken);
            assertEquals("Пользователь должен удаляться успешно",
                    SC_ACCEPTED,
                    deleteResponse.getStatusCode());
        }
    }
}
