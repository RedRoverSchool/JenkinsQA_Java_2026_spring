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
import school.redrover.page.HomePage;
import school.redrover.page.project.FreestyleProjectPage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FreestyleProjectTest extends BaseTest {

    private final static String PROJECT_NAME = "FreestyleProject";
    private final static String NO_EXISTING_PROJECT = "My FreestyleProject Test";
    private final static String NEW_PROJECT_NAME_1 = "FreestyleProject1";
    private static final String REPOSITORY_URL = "https://github.com/";
    private static final String BRANCH_NAME = "*/main";
    private static final String SOURCE_ITEM_NAME = "source_item";
    private static final String DESCRIPTION_TEXT = "My test description";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String POPUP_MESSAGE = "Build scheduled";

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

    @Test
    public void testCheckboxIsChecked() {

        boolean isChecked = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .setDeleteWorkspaceBeforeBuildStartsCheckbox(true)
                .clickSaveButton()
                .clickConfigure()
                .isDeleteWorkspaceBeforeBuildStartsCheckboxChecked();

        Assert.assertTrue(isChecked);
    }

    @Test
    public void testCheckboxIsUnchecked() {
        boolean isChecked = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .setDeleteWorkspaceBeforeBuildStartsCheckbox(false)
                .clickSaveButton()
                .clickConfigure()
                .isDeleteWorkspaceBeforeBuildStartsCheckboxChecked();

        Assert.assertFalse(isChecked);
    }

    @Test
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
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAddBuildStep()
                .listOfBuildSteps();

        Assert.assertEquals(actualTexts, expectedTexts,
                "Dropdown options should match expected list");
    }

    @Test
    public void testAddBuildStep() {
        boolean isBuildStepAdded = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAddBuildStep()
                .clickExecuteWindowsBatchCommandMenuItem()
                .clickSaveButton()
                .clickConfigure()
                .isBuildStepAdded();

        Assert.assertTrue(isBuildStepAdded);
    }

    @Test
    public void testDeleteBuildStep() {
        boolean commandFieldExists = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .clickAddBuildStep()
                .clickExecuteWindowsBatchCommandMenuItem()
                .clickSaveButton()
                .clickConfigure()
                .clickDeleteButton();

        Assert.assertTrue(commandFieldExists);
    }

    @Test
    public void testAddDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .enterDescription(DESCRIPTION_TEXT)
                .clickSaveButton()
                .getDescription();

        Assert.assertEquals(actualDescriptionText,DESCRIPTION_TEXT);
    }

    @Test
    public void testDisableProject() {
        Boolean projectDisabledMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .disableProjectToggle()
                .clickSaveButton()
                .getProjectIsDisabledMessage();

        Assert.assertTrue(projectDisabledMessage);
    }

    @Test
    public void testEnableProject() {
        Boolean projectEnabledMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .disableProjectToggle()
                .clickSaveButton()
                .enableProject()
                .clickConfigure()
                .getProjectState("Enabled");

        Assert.assertTrue(projectEnabledMessage);
    }

    @Test
    public void testBuildNowDisplaysPopupMessage() {
         Boolean popupMessage = new HomePage(getDriver())
                 .clickItemNewJob()
                 .setProjectName(PROJECT_NAME)
                 .selectFreeStyleProject()
                 .clickOkButton()
                 .goHomePage()
                 .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                 .isPopupMessageDisplayed(POPUP_MESSAGE);

        Assert.assertTrue(popupMessage);
    }

    @Test
    public void testBuildNowCreatesBuild() {
        int build_count = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickBuildNow()
                .getBuilds()
                .size();

        Assert.assertEquals(build_count, 1);
    }

    @Test(dependsOnMethods = "testBuildNowCreatesBuild")
    public void testBuildAfterOtherProjectsAreBuild() {
        int build_count = new HomePage(getDriver())
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .selectBuildAfterOtherProjectsAreBuiltCheckbox()
                .enterMessageIntoProjectsToWatchField(PROJECT_NAME)
                .selectTriggerEvenIfTheBuildFailsRadioButton()
                .clickSaveButton()
                .getSideMenu()
                .clickBuildNow()
                .getBuilds()
                .size();

        Assert.assertEquals(build_count, 1);
    }

    @Test
    public void testDeleteProject() {
        int projectCount = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickDelete()
                .getProjectList()
                .size();

        Assert.assertEquals(projectCount, 0);
    }

    @Test(dependsOnMethods = "testDeleteProject")
    public void testRepositoryURL() {
        String actualRepositoryURL = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .selectGitRadioButton()
                .enterRepositoryURL(REPOSITORY_URL)
                .getRepositoryUrl();

        Assert.assertEquals(actualRepositoryURL, REPOSITORY_URL,
                "The repository URL does not match!");
    }

    @Test
    public void testIsBranchSpecifierValueSaved() {
        String branchSpecifierText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .selectGitRadioButton()
                .enterBranchSpecifier(BRANCH_NAME)
                .clickSaveButton()
                .clickConfigure()
                .getBranchSpecifierValue();
        Assert.assertEquals(branchSpecifierText, BRANCH_NAME,
                "The branch name does not match the expected one!");
    }

    @Test
    public void testSCMAuthenticationFails(){
        String actualRepositoryConnectionErrorMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .selectGitRadioButton()
                .enterRepositoryURL(REPOSITORY_URL)
                .clickSaveButton()
                .clickConfigure()
                .getRepositoryConnectionErrorMessage();

        Assert.assertTrue(actualRepositoryConnectionErrorMessage.contains("Failed to connect to repository"),
                "Ожидаемый текст ошибки не найден");
    }

    @Test
    public void testCreateSourceItem() {
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .clickConfigure()
                .fillDescription(DESCRIPTION_TEXT)
                .clickCheckBoxGitHub()
                .fillGitURL(REPOSITORY_URL)
                .clickSaveButton();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.job-index-headline"))).getText(),
                PROJECT_NAME
        );
    }

    @Test
    public void testCopyFromShowsNoItemsWhenNoMatchingProjectsFound() {
        String actualEmptyStateMessage =new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickItemNewJob()
                .enterMessageToCopyFromField(NO_EXISTING_PROJECT)
                .getEmptyStateMessage();
        Assert.assertEquals(actualEmptyStateMessage, "No items");
    }

    @Ignore
    @Test
    public void testCreateItemFromExisting() {
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreeStyleProject()
                .clickOkButton()
                .goHomePage()
                .clickItemNewJob()
                .enterMessageToCopyFromField(PROJECT_NAME)
                .setProjectName(NEW_PROJECT_NAME_1)
                .selectFreeStyleProject()
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
