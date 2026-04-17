package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class PipelineProjectTest extends BaseTest {

    public static final String PIPELINE_NAME = "PipelineName";
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By JENKINS_LOGO = By.xpath("//span[@class='jenkins-mobile-hide']");

    @Test
    public void testCreateWithoutNameError() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertTrue(
                getDriver().findElement(By.id("ok-button")).isDisplayed());
    }

    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PIPELINE_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                PIPELINE_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        final String description = "SomeDescription";

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td/a[@href='job/%s/']".formatted(PIPELINE_NAME)))).click();

        getDriver().findElement(By.id("description-link")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText(),
                description);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testRename() {
        final String renamedPipeline = "RenamedPipeline";

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td/a[@href='job/%s/']".formatted(PIPELINE_NAME)))).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/job/%s/confirm-rename']".formatted(PIPELINE_NAME)))).click();

        WebElement inputField = getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        inputField.clear();
        inputField.sendKeys(renamedPipeline);
        getDriver().findElement(By.xpath("//button[@value='Rename']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                renamedPipeline);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testDisplayNameInAdvancedSectionChangesProjectNameOnDashboard() {
        String displayName = "Changed Pipeline";

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='PipelineName']".formatted(PIPELINE_NAME)))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();

        ((JavascriptExecutor) getDriver()).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");

        WebElement advancedButton = getWait10().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(@class, 'advancedButton')])[last()]")));
        advancedButton.click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(displayName);

        getDriver().findElement(SAVE_BUTTON).click();

        getWait10().until(ExpectedConditions.urlContains("/job/" + PIPELINE_NAME.replace(" ", "%20")));

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                JENKINS_LOGO)).click();

        WebElement projectOnDashboard = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[text()='%s']".formatted(displayName))));
        Assert.assertEquals(projectOnDashboard.getText(), displayName,
                "Project should be displayed with Display Name on dashboard");
    }
}
