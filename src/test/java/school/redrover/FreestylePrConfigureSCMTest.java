package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestylePrConfigureSCMTest extends BaseTest {
    private void createFreestyleProjectAndSelectGit() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.cssSelector("li.hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);
    }
    @Test
    public void testRepositoryURL() {
        createFreestyleProjectAndSelectGit();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.url"))).
                sendKeys("https://github.com");

        Assert.assertEquals(getDriver().findElement(By.name("_.url")).
                getAttribute("value"), "https://github.com", "The repository URL does not match!");
    }
    @Test
    public void testCredentials() {
        createFreestyleProjectAndSelectGit();

        WebElement dropDownList = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//select[@name='_.credentialsId']")));

        Assert.assertTrue(dropDownList.isDisplayed(), "The Credentials drop-down list is not displayed");
    }
    @Test
    public void testBranchesToBuild() {
        createFreestyleProjectAndSelectGit();

        WebElement branchInput = getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")));
        branchInput.clear();
        branchInput.sendKeys("*/main");

        Assert.assertEquals(getDriver().findElement
                        (By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")).
                getAttribute("value"), "*/main",
                "The branch name does not match the expected one!");
    }
}
