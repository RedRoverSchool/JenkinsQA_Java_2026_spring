package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineProject1Test extends BaseTest {

    private static final String PROJECT_NAME = "new Pipeline";
    private static final String DISPLAY_NAME = "Changed Pipeline";

    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-jenkins-logo"))).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//span[text()='%s']".formatted(PROJECT_NAME)))
                .getText(), PROJECT_NAME);
    }
    @Test(dependsOnMethods = "testCreate")
    public void testRename() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#job_new\\ Pipeline > td:nth-child(3) > a"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[contains(@class, 'advancedButton')])[last()]"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(DISPLAY_NAME);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();

        Assert.assertEquals(getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='%s']"
                .formatted(DISPLAY_NAME))))
                .getText(), DISPLAY_NAME, "Project should be displayed with Display Name on dashboard");
    }
}
