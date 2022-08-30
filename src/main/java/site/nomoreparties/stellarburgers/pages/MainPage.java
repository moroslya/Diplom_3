package site.nomoreparties.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.sql.Timestamp;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.is;

public class MainPage {

    //Заголовок "Соберите бургер"
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement titleAssembleBurger;

    //Активный таб
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab_type_current__2BEPc')]")
    private SelenideElement activeTab;

    //Таб "Булки"
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab__1SPyG') and descendant::*[text()='Булки']]")
    private SelenideElement tabBuns;

    //Таб "Соусы"
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab__1SPyG') and descendant::*[text()='Соусы']]")
    private SelenideElement tabSauces;

    //Таб "Начинки"
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab__1SPyG') and descendant::*[text()='Начинки']]")
    private SelenideElement tabFillings;

    //Меню
    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__menuContainer__Xu3Mo")
    private SelenideElement menuContainer;

    //Заголовок "Булки"
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки' and ancestor::*[contains(@class, 'BurgerIngredients')]]")
    private SelenideElement titleBuns;

    //Заголовок "Соусы"
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы' and ancestor::*[contains(@class, 'BurgerIngredients')]]")
    private SelenideElement titleSauces;

    //Заголовок "Начинки"
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки' and ancestor::*[contains(@class, 'BurgerIngredients')]]")
    private SelenideElement titleFillings;

    //Кнопка "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLogin;

    //Кнопка "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//a[descendant::*[text()='Личный Кабинет']]")
    private SelenideElement buttonProfile;

    public SelenideElement getTitleAssembleBurger() {
        return titleAssembleBurger;
    }

    public SelenideElement getButtonLogin() {
        return buttonLogin;
    }

    public SelenideElement getButtonProfile() {
        return buttonProfile;
    }

    public SelenideElement getActiveTab() {
        return activeTab;
    }

    public SelenideElement getTabBuns() {
        return tabBuns;
    }

    public SelenideElement getTabSauces() {
        return tabSauces;
    }

    public SelenideElement getTabFillings() {
        return tabFillings;
    }

    public SelenideElement getTitleBuns() {
        return titleBuns;
    }

    public SelenideElement getTitleSauces() {
        return titleSauces;
    }

    public SelenideElement getTitleFillings() {
        return titleFillings;
    }

    public SelenideElement getMenuContainer() {
        return menuContainer;
    }

    public LoginPage getLoginPageByButton(SelenideElement button) {

        button.click();
        return page(LoginPage.class);

    }

    public ProfilePage clickButtonProfile() {

        buttonProfile.click();
        return page(ProfilePage.class);

    }

    public void checkSetCurrentTab(String tabName, SelenideElement title, int expectedPositionY) {

        activeTab.shouldHave(exactText(tabName));

        waitUntilChangeElementLocationY(title, expectedPositionY, 4000);
        MatcherAssert.assertThat(title.getLocation().getY(), is(expectedPositionY));

    }

    private void waitUntilChangeElementLocationY(SelenideElement element, int expectedPositionY, int timeout) {

        long beginTimestamp = (new Timestamp(System.currentTimeMillis())).getTime();

        while (true) {

            if (element.getLocation().getY() == expectedPositionY
                    || (new Timestamp(System.currentTimeMillis())).getTime() - beginTimestamp >= timeout) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
            }

        }

    }

}
