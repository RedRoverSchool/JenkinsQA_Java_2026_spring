package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestylePrConfigureSCMTest extends BaseTest {
    public static final String NAME_PROJECT = "Freestyle";
    public static final String REPOSITORY_URL = "https://github.com";
    public static final String BRANCH_NAME = "*/main";

    private final By newItem = By.xpath("//a[@href='/view/all/newJob']");
    private final By itemName = By.id("name");
    private final By itemType = By.cssSelector("li.hudson_model_FreeStyleProject");
    private final By okButton = By.id("ok-button");
    private final By saveButton = By.name("Submit");
    private final By mainPage = By.cssSelector("#jenkins-home-link, #jenkins-head-icon");
    private final By nameProject = By.xpath("//a[contains(@href, 'job/Freestyle')]");
    private final By freestyleButton = By.xpath("//a[@href='job/Freestyle/']");
    private final By configureButton = By.xpath("//a[contains(@href, 'configure')]");
    private final By gitButton = By.xpath("//label[text()='Git']");
    private final By repositoryURLArea = By.name("_.url");
    private final By credentials = By.xpath("//select[@name='_.credentialsId']");
    private final By branchesToBuild = By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]");
    private final By branchSpecifier = By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]");
    private final By pageBody = By.id("page-body");
    private final By error = By.xpath("//input[@name='_.url']/following::div[@class='error'][1]");


    private void goToCongigurePage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(freestyleButton)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(configureButton)).click();
    }
    private void gitButton(){
        WebElement gitOption = getDriver().findElement(gitButton);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);
    }
    private void enterRepositoryURL(){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(repositoryURLArea)).
                sendKeys(REPOSITORY_URL);
    }
    @Test
    public void testCreateProject(){
        getDriver().findElement(newItem).click();
        getDriver().findElement(itemName).sendKeys(NAME_PROJECT);
        getDriver().findElement(itemType).click();
        getDriver().findElement(okButton).click();
        getDriver().findElement(saveButton).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(mainPage)).click();

        Assert.assertEquals(getDriver().findElement(nameProject).getText(), NAME_PROJECT);
    }
    @Test(dependsOnMethods = "testCreateProject")
    public void testRepositoryURL() {
        goToCongigurePage();
        gitButton();
        enterRepositoryURL();

        Assert.assertEquals(getDriver().findElement(repositoryURLArea).getAttribute("value"), REPOSITORY_URL,
                "The repository URL does not match!");
    }
    @Test(dependsOnMethods = "testRepositoryURL")
    public void testCredentials() {
        goToCongigurePage();
        gitButton();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(credentials)).isDisplayed(),
                "The Credentials drop-down list is not displayed");
    }
    @Test(dependsOnMethods = "testCredentials")
    public void testBranchesToBuild() {
        goToCongigurePage();
        gitButton();

        WebElement branchInput = getWait5().until(ExpectedConditions.visibilityOfElementLocated(branchesToBuild));
        branchInput.clear();
        branchInput.sendKeys(BRANCH_NAME);

        Assert.assertEquals(getDriver().findElement(branchSpecifier).getAttribute("value"), BRANCH_NAME,
                "The branch name does not match the expected one!");
    }
    @Test(dependsOnMethods = "testBranchesToBuild")
    public void testSCMAuthenticationFails(){
        goToCongigurePage();
        gitButton();
        enterRepositoryURL();

        getDriver().findElement(pageBody).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(error,
                "Failed to connect to repository"));

        String actualError = getDriver().findElement(error).getText();

        Assert.assertTrue(actualError.contains("Failed to connect to repository"),
                "Ожидаемый текст ошибки не найден" + actualError);
    }
}
