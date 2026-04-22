package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FooterRestApiLinkTest extends BaseTest {

    @Test
    public void testCheckingTheRestApiLinkHasHoverEffect() {
        WebElement restApiLink = getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("footer .rest-api")
                ));

        String beforeBackground = (String) ((JavascriptExecutor) getDriver()).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                restApiLink
        );

        Actions actions = new Actions(getDriver());
        actions.moveToElement(restApiLink).perform();

        getWait5().until(driver -> {
            String currentBackground = (String) ((JavascriptExecutor) driver).executeScript(
                    "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                    restApiLink
            );
            if (currentBackground == null) {
                return false;
            }
            return !currentBackground.equals(beforeBackground);
        });

        String afterBackground = (String) ((JavascriptExecutor) getDriver()).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                restApiLink
        );

        Assert.assertEquals(restApiLink.getCssValue("cursor"), "pointer");
        Assert.assertNotEquals(beforeBackground, afterBackground);
    }

    @Test (dependsOnMethods = "testRestApiLinkOpensInSameTab")
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

    @Test (dependsOnMethods = "testRestApiLinkOpensInSameTab")
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
}
