package constant;

import org.openqa.selenium.By;

public class Locators {
    public static final By EMAIL_INPUT = By.xpath("//input[@name='name' and @type='text']");
    public static final By PASSWORD_INPUT = By.xpath("//input[@name='Пароль' and @type='password']");
    public static final By LOGIN_SUBMIT_BUTTON = By.xpath("//button[contains(text(),'Войти')]");
    public static final By LOGIN_FORM = By.xpath("//form[contains(@class,'Auth_form')]");// Main Page
    public static final By MAIN_PAGE_LOGIN_BUTTON = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    public static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//a[contains(@href,'/account')]");

    public static final By LOGOUT_BUTTON = By.xpath("//button[contains(text(),'Выход')]");

    public static final By LOGIN_LINK_ON_REGISTER = By.xpath("//a[contains(text(),'Войти')]");

    public static final By LOGIN_LINK_ON_RECOVERY = By.xpath("//a[contains(@href,'/login')]");

    public static final By PROFILE_SECTION = By.xpath("//section[contains(@class,'profile')]");

    public static final By ERROR_MESSAGE = By.xpath("//p[contains(@class,'error')]");

    public static final By NAME_INPUT = By.xpath("//fieldset[1]//input");
    public static final By REGISTER_BUTTON = By.xpath("//button[text()='Зарегистрироваться']");


    public static final By BUNS_SECTION = By.xpath("//span[text()='Булки']/..");
    public static final By SAUCES_SECTION = By.xpath("//span[text()='Соусы']/..");
    public static final By FILLINGS_SECTION = By.xpath("//span[text()='Начинки']/..");
    public static final By ACTIVE_SECTION = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
    public static final By CONSTRUCTOR_SECTION = By.xpath("//section[contains(@class, 'BurgerIngredients_ingredients')]");
}

