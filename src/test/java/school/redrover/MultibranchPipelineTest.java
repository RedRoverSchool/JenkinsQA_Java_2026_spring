package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import school.redrover.page.projects.MultibranchProjectPage;
import java.util.List;


public class MultibranchPipelineTest extends BaseTest {
	private final static String PROJECT_NAME = "MultibranchPipelineProject";
	private final static String PROJECT_NAME_1 = "MultibranchPipelineProject1";
	private final static String PROJECT_NAME_DELETE = "Project_To_Delete";

	@Test
	public void testCreate() {
		List<String> projectList = new HomePage(getDriver())
				.clickItemNewJob()
				.setProjectName(PROJECT_NAME)
				.selectMultibranchPipline()
				.clickOkButton()
				.goHomePage()
				.getProjectList();

		Assert.assertEquals(projectList.size(), 1);
		Assert.assertEquals(projectList.getFirst(), PROJECT_NAME);
	}

	@Ignore
	@Test
	public void testStatusIconIsDisplayedForMultibranchPipeline() {
		final String projectName = "new-multibranch-pipeline-" + System.currentTimeMillis();
		TestUtils.createJob(
				getDriver(),
				projectName,
				TestUtils.JobType.MULTIBRANCH_PIPELINE);

		getWait10().until(ExpectedConditions.urlContains("/configure"));
		getWait10().until(ExpectedConditions.elementToBeClickable(By.className("app-jenkins-logo"))).click();

		By statusIcon = By.xpath("//tr[.//span[normalize-space()='" + projectName + "']]//*[name()='svg']");
		WebElement icon = getWait10().until(ExpectedConditions.visibilityOfElementLocated(statusIcon));
		Assert.assertTrue(icon.isDisplayed());
	}

	@Test(dependsOnMethods = "testCreate")
	public void testRename(){
		List<String> projectList = new HomePage(getDriver())
				.clickOnProject(PROJECT_NAME,new MultibranchProjectPage(getDriver()))
				.clickRename(PROJECT_NAME)
				.enterNewName(PROJECT_NAME_1)
				.clickRenameButton()
				.goHomePage()
				.getProjectList();

		Assert.assertEquals(projectList.size(), 1);
		Assert.assertEquals(projectList.getFirst(), PROJECT_NAME_1);
	}

	@Test (dependsOnMethods = "testRename")
	public void testRenameViaContextMenu(){
		List<String> projectList = new HomePage(getDriver())
				.openProjectDropdownMenu(PROJECT_NAME_1)
				.clickRenameInDropdown()
				.setNewProjectName(PROJECT_NAME)
				.clickRenameButton()
				.goHomePage()
				.getProjectList();

		Assert.assertEquals(projectList.size(), 1);
		Assert.assertEquals(projectList.getFirst(), PROJECT_NAME);
	}

	@Test
	public void testDeleteProjectViaSideMenu() {

		List<String> projectList = TestUtils.createJob(getDriver(), PROJECT_NAME_DELETE, TestUtils.JobType.MULTIBRANCH_PIPELINE)
				.clickOnProject(PROJECT_NAME_DELETE, new MultibranchProjectPage(getDriver()))
				.clickDeleteInSideMenu()
				.confirmDelete()
				.getProjectList();

		Assert.assertEquals(projectList.size(), 0);
	}

	@Test
	public void testDeleteProjectViaDashboardMenu() {

		List<String> projectList = TestUtils.createJob(getDriver(), PROJECT_NAME_DELETE, TestUtils.JobType.MULTIBRANCH_PIPELINE)
				.openProjectDropdownMenu(PROJECT_NAME_DELETE)
				.clickDeleteInDropdown()
				.confirmDelete(PROJECT_NAME_DELETE)
				.getProjectList();

		Assert.assertEquals(projectList.size(), 0);
	}
}
