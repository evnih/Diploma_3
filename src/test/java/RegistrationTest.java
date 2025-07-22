

import constant.TestConstants;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pageobject.MainPage;
import pageobject.RegistrationPage;
import utils.TestDataGenerator;


import static org.junit.Assert.*;

@Epic("Регистрация")
@Feature("Функциональность регистрации пользователя")
@DisplayName("Тесты регистрации пользователя")
public class RegistrationTest extends BaseTest {
    private RegistrationPage registrationPage;


    @Before
    public void setUp() {
        super.setUp();
        registrationPage = new RegistrationPage(driver);

    }
    @Test
    @Story("Успешная регистрация")
    @DisplayName("Регистрация нового пользователя")
    @Description("Проверка успешной регистрации через UI")
    @Severity(SeverityLevel.BLOCKER)
    public void testSuccessfulUserRegistration() {
        MainPage mainPage = new MainPage(driver);


        String email = TestDataGenerator.randomEmail();
        String password = TestDataGenerator.randomPassword(8);
        String name = TestDataGenerator.randomName();

        registrationPage.open(TestConstants.REGISTER_PAGE)
                .enterName(name)
                .enterEmail(email)
                .enterPassword(password)
                .clickRegisterButton();

        assertTrue("После успешной регистрации должна отображаться главная страница",
                mainPage.isPageLoaded());
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Description("Проверка валидации поля пароля при регистрации")
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistrationWithInvalidPassword() {
        String email = TestDataGenerator.randomEmail();
        String shortPassword = "12345"; // Пароль короче 6 символов

        registrationPage.open(TestConstants.REGISTER_PAGE)
                .enterName(TestDataGenerator.randomName())
                .enterEmail(email)
                .enterPassword(shortPassword)
                .clickRegisterButton();


       assertTrue("Сообщение об ошибке должно быть видимым",
                registrationPage.isErrorMessageDisplayed());

        assertEquals("Некорректный пароль",
                registrationPage.getErrorMessage());
    }

    @After
    public void tearDown() {


        super.tearDown();

    }
}