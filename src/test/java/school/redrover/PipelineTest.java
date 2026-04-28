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

        getWait10().until(ExpectedConditions.urlContains("/configure"));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-jenkins-logo"))).click();

        WebElement actualProjectName = getDriver().findElement(By.xpath("//span[text()='%s']".formatted(projectName)));
        Assert.assertEquals(actualProjectName.getText(), projectName);
    }

    @Test
    public void testReplacesOriginalName() {
        String projectName = "Pipeline_" + System.currentTimeMillis();
        String displayName = "Changed Pipeline";

        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")))
                .sendKeys(projectName);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Pipeline']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));
        System.out.println("Project created: " + projectName);

        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();
        getWait10().until(ExpectedConditions.urlContains("localhost"));

        getDriver().navigate().refresh();
        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jenkins-table__link")));

        WebElement projectLink = getWait10().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@href, '%s')]".formatted(projectName))));
        projectLink.click();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement advancedButton = getWait10().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(@class, 'advancedButton')])[last()]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", advancedButton);
        advancedButton.click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(displayName);

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getWait5().until(ExpectedConditions.urlContains("/job/" + projectName));

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();
        getDriver().navigate().refresh();

        WebElement projectOnDashboard = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[text()='%s']".formatted(displayName))));
        Assert.assertEquals(projectOnDashboard.getText(), displayName,
                "Project should be displayed with Display Name on dashboard");
    }

    @Test
    public void testDisableProject() {
        String projectName = "Pipeline_" + System.currentTimeMillis();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(projectName);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Pipeline']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));

        WebElement disableControl = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Disable')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", disableControl);
        try {
            disableControl.click();
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", disableControl);
        }

        getWait10().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait10().until(ExpectedConditions.urlContains("/job/" + projectName + "/"));

        WebElement warningMessage = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(), 'This project is currently disabled') or contains(normalize-space(), 'This project is disabled')]")));
        Assert.assertTrue(warningMessage.isDisplayed(), "Disabled warning message should be displayed");

        WebElement enableButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Enable' or contains(normalize-space(), 'Enable')]")));
        Assert.assertTrue(enableButton.isDisplayed(), "Enable button should be displayed");
    }
}
