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
    public void testRestApiLinkHasHoverEffect() {
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
}
