package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.constants.Constants;
import site.nomoreparties.stellarburgers.pages.MainPage;
import site.nomoreparties.stellarburgers.data.*;
import site.nomoreparties.stellarburgers.pages.*;
import site.nomoreparties.stellarburgers.steps.*;

import static com.codeborne.selenide.Selenide.open;

public class LogInAndLogOutUserTest extends Steps {

    MainPage mainPage;

    User user;

    @Before
    public void setUp() {

        RestAssured.baseURI = Constants.URL;

        Configuration.browser = Constants.BROWSER;
        mainPage = open(Constants.URL, MainPage.class);

        user = registrationNewUser();

    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInByButtonLoginInMainPage() {

        //Открываем страницу авторизации пользователя через кнопку "Войти в аккаунт" на главной
        SelenideElement button = mainPage.getButtonLogin();
        LoginPage loginPage = checkOpeningLoginPageByButton(button, button.getText());

        //Выполняем проверку успешной авторизации пользователя
        checkSuccessLoginUser(loginPage, user);

        //Проверяем, что пользователь действительно залогинен и значит по кнопке "Личный кабинет" происходит переход в профиль
        checkOpeningProfilePage(mainPage);

    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void logInByButtonPersonalAccount() {

        //Открываем страницу авторизации пользователя через кнопку «Личный кабинет»
        SelenideElement button = mainPage.getButtonProfile();
        LoginPage loginPage = checkOpeningLoginPageByButton(button, button.getText());

        //Выполняем проверку успешной авторизации пользователя
        checkSuccessLoginUser(loginPage, user);

        //Проверяем, что пользователь действительно залогинен и значит по кнопке "Личный кабинет" происходит переход в профиль
        checkOpeningProfilePage(mainPage);

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void logInByButtonInRegisterPage() {

        //Открываем страницу регистрации нового пользователя
        RegisterPage registerPage = checkOpeningUserRegistrationPage(mainPage);

        //Открываем страницу авторизации пользователя через ссылку «Войти»
        SelenideElement link = registerPage.getLinkLogin();
        LoginPage loginPage = checkOpeningLoginPageByButton(link, link.getText());

        //Выполняем проверку успешной авторизации пользователя
        checkSuccessLoginUser(loginPage, user);

        //Проверяем, что пользователь действительно залогинен и значит по кнопке "Личный кабинет" происходит переход в профиль
        checkOpeningProfilePage(mainPage);

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void logInByButtonInForgotPasswordPage() {

        //Открываем страницу восстановления пароля
        ForgotPasswordPage forgotPasswordPage = checkOpeningPasswordRecoveryPage(mainPage);

        //Открываем страницу авторизации пользователя через ссылку «Войти»
        SelenideElement link = forgotPasswordPage.getLinkLogin();
        LoginPage loginPage = checkOpeningLoginPageByButton(link, link.getText());

        //Выполняем проверку успешной авторизации пользователя
        checkSuccessLoginUser(loginPage, user);

        //Проверяем, что пользователь действительно залогинен и значит по кнопке "Личный кабинет" происходит переход в профиль
        checkOpeningProfilePage(mainPage);

    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logOutFromProfilePage(){

        //Открываем страницу авторизации пользователя через кнопку «Личный кабинет»
        SelenideElement button = mainPage.getButtonProfile();
        LoginPage loginPage = checkOpeningLoginPageByButton(button, button.getText());

        //Выполняем проверку успешной авторизации пользователя
        checkSuccessLoginUser(loginPage, user);

        //Открываем страницу "Личный кабинет"
        ProfilePage profilePage = checkOpeningProfilePage(mainPage);

        //Выполняем проверку успешного выхода из аккаунта
        checkSuccessLogOutUser(profilePage);

        //Проверяем, что пользователь действительно разлогинен и значит по кнопке "Личный кабинет" происходит переход
        //на страницу авторизации
        checkOpeningLoginPageByButton(button, button.getText());

    }

    @After
    public void cleanUp() {

        if (user != null) {

            deleteUser(user);
            user = null;

        }

    }

}
