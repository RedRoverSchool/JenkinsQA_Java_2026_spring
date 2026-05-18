package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.RestApiPage;

public class FooterRestApiLinkTest extends BaseTest {

    @Test
    public void testCheckingTheRestApiLinkHasHoverEffect() {
        WebElement restApiLink = getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("footer .rest-api")));

        String beforeBackground = (String) ((JavascriptExecutor) getDriver()).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                restApiLink);

        Actions actions = new Actions(getDriver());
        actions.moveToElement(restApiLink).perform();

        getWait5().until(driver -> {
            String currentBackground = (String) ((JavascriptExecutor) driver).executeScript(
                    "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('background-color');",
                    restApiLink);
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

    @Test
    public void testRestApiLinkOpensInSameTab() {
        String originalWindow = getDriver().getWindowHandle();

        new HomePage(getDriver())
                .scrollToBottom()
                .clickRestApiLink();

        Assert.assertEquals(getDriver().getWindowHandle(), originalWindow,
                "Focus switched to another window");
    }

    @Test(dependsOnMethods = "testRestApiLinkOpensInSameTab")
    public void testRestApiLinkIsHiddenOnApiPage() {
        RestApiPage restApiPage = new HomePage(getDriver())
                .scrollToBottom()
                .clickRestApiLink();

        // On the REST API page itself, the link should NOT be present in footer
        Assert.assertFalse(restApiPage.isRestApiLinkDisplayedInFooter(),
                "REST API link should not be displayed in footer on the REST API page (self-link)");
    }

    @Test
    public void testRestApiLinkHoverEffect() {
        String cursor = new HomePage(getDriver())
                .scrollToBottom()
                .getRestApiLinkCursor();

        Assert.assertEquals(cursor, "pointer", "Link should have 'pointer' cursor on hover");
    }

    @Test(dependsOnMethods = "testRestApiLinkOpensInSameTab")
    public void testReturnWithBackButton() {
        new HomePage(getDriver())
                .scrollToBottom()
                .clickRestApiLink();

        getDriver().navigate().back();

        boolean dashboardVisible = new HomePage(getDriver()).isDashboardVisible();
        Assert.assertTrue(dashboardVisible, "Dashboard elements are not visible. User might be logged out.");
    }
}
