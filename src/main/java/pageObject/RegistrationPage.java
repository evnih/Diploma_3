package pageObject;

import constant.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открыть страницу авторизации")
    public RegistrationPage open(String url) {
        driver.get(url);
        waitForElement(Locators.REGISTER_BUTTON);
        return this;
    }

    public RegistrationPage enterName(String name) {
        findElement(Locators.NAME_INPUT).sendKeys(name);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        findElement(Locators.EMAIL_INPUT).sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        findElement(Locators.PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public void clickRegisterButton() {
        click(Locators.REGISTER_BUTTON);
    }

    public String getErrorMessage() {
        return waitForElement(Locators.ERROR_MESSAGE).getText();
    }
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(Locators.LOGIN_LINK_ON_REGISTER))
                .click();
    }
}