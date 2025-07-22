package pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;




public class RegistrationPage extends BasePage {

    public static final By LOGIN_LINK_ON_REGISTER = By.xpath("//a[contains(text(),'Войти')]");
    public static final By NAME_INPUT = By.xpath("//fieldset[1]//input");
    public static final By REGISTER_BUTTON = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    public static final By EMAIL_INPUT = By.xpath("//form//fieldset[2]//input");
    public static final By ERROR_MESSAGE = By.cssSelector(".input__error");
    public static final By PASSWORD_INPUT = By.xpath("//form//fieldset[3]//input");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу авторизации")
    public RegistrationPage open(String url) {
        driver.get(url);
        waitForElement(REGISTER_BUTTON);
        return this;
    }

    public RegistrationPage enterName(String name) {
        findElement(NAME_INPUT).sendKeys(name);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        findElement(EMAIL_INPUT).sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        findElement(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public void clickRegisterButton() {
        click(REGISTER_BUTTON);
    }

    public String getErrorMessage() {
        return waitForElement(ERROR_MESSAGE).getText();
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_LINK_ON_REGISTER))
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
}