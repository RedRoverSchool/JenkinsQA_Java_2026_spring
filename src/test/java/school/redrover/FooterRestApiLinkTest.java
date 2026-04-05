package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;


public class FooterRestApiLinkTest extends BaseTest {
    @Test
    public void testRestApiLinkHasHoverEffect() {
        WebElement restApiLink = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("footer .rest-api")
                ));

        String beforeBackground = (String) ((JavascriptExecutor) getDriver()).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                restApiLink
        );

        Actions actions = new Actions(getDriver());
        actions.moveToElement(restApiLink).perform();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String afterBackground = (String) ((JavascriptExecutor) getDriver()).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                restApiLink
        );

        Assert.assertEquals(restApiLink.getCssValue("cursor"), "pointer");
        Assert.assertNotEquals(beforeBackground, afterBackground);
    }
}
