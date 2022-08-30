package site.nomoreparties.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {

    //Заголовок "Восстановление пароля"
    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private SelenideElement titlePasswordRecovery;

    //Ссылка "Войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement linkLogin;

    public SelenideElement getTitlePasswordRecovery() {
        return titlePasswordRecovery;
    }

    public SelenideElement getLinkLogin() {
        return linkLogin;
    }

}
