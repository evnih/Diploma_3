import constant.TestConstants;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pageObject.ConstructionPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.qameta.allure.*;


@Epic("Конструктор бургеров")
@Feature("Разделы конструктора")
@DisplayName("Тесты разделов конструктора")
public class ConstructionTest extends BaseTest {
    private ConstructionPage constructionPage;

    @Before
    public void setUp() {
        super.setUp();
        constructionPage = new ConstructionPage(driver);
        driver.get(TestConstants.BASE_URL);
    }
    @Test
    @Story("Выбор раздела булок")
    @DisplayName("Проверка перехода в раздел 'Булки'")
    @Severity(SeverityLevel.CRITICAL)
    public void testBunsSectionSelection() {
        constructionPage.selectSection(TestConstants.BUNS_SECTION);
        assertEquals(TestConstants.BUNS_SECTION, constructionPage.getActiveSectionName());
    }

    @Test
    @Story("Выбор раздела соусов")
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    @Severity(SeverityLevel.CRITICAL)
    public void testSaucesSectionSelection() {
        constructionPage.selectSection(TestConstants.SAUCES_SECTION);
        assertEquals(TestConstants.SAUCES_SECTION, constructionPage.getActiveSectionName());
    }

    @Test
    public void testFillingsSectionSelection() {
        constructionPage.selectSection(TestConstants.FILLINGS_SECTION);
        assertEquals(TestConstants.FILLINGS_SECTION, constructionPage.getActiveSectionName());

        constructionPage.selectSection(TestConstants.BUNS_SECTION);
        assertEquals(TestConstants.BUNS_SECTION, constructionPage.getActiveSectionName());

        assertTrue(constructionPage.isBunsSectionActive());
    }
    }

