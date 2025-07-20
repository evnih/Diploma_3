import api.clients.UserApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import api.clients.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestDataGenerator;
import utils.WebDriverFactory;

import java.time.Duration;



public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String accessToken;

    @Step("Инициализация тестового окружения")
    @DisplayName("Настройка перед тестом")
    @Before
    public void setUp() {
        try {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            WebDriverManager.chromedriver().setup();

            if ("yandex".equals(browser)) {
                driver = createYandexDriver();
            } else {
                driver = createChromeDriver();
            }

            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    private WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--start-maximized",
                "--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--ignore-certificate-errors",
                "--disable-gpu",
                "--disable-extensions",
                "--disable-notifications"
        );
        return new ChromeDriver(options);
    }

    private WebDriver createYandexDriver() {
        ChromeOptions options = new ChromeOptions();
        // Общие настройки
        options.addArguments(
                "--start-maximized",
                "--remote-allow-origins=*",
                "--disable-blink-features=AutomationControlled",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--ignore-certificate-errors",
                "--disable-gpu",
                "--disable-extensions",
                "--disable-notifications"
        );

        // Путь к Yandex браузеру
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            options.setBinary(System.getenv("LOCALAPPDATA") + "\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else if (osName.contains("mac")) {
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        } else {
            options.setBinary("/usr/bin/yandex-browser");
        }

        return new ChromeDriver(options);
    }

        protected void safeGet(String url) {
            int attempts = 0;
            while (attempts < 3) {
                try {
                    driver.get(url);
                    wait.until(webDriver ->
                            ((JavascriptExecutor) webDriver)
                                    .executeScript("return document.readyState").equals("complete"));
                    return;
                } catch (TimeoutException e) {
                    attempts++;
                    System.out.println("Attempt " + attempts + " to load page failed");
                    if (attempts == 3) throw e;
                    sleep(1000);
                }
            }
        }
    protected void retryClick(By locator, int maxAttempts) {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                attempts++;
                if (attempts == maxAttempts) throw e;
                sleep(500);
            }
        }
    }
    protected boolean isElementDisplayed(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    @After
    @Step("Очистка тестового окружения")
    @DisplayName("Очистка после теста")
    public void tearDown() {
        try {
            // Удаление тестового пользователя
            if (accessToken != null) {
                UserApi.deleteUser(accessToken);
            }
        } catch (Exception e) {
            System.err.println("Failed to delete test user: " + e.getMessage());
        } finally {
            // Закрытие драйвера
            if (driver != null) {
                driver.quit();
            }
        }
    }
    @Step("Создать тестового пользователя")
    private User createTestUser() {
        User user = new User()
                .setEmail(TestDataGenerator.randomEmail())
                .setPassword(TestDataGenerator.randomPassword(8))
                .setName(TestDataGenerator.randomName());

        Response response = UserApi.createUser(user);
        accessToken = response.jsonPath().getString("accessToken");
        return user.setAccessToken(accessToken);
    }
}