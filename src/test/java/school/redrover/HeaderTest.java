package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class HeaderTest extends BaseTest {
    public static final String JOB_NAME = "FREESTYLE_NEW";
    public static final String TITLE = "Dashboard - Jenkins";

    private void clickOnLogo() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    @Test
    public void testNavigateBackToDashbord() {
        TestUtils.createJob(getDriver(), getWait10(), JOB_NAME, TestUtils.JobType.FREESTYLE);
        clickOnLogo();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(JOB_NAME)))).click();
        clickOnLogo();

        Assert.assertTrue(getWait10().until(ExpectedConditions.titleIs(TITLE)));
    }
}
