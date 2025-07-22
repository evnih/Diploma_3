package pageobject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class PasswordResetPage extends BasePage{

    public static final By LOGIN_LINK_ON_RECOVERY = By.xpath("//a[contains(@href,'/login')]");

        public PasswordResetPage(WebDriver driver) {
            super(driver);
        }



    public void clickLoginLink() {
        click(LOGIN_LINK_ON_RECOVERY);
    }



    }