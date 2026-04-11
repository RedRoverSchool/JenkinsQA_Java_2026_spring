package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class RestApiPageTest extends BaseTest {

    @Test
    public void testRestApiLinkIsHiddenOnApiPage() {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        // Прокручиваем до футера
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Кликаем по ссылке "REST API" в футере (используем getWait10())
        getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        ).click();

        // Прокручиваем до футера на открывшейся странице
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Проверяем, что ссылка отсутствует в футере
        boolean isRestApiLinkPresentInFooter = !getDriver().findElements(
                By.xpath("//footer//a[contains(text(),'REST API')]")
        ).isEmpty();

        Assert.assertFalse(isRestApiLinkPresentInFooter,
                "Ссылка 'REST API' не должна отображаться в футере на странице REST API (сама на себя)");
    }

}
