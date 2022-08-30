package site.nomoreparties.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import site.nomoreparties.stellarburgers.data.User;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    final static String CLASS_INPUT_ERROR = "input_status_error";

    //Заголовок "Регистрация"
    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private SelenideElement titleRegistration;

    //Поле ввода "Email"
    @FindBy(how = How.XPATH, using = "//*[@class='input__container' and descendant::label[text()='Email']]//input")
    private SelenideElement inputEmail;
    //Поле ввода "Пароль"
    @FindBy(how = How.XPATH, using = "//*[@class='input__container' and descendant::label[text()='Пароль']]//input")
    private SelenideElement inputPassword;
    //Поле ввода "Имя"
    @FindBy(how = How.XPATH, using = "//*[@class='input__container' and descendant::label[text()='Имя']]//input")
    private SelenideElement inputName;

    //Рамочка поля ввода "Пароль"
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'input_type_password') and descendant::*[text()='Пароль']]")
    private SelenideElement passwordContainer;

    //Сообщение об ошибке
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'input__error') and text()='Некорректный пароль']")
    private SelenideElement errorMessage;

    //Кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegister;

    //Ссылка "Войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement linkLogin;

    public SelenideElement getTitleRegistration() {
        return titleRegistration;
    }

    public SelenideElement getLinkLogin() {
        return linkLogin;
    }

    public LoginPage clickButtonRegister() {

        buttonRegister.click();
        return page(LoginPage.class);

    }

    public void setRegistrationData(User user) {

        inputEmail.setValue(user.getEmail());
        inputPassword.setValue(user.getPassword());
        inputName.setValue(user.getName());

    }

    public void checkPasswordError() {

        errorMessage.shouldBe(visible);
        passwordContainer.shouldHave(cssClass(CLASS_INPUT_ERROR));

    }

}
