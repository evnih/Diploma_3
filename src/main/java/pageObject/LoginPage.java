package pageObject;

import constant.Locators;
import constant.TestConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.EMAIL_INPUT))
                .sendKeys(email);
    }
    @Step("Ввести пароль")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PASSWORD_INPUT))
                .sendKeys(password);
    }

    @Step("Нажать кнопку входа")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.LOGIN_SUBMIT_BUTTON))
                .click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ERROR_MESSAGE))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @Step("Проверить отображение формы логина")
    public boolean isLoginFormDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.LOGIN_FORM))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
