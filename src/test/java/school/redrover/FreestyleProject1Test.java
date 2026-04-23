package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestyleProject1Test extends BaseTest {

    private static final String NAME_PROJECT = "Freestyle";
    private static final String REPOSITORY_URL = "https://github.com";
    private static final String BRANCH_NAME = "*/main";

    private void goToConfigurePage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='job/Freestyle/']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'configure')]"))).click();
    }
    private void gitButton(){
        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);
    }
    private void enterRepositoryURL(){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.url"))).
                sendKeys(REPOSITORY_URL);
    }
    @Test
    public void testCreateProject(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NAME_PROJECT);
        getDriver().findElement(By.cssSelector("li.hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlContains("configure")));
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.id("jenkins-head-icon"))).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//a[contains(@href, 'job/Freestyle')]")).getText(), NAME_PROJECT);
    }
    @Test(dependsOnMethods = "testCreateProject")
    public void testRepositoryURL() {
        goToConfigurePage();
        gitButton();
        enterRepositoryURL();

        Assert.assertEquals(getDriver().findElement(By.name("_.url")).getAttribute("value"), REPOSITORY_URL,
                "The repository URL does not match!");
    }
    @Test(dependsOnMethods = "testRepositoryURL")
    public void testCredentials() {
        goToConfigurePage();
        gitButton();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//select[@name='_.credentialsId']"))).isDisplayed(),
                "The Credentials drop-down list is not displayed");
    }
    @Test(dependsOnMethods = "testCredentials")
    public void testBranchesToBuild() {
        goToConfigurePage();
        gitButton();

        WebElement branchInput = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")));
        branchInput.clear();
        branchInput.sendKeys(BRANCH_NAME);

        Assert.assertEquals(getDriver().findElement(
                                By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]"))
                        .getAttribute("value"), BRANCH_NAME,
                "The branch name does not match the expected one!");
    }
    @Test(dependsOnMethods = "testBranchesToBuild")
    public void testSCMAuthenticationFails(){
        goToConfigurePage();
        gitButton();
        enterRepositoryURL();

        getDriver().findElement(By.id("page-body")).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//input[@name='_.url']/following::div[@class='error'][1]"),
                "Failed to connect to repository"));

        String actualError = getDriver().findElement(
                By.xpath("//input[@name='_.url']/following::div[@class='error'][1]")).getText();

        Assert.assertTrue(actualError.contains("Failed to connect to repository"),
                "Ожидаемый текст ошибки не найден" + actualError);
    }
}
