package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineTest extends BaseTest {

    @Test
    public void testCreatePipeline() {
        final String projectName = "new Pipeline";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-jenkins-logo"))).click();

        WebElement actualProjectName = getDriver().findElement(By.xpath("//span[text()='%s']".formatted(projectName)));
        Assert.assertEquals(actualProjectName.getText(), projectName);
    }

    @Test(dependsOnMethods = "testCreatePipeline")

    public void testDisplayNameInAdvancedSectionChangesProjectNameOnDashboard() {

        String displayName = "Changed Pipeline";


        WebElement pipelineName = getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#job_new\\ Pipeline > td:nth-child(3) > a")));
        pipelineName.click();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebElement advancedButton = getWait10().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(@class, 'advancedButton')])[last()]")));
        advancedButton.click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(displayName);

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.id("jenkins-head-icon"))).click();

        WebElement projectOnDashboard = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[text()='%s']".formatted(displayName))));
        Assert.assertEquals(projectOnDashboard.getText(), displayName,
                "Project should be displayed with Display Name on dashboard");
    }
}
