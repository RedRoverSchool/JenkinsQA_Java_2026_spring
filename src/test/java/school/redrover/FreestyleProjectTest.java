package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FreestyleProjectTest extends BaseTest {

    private final static String PROJECT_NAME = "FreestyleProject";
    private final static String NEW_PROJECT_NAME_1 = "FreestyleProject1";
    private final static String NEW_PROJECT_NAME_2 ="FreestyleProject2";
    private static final String REPOSITORY_URL = "https://github.com";
    private static final String BRANCH_NAME = "*/main";

    private void goToConfigurePage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'job/')]//span[text()='%s']".formatted(PROJECT_NAME)))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/configure')]"))).click();
    }

    private void gitButton(){
        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);
    }

    private void enterRepositoryURL(){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.url"))).
                sendKeys(REPOSITORY_URL);
    }

    @Ignore
    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlContains("configure")));
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.id("jenkins-head-icon"))).click();

        String actualName = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href, 'job/Freestyle')]//span"))).getText();

        Assert.assertEquals(actualName, PROJECT_NAME);
    }

    @Ignore
    @Test (dependsOnMethods = "testCreate")
    public void testAddDescription() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(PROJECT_NAME)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), PROJECT_NAME));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/FreestyleProject/configure']"))).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Description");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//div[@id='description-content']")).getText(),"Description");
    }

    @Ignore
    @Test(dependsOnMethods = "testAddDescription")
    public void testDisable() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(PROJECT_NAME)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), PROJECT_NAME));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/FreestyleProject/configure']"))).click();
        getDriver().findElement(By.xpath("//label[@class='jenkins-toggle-switch__label ']")).click();
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertTrue(getDriver().findElement(
                By.id("enable-project")).getText().contains("This project is currently disabled"));
    }

    @Ignore
    @Test(dependsOnMethods = "testDisable")
    public void testEnable() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(PROJECT_NAME)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), PROJECT_NAME));
        getDriver().findElement(By.xpath("//button[@value='Enable']")).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), PROJECT_NAME));
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/job/FreestyleProject/configure']"))).click();

        Assert.assertEquals(getDriver().findElement(
                        By.className("jenkins-toggle-switch__label__checked-title")).getText(),
                "Enabled");
    }

    @Ignore
    @Test(dependsOnMethods = "testEnable")
    public void testRename() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(PROJECT_NAME)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), PROJECT_NAME));
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Rename']/.."))).click();
        getDriver().findElement(By.xpath("//input[@name='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@name='newName']")).sendKeys(NEW_PROJECT_NAME_1);
        getDriver().findElement(By.name("Submit")).click();
        
        Assert.assertEquals(getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='main-panel']//h1"))).getText(), NEW_PROJECT_NAME_1);
    }

    @Ignore
    @Test(dependsOnMethods = "testRename")
    public void testBuildNowCheckAlert() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(NEW_PROJECT_NAME_1)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), NEW_PROJECT_NAME_1));
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Build Now']/.."))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("notification-bar"))).getText(), "Build scheduled");
    }

    @Ignore
    @Test(dependsOnMethods = "testBuildNowCheckAlert")
    public void testBuildNow() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(NEW_PROJECT_NAME_1)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), NEW_PROJECT_NAME_1));
        getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.className("app-builds-container__item")));

        List<String> listOfBuilds = getDriver().findElements(By.className("app-builds-container__item"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertEquals(listOfBuilds.size(), 1);
    }

    @Ignore
    @Test(dependsOnMethods = "testBuildNow")
    public void testBuildAfterOtherProjectsAreBuild() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(NEW_PROJECT_NAME_2);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);",
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[name = 'jenkins-triggers-ReverseBuildTrigger']"))));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[name = 'jenkins-triggers-ReverseBuildTrigger']"))));

        getWait10().until(ExpectedConditions.presenceOfElementLocated(By.name("_.upstreamProjects")))
                .sendKeys(NEW_PROJECT_NAME_1, Keys.TAB);
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(text(), 'Trigger even if the build fails')]"))).click();
        getDriver().findElement(By.name("Submit")).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), NEW_PROJECT_NAME_2));
        getDriver().findElement(By.xpath("//a[@data-build-success='Build scheduled']")).click();

        List <String> listOfBuilds = getDriver().findElements(By.className("app-builds-container__item"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertEquals(listOfBuilds.size(), 1);
    }

    @Ignore
    @Test(dependsOnMethods = "testBuildAfterOtherProjectsAreBuild")
    public void testDelete() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='%s']".formatted(NEW_PROJECT_NAME_2)))).click();
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("h1"), NEW_PROJECT_NAME_2));
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-title='Delete Project']"))).click();
        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();
        List<String> listOfJobs = getDriver().findElements(By.cssSelector(".jenkins-table__link > span:nth-child(1)"))
                .stream()
                .map(WebElement::getText).toList();

        Assert.assertEquals(listOfJobs.size(), 1);
    }

    @Test
    public void testAddBuildStepOptions(){

        String projectName = "Freestyle_Test_" + System.currentTimeMillis();

        List<String> expectedTexts = Arrays.asList(
                "Execute Windows batch command",
                "Execute shell",
                "Invoke Ant",
                "Invoke Gradle script",
                "Invoke top-level Maven targets",
                "Run with timeout",
                "Set build status to \"pending\" on GitHub commit");

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']"))).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebElement addBuildStepButton = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@suffix='builder']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", addBuildStepButton);

        getWait5().until(ExpectedConditions.elementToBeClickable(addBuildStepButton));
        addBuildStepButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'jenkins-dropdown')]")));

        List<WebElement> dropdownItems = getWait10().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='jenkins-dropdown jenkins-dropdown--compact']//button")));
        List<String> actualTexts = dropdownItems.stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertEquals(actualTexts, expectedTexts,
                "Dropdown options should match expected list");
    }

    @Test
    public void testDeleteBuildStep() {

        String projectName = "Freestyle_Test_" + System.currentTimeMillis();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/view/all/newJob']"))).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();


        WebElement addBuildStepButton = getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@suffix='builder']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", addBuildStepButton);
        getWait2().until(driver -> true); // небольшая пауза
        addBuildStepButton.click();

        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Execute Windows batch command')]")))
                .click();

        WebElement commandField = getWait10().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//textarea[@name='command']")));commandField.sendKeys("echo 'Test'");

        WebElement deleteButton = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//button[contains(@class, 'repeatable-delete')])[last()]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        getWait2().until(driver -> true); // небольшая пауза

        deleteButton.click();

        boolean commandFieldExists = getWait5().until(
                driver -> driver.findElements(By.xpath("//textarea[@name='command']")).isEmpty());
        Assert.assertTrue(commandFieldExists, "Build step should disappear immediately");
        Assert.assertTrue(commandFieldExists, "Build step should disappear immediately after clicking delete");

        WebElement saveButton = getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@name='Submit']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        getWait2().until(driver -> true); // небольшая пауза
        saveButton.click();

        getWait10().until(ExpectedConditions.urlContains("/job/" + projectName.replace(" ", "%20")));

        WebElement configureLink = getWait10().until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/configure')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", configureLink);
        getWait2().until(driver -> true);
        configureLink.click();

        commandFieldExists = getWait5().until(
                driver -> driver.findElements(By.xpath("//textarea[@name='command']")).isEmpty());

        Assert.assertTrue(commandFieldExists, "Build step should disappear immediately");
        Assert.assertTrue(commandFieldExists, "Build step should not appear after reopening configuration");
    }

    @Ignore
    @Test
    public void testCreateProject(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.cssSelector("li.hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlContains("configure")));
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.id("jenkins-head-icon"))).click();

        String actualName = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href, 'job/Freestyle')]//span"))).getText();

        Assert.assertEquals(actualName, PROJECT_NAME);
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testRepositoryURL() {
        goToConfigurePage();
        gitButton();
        enterRepositoryURL();

        Assert.assertEquals(getDriver().findElement(By.name("_.url")).getAttribute("value"), REPOSITORY_URL,
                "The repository URL does not match!");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
    public void testCredentials() {
        goToConfigurePage();
        gitButton();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//select[@name='_.credentialsId']"))).isDisplayed(),
                "The Credentials drop-down list is not displayed");
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
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

    @Ignore
    @Test(dependsOnMethods = "testCreateProject")
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