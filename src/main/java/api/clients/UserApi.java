package api.clients;
import api.endpoints.ApiEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;


import static io.restassured.RestAssured.given;

public class UserApi {

    @Step("Регистрация пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(ApiEndpoints.REGISTER);  // Используем константу
    }
    @Step("Авторизация пользователя")
    public static Response login(UserCredentials credentials) {
        return given()
                .header("Content-type", "application/json")
                .body(credentials)
                .post(ApiEndpoints.LOGIN);  // Используем константу
    }

    // Получение данных пользователя
    public static Response getUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .get(ApiEndpoints.USER);
    }

    // Обновление данных пользователя
    public static Response updateUser(User user, String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .body(user)
                .patch(ApiEndpoints.USER);
    }

    @Step("API: Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(ApiEndpoints.USER);
    }

    // Выход из системы
    public static Response logout(String refreshToken) {
        return given()
                .header("Content-type", "application/json")
                .body(Map.of("token", refreshToken))
                .post(ApiEndpoints.LOGOUT);
    }

    // Полный flow: регистрация -> получение токена
    public static String registerAndGetToken(User user) {
        Response response = createUser(user);
        return response.jsonPath().getString("accessToken");
    }
}