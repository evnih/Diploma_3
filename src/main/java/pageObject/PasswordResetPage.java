package pageObject;

import constant.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PasswordResetPage extends BasePage{

        // Локаторы элементов
        private final By emailInput = By.xpath("//input[@type='email']");
        private final By resetButton = By.xpath("//button[text()='Восстановить']");
        private final By loginLink = By.xpath("//a[text()='Войти']");
        private final By errorMessage = By.cssSelector(".input__error");
        private final By successMessage = By.xpath("//p[contains(@class, 'message')]");

        public PasswordResetPage(WebDriver driver) {
            super(driver);
        }

        public void enterEmail(String email) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                    .sendKeys(email);
        }

        public void clickResetButton() {
            wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
        }

    public void clickLoginLink() {
        click(Locators.LOGIN_LINK_ON_RECOVERY);
    }

        public String getErrorMessage() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                    .getText();
        }

        public String getSuccessMessage() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage))
                    .getText();
        }

        public void requestPasswordReset(String email) {
            enterEmail(email);
            clickResetButton();
        }

    }