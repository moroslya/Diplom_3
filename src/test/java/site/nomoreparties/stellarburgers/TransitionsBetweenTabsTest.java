package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.*;
import site.nomoreparties.stellarburgers.stepsAndConstants.*;

import static com.codeborne.selenide.Selenide.open;

public class TransitionsBetweenTabsTest extends Steps {

    MainPage mainPage;
    int startPositionY;

    @Before
    public void setUp() {

        RestAssured.baseURI = Constants.URL;
        Configuration.browser = Constants.BROWSER;
        mainPage = open(Constants.URL, MainPage.class);

        //Получаем позицию, к которой должен проскроллиться заголовок раздела при переключении по табам
        startPositionY = mainPage.getMenuContainer().getLocation().getY();

    }

    @Test
    @DisplayName("Переходы к разделу «Булки» кликом по названию таба")
    public void transitionToTabBunsByClick() {

        //Переходим на любую таб отличный от «Булки»
        mainPage.getTabSauces().click();

        //Проверяем переход к разделу «Булки»
        checkTransitionByTab(mainPage.getTabBuns(), mainPage.getTitleBuns(), startPositionY);

    }

    @Test
    @DisplayName("Переходы к разделу «Соусы» кликом по названию таба")
    public void transitionToTabSaucesByClick() {

        checkTransitionByTab(mainPage.getTabSauces(), mainPage.getTitleSauces(), startPositionY);

    }

    @Test
    @DisplayName("Переходы к разделу «Начинки» кликом по названию таба")
    public void transitionToTabFillingsByClick() {

        checkTransitionByTab(mainPage.getTabFillings(), mainPage.getTitleFillings(), startPositionY);

    }

    @Test
    @DisplayName("Переходы к разделу «Булки» скроллом меню")
    public void transitionToTabBunsByScroll() {

        //Переходим на любую таб отличный от «Булки»
        mainPage.getTabSauces().click();

        //Проверяем переход к разделу «Булки»
        checkScrollToTitleMenu(mainPage.getTitleBuns(), mainPage.getTabBuns());

    }

    @Test
    @DisplayName("Переходы к разделу «Соусы» скроллом меню")
    public void transitionToTabSaucesByScroll() {

        checkScrollToTitleMenu(mainPage.getTitleSauces(), mainPage.getTabSauces());

    }

    @Test
    @DisplayName("Переходы к разделу «Начинки» скроллом меню")
    public void transitionToTabFillingsByScroll() {

        checkScrollToTitleMenu(mainPage.getTitleFillings(), mainPage.getTabFillings());

    }

}
