package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.project.FreestyleProjectPage;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FreestyleProjectTest extends BaseTest {

    private final static String PROJECT_NAME = "FreestyleProject";
    private final static String PROJECT_NAME_UPDATED = "My FreestyleProject test";
    private final static String NEW_PROJECT_NAME_1 = "FreestyleProject1";
    private final static String NEW_PROJECT_NAME_2 ="FreestyleProject2";
    private static final String REPOSITORY_URL = "https://github.com/";
    private static final String BRANCH_NAME = "*/main";
    private static final String SOURCE_ITEM_NAME = "source_item";
    private static final String DESCRIPTION_TEXT = "My test description";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String BUILD_STEP_NAME = "Test";

    private void goToConfigurePage(){
        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'job/')]//span[text()='%s']".formatted(NEW_PROJECT_NAME_1)))).click();
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

    @Test
    public void testCreate() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.getFirst(), PROJECT_NAME);
    }

    @Ignore
    @Test(dependsOnMethods = "testCreate")
    public void testEnableDeleteWorkspaceBeforeBuildStarts() {

        boolean isSelected = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .enableDeleteWorkspaceBeforeBuildStarts()
                .clickSaveButton()
                .clickConfigure()
                .isDeleteWorkspaceBeforeBuildStartsSelected();

        Assert.assertTrue(isSelected);
    }

    @Test (dependsOnMethods = "testCreate")
    public void testAddBuildStepDropdownContainsAllOptions(){
        List<String> expectedTexts = Arrays.asList(
                "Execute Windows batch command",
                "Execute shell",
                "Invoke Ant",
                "Invoke Gradle script",
                "Invoke top-level Maven targets",
                "Run with timeout",
                "Set build status to \"pending\" on GitHub commit");

        List<String> actualTexts= new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAddBuildStep()
                .listOfBuildSteps();

        Assert.assertEquals(actualTexts, expectedTexts,
                "Dropdown options should match expected list");
    }

    @Test(dependsOnMethods = "testAddBuildStepDropdownContainsAllOptions")
    public void testDeleteBuildStep() {
        boolean commandFieldExists = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAddBuildStep()
                .clickOnBuildStep()
                .enterCommand(BUILD_STEP_NAME)
                .clickDeleteButton();

        Assert.assertTrue(commandFieldExists);
    }

    @Test (dependsOnMethods = "testDeleteBuildStep")
    public void testAddDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescriptionText,DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testDisableProject() {
        Boolean projectDisabledMessage = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .disableProjectToggle()
                .clickSaveButton()
                .getProjectIsDisabledMessage();

        Assert.assertTrue(projectDisabledMessage);
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testEnableProject() {
        Boolean projectEnabledMessage = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .enableProject()
                .clickConfigure()
                .getProjectState("Enabled");

        Assert.assertTrue(projectEnabledMessage);
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testRename() {
        Boolean updatedProjectName = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .renameProject()
                .setNewProjectName(PROJECT_NAME_UPDATED)
                .clickRenameButton()
                .getUpdatedProjectName(PROJECT_NAME_UPDATED);
        
        Assert.assertTrue(updatedProjectName);
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

        List <String> listOfBuilds = getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("app-builds-container__item")))
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
        List<String> listOfJobs = getWait10().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".jenkins-table__link > span:nth-child(1)")))
                .stream()
                .map(WebElement::getText).toList();

        Assert.assertEquals(listOfJobs.size(), 1);
    }

    @Ignore
    @Test(dependsOnMethods = "testDelete")
    public void testRepositoryURL() {
        goToConfigurePage();
        gitButton();
        enterRepositoryURL();

        Assert.assertEquals(getDriver().findElement(By.name("_.url")).getAttribute("value"), REPOSITORY_URL,
                "The repository URL does not match!");
    }

    @Ignore
    @Test(dependsOnMethods = "testRepositoryURL")
    public void testCredentials() {
        goToConfigurePage();
        gitButton();

        Assert.assertTrue(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//select[@name='_.credentialsId']"))).isDisplayed(),
                "The Credentials drop-down list is not displayed");
    }

    @Ignore
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

    @Ignore
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

    @Ignore
    @Test
    public void testCreateSourceItem(){
        TestUtils.createJob(getDriver(), SOURCE_ITEM_NAME, TestUtils.JobType.FREESTYLE)
                .clickOnProject(SOURCE_ITEM_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .fillDescription(DESCRIPTION_TEXT)
                .clickCheckBoxGitHub()
                .fillGitURL(REPOSITORY_URL)
                .clickSaveButton();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.job-index-headline"))).getText(),
                SOURCE_ITEM_NAME
        );
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExistingWithEmptyListItems(){
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .enterCopyItemName("Empty");
        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".jenkins-dropdown__placeholder")
                )).getText(),
                "No items"
        );
    }

    @Ignore
    @Test(dependsOnMethods = "testCreateSourceItem")
    public void testCreateItemFromExisting() {
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .enterCopyItemName(SOURCE_ITEM_NAME)
                .clickOkButton();

        WebElement gitCheckBoxButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[name='githubProject'][type='checkbox']")));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.linkText(NEW_ITEM_NAME))).getText(),
                NEW_ITEM_NAME
        );

        softAssert.assertTrue(
                Objects.requireNonNull(getDriver().getCurrentUrl()).contains("/job/" + NEW_ITEM_NAME + "/configure"),
                "Не удалось перейти на страницу конфигурации нового проекта"
        );

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("description"))).getAttribute("value"),
                DESCRIPTION_TEXT
        );

        softAssert.assertTrue(
                gitCheckBoxButton.isSelected(),
                "Git project is not selected"
        );

        softAssert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("_.projectUrlStr"))).getAttribute("value"),
                REPOSITORY_URL
        );

        softAssert.assertAll();
    }
}
