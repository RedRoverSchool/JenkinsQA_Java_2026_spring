package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ManageJenkinsPage3Test extends BaseTest {

    private static final By MANAGE_JENKINS_LINK = By.cssSelector("a[href='/manage']");
    private static final By CONFIGURE_SYSTEM_LINK = By.xpath("//a[contains(@href, 'configure')]");

    @Test
    public void testOpenConfigureSystemPage() {

        WebElement manageJenkinsIcon = getWait10()
                .until(ExpectedConditions.elementToBeClickable(MANAGE_JENKINS_LINK));
        manageJenkinsIcon.click();

        getWait10().until(ExpectedConditions.urlContains("/manage"));


        WebElement configureSystemLink = getWait5()
                .until(ExpectedConditions.elementToBeClickable(CONFIGURE_SYSTEM_LINK));
        configureSystemLink.click();


        getWait2().until(ExpectedConditions.urlContains("/configure"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/configure"),
                "User should be redirected to Configure System page");
    }
}
