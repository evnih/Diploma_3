package pageobject;


import constant.TestConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    public static final By EMAIL_INPUT = By.xpath("//input[@name='name' and @type='text']");
    public static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    public static final By LOGIN_SUBMIT_BUTTON = By.xpath("//button[contains(text(),'Войти')]");
    public static final By LOGIN_FORM = By.xpath("//form[contains(@class,'Auth_form')]");
    public static final By ERROR_MESSAGE = By.xpath("//p[contains(@class,'error')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Открыть страницу логина")
    public LoginPage open() {
        openPage(TestConstants.LOGIN_PAGE);
        return this;
    }
    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT))
                .sendKeys(email);
    }
    @Step("Ввести пароль")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT))
                .sendKeys(password);
    }

    @Step("Нажать кнопку входа")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_SUBMIT_BUTTON))
                .click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @Step("Проверить отображение формы логина")
    public boolean isLoginFormDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_FORM))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
