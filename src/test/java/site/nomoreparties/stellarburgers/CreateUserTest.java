package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.constants.Constants;
import site.nomoreparties.stellarburgers.data.*;
import site.nomoreparties.stellarburgers.pages.*;
import site.nomoreparties.stellarburgers.steps.*;

import static com.codeborne.selenide.Selenide.open;

public class CreateUserTest extends Steps {

    MainPage mainPage;

    User user;

    @Before
    public void setUp() {

        RestAssured.baseURI = Constants.URL;

        Configuration.browser = Constants.BROWSER;
        mainPage = open(Constants.URL, MainPage.class);

    }

    @Test
    @DisplayName("Регистрация пользователя с корректными данными")
    public void registrationUserWithCorrectData() {

        user = new User(getRandomUserEmail(), "qwerty12345", "Иванов Иван");

        //Открываем страницу регистрации нового пользователя
        RegisterPage registerPage = checkOpeningUserRegistrationPage(mainPage);

        //Выполняем проверку успешной регистрации пользователя
        LoginPage loginPage = checkSuccessRegistrationUser(registerPage, user);

        //Выполняем проверку успешной авторизации созданным пользователем
        checkSuccessLoginUser(loginPage, user);

    }

    @Test
    @DisplayName("Регистрация пользователя с некорректными паролем")
    public void registrationUserWithIncorrectPassword() {

        User incorrectUser = new User(getRandomUserEmail(), "qwert", "Иванов Иван");

        //Открываем страницу регистрации нового пользователя
        RegisterPage registerPage = checkOpeningUserRegistrationPage(mainPage);

        //Выполняем проверку, что при попытке регистрации с некорректым паролем появляется сообщение об ошибке
        checkErrorWhenRegistrationWithIncorrectPassword(registerPage, incorrectUser);

    }

    @After
    public void cleanUp() {

        if (user != null) {

            deleteUser(user);
            user = null;

        }

    }

}
