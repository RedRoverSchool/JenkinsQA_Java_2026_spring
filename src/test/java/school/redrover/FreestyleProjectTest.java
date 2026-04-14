package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class FreestyleProjectTest extends BaseTest {

    private void createNewProject(String projectName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
    }

    @Test
    public void testCreateFreestyleProject() {
        String testProjectName = "test";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(testProjectName);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='app-jenkins-logo']"))).click();

        Assert.assertEquals(getDriver().findElement(
                        By.xpath("//*[@class='jenkins-table__link model-link inside']")).getText(),
                testProjectName);
    }

    @Test
    public void testAddDescriptionToFreestyleProject(){
        createNewProject("FreestyleProjectWithDescription");
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Description");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='description-content']")).getText(),"Description");
    }

    @Test
    public void testDisableFreestyleProject() {
        createNewProject("FreestyleProject");
        getDriver().findElement(By.xpath("//label[@class='jenkins-toggle-switch__label ']")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(
                By.id("enable-project")).getText().contains("This project is currently disabled"));
    }

    @Test
    public void testEnableFreestyleProject() {
        createNewProject("FreestyleProject");
        getDriver().findElement(By.xpath("//label[@class='jenkins-toggle-switch__label ']")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.xpath("//button[@value='Enable']")).click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "FreestyleProject"));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/FreestyleProject/configure']"))).click();

        Assert.assertEquals(getDriver().findElement(
                        By.className("jenkins-toggle-switch__label__checked-title")).getText(),
                "Enabled");
    }

    @Test
    public void testBuildTriggersWarningMessage() {
        createNewProject("FreestyleProject");

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//input[@id='cb14']/ancestor::span")));

        getDriver().findElement(By.xpath("//label[contains(text(), 'Build after other projects are built')]")).click();
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys("FreestyleUnexisted");
        getDriver().findElement(By.xpath("//label[contains(text(), 'Trigger even if the build fails')]")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='error' and contains(text(), 'No such project')]"))).getText(),
                "No such project ‘FreestyleUnexisted’. Did you mean ‘FreestyleProject’?");
    }

    @Test
    public void testBuildNowCheckAlert() {
        createNewProject("FreestyleProject");
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Build Now']/.."))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("notification-bar"))).getText(),
                "Build scheduled");
    }
    @Ignore
    @Test
    public void testBuildAfterOtherProjectsAreBuild() {
        createNewProject("FreestyleProject");

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.app-jenkins-logo"))).click();

        createNewProject("FreestyleProject2");

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                getDriver().findElement(By.xpath("//input[@id='cb14']/ancestor::span")));

        getDriver().findElement(By.xpath("//label[contains(text(), 'Build after other projects are built')]")).click();
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys("FreestyleProject");
        getDriver().findElement(By.xpath("//label[contains(text(), 'Trigger even if the build fails')]")).click();
        getDriver().findElement(By.name("Submit")).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), "FreestyleProject2"));
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Status']/.."))).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']/h2[1]")).getText(),
                "Upstream Projects");
        Assert.assertEquals(getDriver().findElement(By.xpath("//a[contains(@class,'model-link')]")).getText(),
                "FreestyleProject");
    }
}