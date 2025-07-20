import api.clients.User;

import api.clients.UserApi;

import constant.TestConstants;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PasswordResetPage;
import pageObject.RegistrationPage;
import utils.TestDataGenerator;


import static org.junit.Assert.*;

@Epic("Авторизация")
@Feature("Функциональность входа в систему")
public class LoginTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private PasswordResetPage passwordResetPage;
    private User user;

    @Before
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordResetPage = new PasswordResetPage(driver);
        // Создаем тестового пользователя
        user = new User()
                .setEmail(TestDataGenerator.randomEmail())
                .setPassword(TestDataGenerator.randomPassword(8))
                .setName(TestDataGenerator.randomName());


        Response response = UserApi.createUser(user);
        accessToken = response.jsonPath().getString("accessToken");
    }

    @Test
    @DisplayName("Успешный вход по кнопке 'Войти в аккаунт'")
    @Story("Пользователь может войти через главную страницу")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginViaMainPageButton() {

        driver.get(TestConstants.BASE_URL);


        mainPage.clickLoginButton();

        assertTrue("Форма логина должна быть видна", loginPage.isLoginFormDisplayed());

        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();


        assertTrue("После логина должна отображаться главная страница",
                mainPage.isPageLoaded());
    }

    @Test
    @Story("Вход через личный кабинет")
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginViaPersonalAccountButton() {
        driver.get(TestConstants.BASE_URL);
        mainPage.clickPersonalAccountButton();

        assertTrue("Форма логина должна быть видна", loginPage.isLoginFormDisplayed());

        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Кнопка личного кабинета должна быть видна после входа",
                mainPage.isPersonalAccountButtonVisible());
    }

    @Test
    @Story("Вход через форму регистрации")
    @DisplayName("Вход через кнопку в форме регистрации")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginViaRegistrationForm() {

        driver.get(TestConstants.REGISTER_PAGE);


        registrationPage.clickLoginLink();

        assertTrue("Форма логина должна отображаться", loginPage.isLoginFormDisplayed());

        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Главная страница должна отображаться после входа",
                mainPage.isPageLoaded());
    }

    @Test
    @Story("Вход через форму восстановления пароля")
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginViaPasswordResetForm() {

        driver.get(TestConstants.FORGOT_PASSWORD_PAGE);


        passwordResetPage.clickLoginLink();

        assertTrue("Форма логина должна отображаться", loginPage.isLoginFormDisplayed());

        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLoginButton();

        assertTrue("Главная страница должна отображаться после входа",
                mainPage.isPageLoaded());
    }

    @Test
    @Story("Негативные сценарии")
    @DisplayName("Вход с неверными учетными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidCredentials() {

        driver.get(TestConstants.BASE_URL);
        mainPage.clickLoginButton();

        loginPage.enterEmail("invalid@email.com");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLoginButton();

        assertTrue("Должно отображаться сообщение об ошибке",
                loginPage.isErrorMessageDisplayed());
    }


    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        super.tearDown();
    }
}

