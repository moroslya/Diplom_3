package site.nomoreparties.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    //Кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = "//a[descendant::*[text()='Конструктор']]")
    private SelenideElement buttonConstructor;

    //Логотип "Stellar Burgers"
    @FindBy(how = How.XPATH, using = "//a[ancestor::*[contains(@class, 'header__logo')]]")
    private SelenideElement logoStellarBurgers;

    //Ссылка "Профиль"
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement linkProfile;

    //Кнопка "Выйти"
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement buttonLogOut;

    public SelenideElement getLinkProfile() {
        return linkProfile;
    }

    public SelenideElement getButtonConstructor() {
        return buttonConstructor;
    }

    public SelenideElement getLogoStellarBurgers() {
        return logoStellarBurgers;
    }

    public LoginPage clickButtonLogOut() {

        buttonLogOut.click();
        return page(LoginPage.class);

    }

}
