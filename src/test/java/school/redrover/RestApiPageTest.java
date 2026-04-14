package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static java.sql.DriverManager.getDriver;

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

    @Test
    public void testRestApiLinkHoverEffect() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement restApiLink = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//footer//a[contains(text(),'REST API')]"))
        );

        String cursor = restApiLink.getCssValue("cursor");
        Assert.assertEquals(cursor, "pointer", "У ссылки должен быть курсор pointer при наведении");
    }

    @Test
    public void testReturnWithBackButton() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//footer//a[contains(text(),'REST API')]"))
        ).click();

        getDriver().navigate().back();

        boolean isDashboardVisible = getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/view/')]"))
        ).isDisplayed();
        Assert.assertTrue(isDashboardVisible, "Элементы Dashboard не отображаются. Возможно, пользователь разлогинен.");
    }

    @Test
    public void testRestApiLinkHiddenOnConfigureSystemPage() {

        getDriver().findElement(By.xpath("//a[contains(@href,'/manage')]")).click();


        getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'configure')]"))
        ).click();

        getWait10().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]"))
        );

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");


        boolean isRestApiLinkPresent = !getDriver().findElements(
                By.xpath("//footer//a[contains(text(),'REST API')]")
        ).isEmpty();

        Assert.assertFalse(isRestApiLinkPresent,
                "Ссылка 'REST API' не должна отображаться в футере на странице Configure System");
    }
}
