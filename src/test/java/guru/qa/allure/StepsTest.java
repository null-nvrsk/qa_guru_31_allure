package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private final String repository = "eroshenkoam/allure-example";
    private final int issue = 80;

    @Test
    void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + repository, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(repository);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория " + repository, () -> {
            $(linkText(repository)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем Issue с номером " + issue, () -> {
            $(withText("#" + issue)).should(Condition.exist);
        });
    }

    @Test
    void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps webSteps = new WebSteps();
        webSteps.openMainPage();
        webSteps.searchForRepository(repository);
        webSteps.clickOnRepositoryLink(repository);
        webSteps.openIssuesTab();
        webSteps.shouldSeeIssueWithNumber(issue);
    }
}
