package api.clients;
import api.endpoints.ApiEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;



import static io.restassured.RestAssured.given;




public class UserApi {

    @Step("Регистрация пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(ApiEndpoints.REGISTER);  // Используем константу
    }

    @Step("API: Удаление пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(ApiEndpoints.USER);
    }





}