package pageobject;



import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class MainPage extends BasePage {
    public static final By MAIN_PAGE_LOGIN_BUTTON = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    public static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//a[contains(@href,'/account')]");

    public static final By CONSTRUCTOR_SECTION = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients')]");




    public MainPage(WebDriver driver) {
        super(driver);
    }


    public boolean isPageLoaded() {
        return super.waitForElement(CONSTRUCTOR_SECTION).isDisplayed();
    }



    public void clickLoginButton() {
        click(MAIN_PAGE_LOGIN_BUTTON);
    }
    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        click(PERSONAL_ACCOUNT_BUTTON);
    }



    public boolean isPersonalAccountButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_ACCOUNT_BUTTON))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}