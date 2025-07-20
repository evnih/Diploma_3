package pageObject;

import constant.Locators;
import constant.TestConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ConstructionPage extends BasePage {
    public ConstructionPage(WebDriver driver) {
        super(driver);
    }
    public void selectSection(String sectionName) {
    By sectionLocator;
        switch (sectionName) {
        case TestConstants.BUNS_SECTION:
            sectionLocator = Locators.BUNS_SECTION;
            break;
        case TestConstants.SAUCES_SECTION:
            sectionLocator = Locators.SAUCES_SECTION;
            break;
        case TestConstants.FILLINGS_SECTION:
            sectionLocator = Locators.FILLINGS_SECTION;
            break;
        default:
            throw new IllegalArgumentException("Unknown section: " + sectionName);
    }
    click(sectionLocator);
}

    public String getActiveSectionName() {
        return findElement(Locators.ACTIVE_SECTION).getText();
    }
    public boolean isBunsSectionActive() {
        WebElement bunsSection = findElement(Locators.BUNS_SECTION);
        return bunsSection.getAttribute("class").contains("current");
    }
}