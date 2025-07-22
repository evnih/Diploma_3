package pageobject;

import constant.TestConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ConstructionPage extends BasePage {

    public static final By BUNS_SECTION = By.xpath("//span[text()='Булки']/..");
    public static final By SAUCES_SECTION = By.xpath("//span[text()='Соусы']/..");
    public static final By FILLINGS_SECTION = By.xpath("//span[text()='Начинки']/..");
    public static final By ACTIVE_SECTION = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public ConstructionPage(WebDriver driver) {

        super(driver);
    }
    public void selectSection(String sectionName) {
    By sectionLocator;
        switch (sectionName) {
        case TestConstants.BUNS_SECTION:
            sectionLocator = BUNS_SECTION;
            break;
        case TestConstants.SAUCES_SECTION:
            sectionLocator = SAUCES_SECTION;
            break;
        case TestConstants.FILLINGS_SECTION:
            sectionLocator = FILLINGS_SECTION;
            break;
        default:
            throw new IllegalArgumentException("Unknown section: " + sectionName);
    }
    click(sectionLocator);
}

    public String getActiveSectionName() {
        return findElement(ACTIVE_SECTION).getText();
    }
    public boolean isBunsSectionActive() {
        WebElement bunsSection = findElement(BUNS_SECTION);
        return bunsSection.getAttribute("class").contains("current");
    }
}