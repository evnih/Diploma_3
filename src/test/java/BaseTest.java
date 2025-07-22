import api.clients.UserApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;



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

}