package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class RestApiPageTest extends BaseTest {

    @Test
    public void testRestApiLinkIsHiddenOnApiPage() {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        ).click();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        boolean isRestApiLinkPresentInFooter = !getDriver().findElements(
                By.xpath("//footer//a[contains(text(),'REST API')]")
        ).isEmpty();

        Assert.assertFalse(isRestApiLinkPresentInFooter,
                "Ссылка 'REST API' не должна отображаться в футере на странице REST API (сама на себя)");
    }

    @Test
    public void testRestApiLinkOpensInSameTab() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement restApiLink = getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        );

        String originalWindow = getDriver().getWindowHandle();
        restApiLink.click();

        Assert.assertEquals(getDriver().getWindowHandle(), originalWindow,
                "Фокус переключился на другое окно");

    }
}
