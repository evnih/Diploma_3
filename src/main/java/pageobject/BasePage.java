package pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Инициализация wait здес
    }
    @Step("Открыть страницу {url}")
    protected void openPage(String url) {
        driver.get(constant.TestConstants.LOGIN_PAGE);
    }
    @Step("Найти элемент: {locator}")
    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    @Step("Кликнуть по элементу: {locator}")
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}