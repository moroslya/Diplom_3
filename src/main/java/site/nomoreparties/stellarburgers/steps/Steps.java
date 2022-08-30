package site.nomoreparties.stellarburgers.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import com.codeborne.selenide.WebDriverRunner;
import site.nomoreparties.stellarburgers.constants.Constants;
import site.nomoreparties.stellarburgers.data.*;
import site.nomoreparties.stellarburgers.pages.*;

import java.sql.Timestamp;

import static com.codeborne.selenide.Selenide.page;
import static io.restassured.RestAssured.given;
import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.CoreMatchers.*;

public class Steps {

    @Step("Проверка перехода на страницу регистрации нового пользователя")
    public RegisterPage checkOpeningUserRegistrationPage(MainPage mainPage) {

        //Открываем страницу "Вход" нажатием на кнопку "Войти в аккаунт" на главной
        LoginPage loginPage = mainPage.getLoginPageByButton(mainPage.getButtonLogin());

        //Проверяем, что открылась нужная страница с заголовком "Вход"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_LOGIN_PAGE, loginPage.getTitleLogin());

        //Открываем страницу регистрации нажатием на ссылку "Зарегистрироваться" на странице "Вход"
        RegisterPage registerPage = loginPage.clickLinkRegister();

        //Проверяем, что открылась нужная страница с заголовком "Регистрация"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_REGISTER_PAGE, registerPage.getTitleRegistration());

        return registerPage;

    }

    @Step("Проверка перехода на страницу авторизации пользователя через кнопку «{buttonName}»")
    public LoginPage checkOpeningLoginPageByButton(SelenideElement button, String buttonName) {

        //Открываем страницу авторизации
        button.click();
        LoginPage loginPage = page(LoginPage.class);

        //Проверяем, что открылась нужная страница с заголовком "Вход"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_LOGIN_PAGE, loginPage.getTitleLogin());

        return loginPage;

    }

    @Step("Проверка перехода на страницу восстановления пароля")
    public ForgotPasswordPage checkOpeningPasswordRecoveryPage(MainPage mainPage) {

        //Открываем страницу "Вход" нажатием на кнопку "Войти в аккаунт" на главной
        LoginPage loginPage = mainPage.getLoginPageByButton(mainPage.getButtonLogin());

        //Проверяем, что открылась нужная страница с заголовком "Вход"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_LOGIN_PAGE, loginPage.getTitleLogin());

        //Открываем страницу регистрации нажатием на ссылку "Восстановить пароль" на странице "Вход"
        ForgotPasswordPage forgotPasswordPage = loginPage.clickLinkPasswordRecovery();

        //Проверяем, что открылась нужная страница с заголовком "Восстановление пароля"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_FORGOT_PASSWORD, forgotPasswordPage.getTitlePasswordRecovery());

        return forgotPasswordPage;

    }

    @Step("Проверка перехода на страницу «Личный кабинет»")
    public ProfilePage checkOpeningProfilePage(MainPage mainPage) {

        //Открываем страницу "Личный кабинет"
        ProfilePage profilePage = mainPage.clickButtonProfile();

        //Проверяем, что открылась страница "Профиль"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_PROFILE, profilePage.getLinkProfile());

        return profilePage;

    }

    @Step("Проверка перехода на главную страницу")
    public void checkOpeningMainPageByButton(SelenideElement button) {

        //Кликаем на кнопку
        button.click();
        MainPage mainPage = page(MainPage.class);

        //Проверяем,что открывается главная страница с заголовком "Соберите бургер"
        checkOpenPageWithTitle(Constants.URL + "/", mainPage.getTitleAssembleBurger());

    }

    @Step("Проверка перехода на таб")
    public void checkTransitionByTab(SelenideElement tab, SelenideElement title, int expectedPositionY) {

        //Кликаем на заголовок таба
        tab.click();
        MainPage mainPage = page(MainPage.class);

        //Проверяем,что совершается переход на выбранный таб
        mainPage.checkSetCurrentTab(tab.getText(), title, expectedPositionY);

    }

    @Step("Проверка скролла к разделу меню")
    public void checkScrollToTitleMenu(SelenideElement title, SelenideElement tab) {

        //Проскролливаем страницу к заголовку раздела
        title.scrollIntoView(true);
        MainPage mainPage = page(MainPage.class);

        //Проверяем,что совершается переход на соответствующий таб
        mainPage.getActiveTab().shouldHave(exactText(tab.getText()));

    }

    @Step("Проверка успешной регистрации пользователя")
    public LoginPage checkSuccessRegistrationUser(RegisterPage registerPage, User user) {

        //Заполняем логин и пароль
        registerPage.setRegistrationData(user);
        //и нажимаем кнопку "Зарегистрироваться"
        LoginPage loginPage = registerPage.clickButtonRegister();

        //Проверяем,что открывается страница авторизации с заголовком "Вход"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_LOGIN_PAGE, loginPage.getTitleLogin());

        return loginPage;

    }

    @Step("Проверка успешной авторизации пользователем")
    public void checkSuccessLoginUser(LoginPage loginPage, User user) {

        //Заполняем логин и пароль
        loginPage.setRegistrationData(user);
        //и нажимаем кнопку "Войти"
        MainPage mainPage = loginPage.clickButtonLogin();

        //Проверяем,что открывается главная страница с заголовком "Соберите бургер"
        checkOpenPageWithTitle(Constants.URL + "/", mainPage.getTitleAssembleBurger());

    }

    @Step("Проверка ошибки при регистрации с некорректным паролем")
    public void checkErrorWhenRegistrationWithIncorrectPassword(RegisterPage registerPage, User user) {

        //Заполняем логин и пароль
        registerPage.setRegistrationData(user);
        //и нажимаем кнопку "Зарегистрироваться"
        LoginPage loginPage = registerPage.clickButtonRegister();

        //Проверяем,что появилась ошибка "Некорректный пароль"
        registerPage.checkPasswordError();
        //и что по прежнему открыта страница регистрации
        MatcherAssert.assertThat(WebDriverRunner.url(), is(Constants.URL + Constants.URL_REGISTER_PAGE));

    }

    @Step("Проверка успешной выхода из аккаунта")
    public void checkSuccessLogOutUser(ProfilePage profilePage) {

        //Нажимаем на кнопку "Выход"
        LoginPage loginPage = profilePage.clickButtonLogOut();

        //Проверяем,что открывается страница авторизации с заголовком "Вход"
        checkOpenPageWithTitle(Constants.URL + Constants.URL_LOGIN_PAGE, loginPage.getTitleLogin());

    }

    public String getRandomUserEmail() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "user2022_" + timestamp.getTime() + "@yandex.ru";

    }

    public void checkOpenPageWithTitle(String pageUrl, SelenideElement title) {

        title.shouldBe(visible);
        MatcherAssert.assertThat(WebDriverRunner.url(), is(pageUrl));

    }

    public User registrationNewUser() {

        User user = new User(getRandomUserEmail(), "qwerty12345", "Иванов Иван");

        Response response = sendRequestCreateUser(user);

        user.setAccessToken(response.jsonPath().get("accessToken"));
        user.setToken(response.jsonPath().get("refreshToken"));

        return user;

    }

    public void deleteUser(User user) {

        if (user.getAccessToken() == null) {

            Response responseLogin = sendRequestLoginUser(user);

            user.setAccessToken(responseLogin.jsonPath().get("accessToken"));
            user.setToken(responseLogin.jsonPath().get("refreshToken"));

        }

        Response responseDelete = sendRequestDeleteUser(user.getAccessToken());

        if (responseDelete.getStatusCode() == 403
                && responseDelete.jsonPath().getString("message").equals("jwt expired")) {

            String newAccessToken = sendRequestRefreshAccessToken(user);
            sendRequestDeleteUser(newAccessToken);

        }

    }

    public Response sendRequestCreateUser(User user) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(Constants.API_REGISTER_USER);

    }

    public Response sendRequestDeleteUser(String accessToken) {

        return given()
                .header("Authorization", accessToken)
                .delete(Constants.API_DELETE_USER);

    }

    public Response sendRequestLoginUser(User user) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(Constants.API_LOGIN_USER);

    }

    public String sendRequestRefreshAccessToken(User user) {

        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .post(Constants.API_REFRESH_TOCKEN)
                .jsonPath().getString("accessToken");

    }

}
