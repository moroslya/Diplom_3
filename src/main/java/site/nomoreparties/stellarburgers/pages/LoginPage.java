package site.nomoreparties.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import site.nomoreparties.stellarburgers.data.User;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    //Заголовок "Вход"
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement titleLogin;

    //Поле ввода "Email"
    @FindBy(how = How.XPATH, using = "//*[@class='input__container' and descendant::label[text()='Email']]//input")
    private SelenideElement inputEmail;
    //Поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = "//*[@class='input__container' and descendant::label[text()='Пароль']]//input")
    private SelenideElement inputPassword;

    //Кнопка "Войти"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement buttonLogin;

    //Ссылка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement linkRegister;

    //Ссылка "Восстановить пароль"
    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement linkPasswordRecovery;

    public SelenideElement getTitleLogin() {
        return titleLogin;
    }

    public RegisterPage clickLinkRegister() {

        linkRegister.click();
        return page(RegisterPage.class);

    }

    public ForgotPasswordPage clickLinkPasswordRecovery() {

        linkPasswordRecovery.click();
        return page(ForgotPasswordPage.class);

    }

    public MainPage clickButtonLogin() {

        buttonLogin.click();
        return page(MainPage.class);

    }

    public void setRegistrationData(User user) {

        inputEmail.setValue(user.getEmail());
        inputPassword.setValue(user.getPassword());

    }

}
