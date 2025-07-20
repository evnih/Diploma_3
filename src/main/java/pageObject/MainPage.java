package pageObject;

import constant.Locators;
import constant.TestConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends BasePage {


    public MainPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открыть главную страницу")
    public MainPage open() {
        driver.get(TestConstants.BASE_URL);
        return this;
    }

    public boolean isPageLoaded() {
        return super.waitForElement(Locators.CONSTRUCTOR_SECTION).isDisplayed();
    }

    public void clickBunsSection() {
        super.click(Locators.BUNS_SECTION);
    }

    public boolean isBunsSectionActive() {
        return super.waitForElement(Locators.BUNS_SECTION)
                .getAttribute("class").contains("current");
    }

    public void clickLoginButton() {
        click(Locators.MAIN_PAGE_LOGIN_BUTTON);
    }
    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        click(Locators.PERSONAL_ACCOUNT_BUTTON);
    }

    public boolean isUserProfileDisplayed() {
        return isElementDisplayed(Locators.PROFILE_SECTION);
    }

    public void clickLogoutButton() {
        click(Locators.LOGOUT_BUTTON);
    }

    public boolean isPersonalAccountButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PERSONAL_ACCOUNT_BUTTON))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}