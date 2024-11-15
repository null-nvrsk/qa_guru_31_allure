package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

public class LabelsTest {

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("nullnvrsk")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание Issue для авторизованного пользователя")
    void StaticLabelsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void dynamicLabelsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание Issue для авторизованного пользователя")
        );
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "nullnvrsk");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");
    }
}
