package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.data.*;
import site.nomoreparties.stellarburgers.pages.*;
import site.nomoreparties.stellarburgers.stepsAndConstants.*;

import static com.codeborne.selenide.Selenide.open;

public class TransitionsBetweenPagesTest extends Steps {

    MainPage mainPage;

    User user;

    @Before
    public void setUp() {

        RestAssured.baseURI = Constants.URL;
        Configuration.browser = Constants.BROWSER;
        mainPage = open(Constants.URL, MainPage.class);

        //Регистриемся пользователем
        user = registrationNewUser();

        //Логинимся созданным пользователем
        LoginPage loginPage = mainPage.getLoginPageByButton(mainPage.getButtonLogin());
        loginPage.setRegistrationData(user);
        loginPage.clickButtonLogin();

    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void transitionToProfilePage() {

        checkOpeningProfilePage(mainPage);

    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void transitionToMainPageByButtonConstructor() {

        //Переходим на любую страницу отличную от главной
        ProfilePage profilePage = checkOpeningProfilePage(mainPage);

        //Проверяем переход на главную страницу при клике на кнопку "Конструктор"
        checkOpeningMainPageByButton(profilePage.getButtonConstructor());

    }

    @Test
    @DisplayName("Переход по клику на логотип «Stellar Burgers»")
    public void transitionToMainPageByLogoStellarBurgers() {

        //Переходим на любую страницу отличную от главной
        ProfilePage profilePage = checkOpeningProfilePage(mainPage);

        //Проверяем переход на главную страницу при клике на логотип "Stellar Burgers"
        checkOpeningMainPageByButton(profilePage.getLogoStellarBurgers());

    }

    @After
    public void cleanUp() {

        if (user != null) {

            deleteUser(user);
            user = null;

        }

    }

}
