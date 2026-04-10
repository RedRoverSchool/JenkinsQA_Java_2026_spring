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
    public void testDisableFreestyleProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("FreestyleProject");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//label[@class='jenkins-toggle-switch__label ']")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(
                By.id("enable-project")).getText().contains("This project is currently disabled"));
    }

    @Test
    public void testEnableFreestyleProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("FreestyleProject");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
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
    public void testBuildAfterOtherProject() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("FreestyleProject");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(By.xpath("//input[@id='cb14']/ancestor::span")));

        getDriver().findElement(By.xpath("//label[contains(text(), 'Build after other projects are built')]")).click();

        WebElement inputField= getDriver().findElement(By.name("_.upstreamProjects"));
        inputField.sendKeys("FreestyleUnexisted");
        inputField.click();

        getDriver().findElement(By.xpath("//label[contains(text(), 'Trigger only if build is stable')]")).click();
        WebElement messageError = getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='error' and contains(text(), 'No such project')]")));
        Assert.assertEquals(messageError.getText(),
                "No such project ‘FreestyleUnexisted’. Did you mean ‘FreestyleProject’?");
    }
}