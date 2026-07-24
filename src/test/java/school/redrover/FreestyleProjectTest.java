package school.redrover;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.project.FreestyleProjectPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;

import java.util.Arrays;
import java.util.List;

public class FreestyleProjectTest extends BaseTest {

    private static final String PROJECT_NAME = "FreestyleProject";
    private static final String PROJECT_NAME_1 = "FreestyleProjectSuper";
    private static final String NO_EXISTING_PROJECT = "My FreestyleProject Test";
    private static final String REPOSITORY_URL = "https://github.com/";
    private static final String BRANCH_NAME = "*/main";
    private static final String DESCRIPTION_TEXT = "My test description";
    private static final String NEW_ITEM_NAME = "new_item_copy";
    private static final String POPUP_MESSAGE = "Build scheduled";

    @Test
    public void testCreate() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.getFirst(), PROJECT_NAME);
    }

    @Story("16.010 Freestyle Project Management > Rename Project ")
    @Owner("Yulia R.")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that project is renamed")
    @Test
    public void testRenameViaContextMenu() {
        List<String> projectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .openProjectDropdownMenu(PROJECT_NAME)
                .clickRenameInDropdown()
                .setNewProjectName(PROJECT_NAME_1)
                .clickRenameButton()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.size(), 1);
        Assert.assertEquals(projectList.getFirst(), PROJECT_NAME_1);
    }

    @Test
    public void testCheckboxIsChecked() {
        boolean isChecked = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .setDeleteWorkspaceBeforeBuildStartsCheckbox(true)
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .isDeleteWorkspaceBeforeBuildStartsCheckboxChecked();

        Assert.assertTrue(isChecked);
    }

    @Test
    public void testApplyButton() {
        boolean isSelected = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .setDeleteWorkspaceBeforeBuildStartsCheckbox(true)
                .clickApply()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .isDeleteWorkspaceBeforeBuildStartsCheckboxChecked();

        Assert.assertTrue(isSelected);
    }

    @Test
    public void testCheckboxIsUnchecked() {
        boolean isChecked = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .setDeleteWorkspaceBeforeBuildStartsCheckbox(false)
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
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
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
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
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .clickAddBuildStep()
                .clickExecuteWindowsBatchCommandMenuItem()
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .isBuildStepAdded();

        Assert.assertTrue(isBuildStepAdded);
    }

    @Test
    public void testDeleteBuildStep() {
        boolean commandFieldExists = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .clickAddBuildStep()
                .clickExecuteWindowsBatchCommandMenuItem()
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .clickDeleteButton();

        Assert.assertTrue(commandFieldExists);
    }

    @Test
    public void testAddDescription() {
        String actualDescriptionText = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
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
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .disableProjectToggle()
                .clickSaveButton()
                .getProjectIsDisabledMessage();

        Assert.assertTrue(projectDisabledMessage);
    }

    @Ignore
    @Test
    public void testEnableProject() {
        Boolean projectEnabledMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .disableProjectToggle()
                .clickSaveButton()
                .enableProject()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .getProjectState("Enabled");

        Assert.assertTrue(projectEnabledMessage);
    }

    @Test
    public void testBuildNowDisplaysPopupMessage() {
         Boolean popupMessage = new HomePage(getDriver())
                 .clickItemNewJob()
                 .setProjectName(PROJECT_NAME)
                 .selectFreestyleProjectAndClickOk()
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
                .selectFreestyleProjectAndClickOk()
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
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .selectBuildAfterOtherProjectsAreBuiltCheckbox()
                .enterMessageIntoProjectsToWatchField(PROJECT_NAME)
                .selectTriggerEvenIfTheBuildFailsRadioButton()
                .clickSave(new FreestyleProjectPage(getDriver()))
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
                .selectFreestyleProjectAndClickOk()
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
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
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
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .selectGitRadioButton()
                .enterBranchSpecifier(BRANCH_NAME)
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .getBranchSpecifierValue();

        Assert.assertEquals(branchSpecifierText, BRANCH_NAME,
                "The branch name does not match the expected one!");
    }

    @Test
    public void testSCMAuthenticationFails(){
        String actualRepositoryConnectionErrorMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .selectGitRadioButton()
                .enterRepositoryURL(REPOSITORY_URL)
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .getRepositoryConnectionErrorMessage();

        Assert.assertTrue(actualRepositoryConnectionErrorMessage.contains("Failed to connect to repository"),
                "Ожидаемый текст ошибки не найден");
    }

    @Test
    public void testCreateSourceItem() {
        new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickOnProject(PROJECT_NAME, new FreestyleProjectPage(getDriver()))
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
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
        String actualEmptyStateMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .clickItemNewJob()
                .enterProjectNameToCopyFromField(NO_EXISTING_PROJECT)
                .getEmptyStateMessage();

        Assert.assertEquals(actualEmptyStateMessage, "No items");
    }

    @Ignore
    @Test
    public void testCreateItemFromExisting() {
        FreestyleProjectConfigPage freestyleProjectPage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .enterDescription(DESCRIPTION_TEXT)
                .selectGitRadioButton()
                .enterRepositoryURL(REPOSITORY_URL)
                .selectBuildAfterOtherProjectsAreBuiltCheckbox()
                .selectTriggerEvenIfTheBuildFailsRadioButton()
                .clickSaveButton()
                .goHomePage()
                .clickItemNewJob()
                .setProjectName(NEW_ITEM_NAME)
                .selectFreeStyleProject()
                .enterProjectNameToCopyFromField(PROJECT_NAME)
                .selectProjectDropDown()
                .clickOK(new FreestyleProjectConfigPage(getDriver()))
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()));

        Assert.assertEquals(freestyleProjectPage.getProjectName(), NEW_ITEM_NAME, "Project name is not correct");
        Assert.assertTrue(freestyleProjectPage.isCurrentUrlCorrect(NEW_ITEM_NAME), "Current url is not correct");
        Assert.assertEquals(freestyleProjectPage.getDescriptionText(), DESCRIPTION_TEXT, "Description text is not correct");
        Assert.assertEquals(freestyleProjectPage.getRepositoryUrl(), REPOSITORY_URL, "Repository url is not correct");
    }

    @Test
    public void testSkipConfigShowsItemOnDashboard() {
        List<String> actualProjectList = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertTrue(actualProjectList.contains(PROJECT_NAME),
                "Created item is not displayed on the dashboard");
    }

    @Test
    public void testDiscardOldBuildsCheckboxConfigurationSaved() {
        FreestyleProjectConfigPage freestyleProjectConfigPage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(PROJECT_NAME)
                .selectFreestyleProjectAndClickOk()
                .clickDiscardOldBuildsCheckbox()
                .enterMessageToDaysToKeepBuildsInput("30")
                .enterMessageToMaxOfBuildToKeepInput("10")
                .clickAdvancedAccordion()
                .enterMessagesToDaysToKeepArtifacts("13")
                .enterMessageToMaxOfBuildsToKeepWithArtifacts("100")
                .clickSaveButton()
                .getSideMenu()
                .clickConfigure(new FreestyleProjectConfigPage(getDriver()))
                .clickAdvancedAccordion();

        Assert.assertTrue(freestyleProjectConfigPage.idDiscardOldBuildsCheckboxChecked());
        Assert.assertEquals(freestyleProjectConfigPage.getDaysToKeepBuildsValue(), "30");
        Assert.assertEquals(freestyleProjectConfigPage.getMaxOfBuildToKeepValue(), "10");
        Assert.assertEquals(freestyleProjectConfigPage.getDaysToKeepArtifactsValue(), "13");
        Assert.assertEquals(freestyleProjectConfigPage.getMaxOfBuildsToKeepWithArtifactsValue(), "100");
    }
}
