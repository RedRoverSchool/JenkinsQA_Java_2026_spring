package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class MultibranchPipelineTest extends BaseTest {

	private final static String PROJECT_NAME = "MultibranchPipelineProject";


	@Test
	public void testCreate() {
		getDriver().findElement(By.xpath("//a[contains(@class, 'task-link-no-confirm') and contains(@it, 'hudson')]")).click();

		WebElement nameInput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		nameInput.clear();
		nameInput.sendKeys(PROJECT_NAME);

		WebElement jobElement = getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']"));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", jobElement);
		jobElement.click();
		getDriver().findElement(By.id("ok-button")).click();

		getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-jenkins-logo"))).click();

		WebElement actualProjectName = getWait10().until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'%s')]".formatted(PROJECT_NAME))));
		Assert.assertEquals(actualProjectName.getText(), PROJECT_NAME);
	}

	@Test
	public void testStatusIconIsDisplayedForMultibranchPipeline() {
		final String projectName = "new-multibranch-pipeline-" + System.currentTimeMillis();
		TestUtils.createJob(
				getDriver(),
				getWait10(),
				projectName,
				TestUtils.JobType.MULTIBRANCH_PIPELINE);

		getWait10().until(ExpectedConditions.urlContains("/configure"));
		getWait10().until(ExpectedConditions.elementToBeClickable(By.className("app-jenkins-logo"))).click();

		By statusIcon = By.xpath("//tr[.//span[normalize-space()='" + projectName + "']]//*[name()='svg']");
		WebElement icon = getWait10().until(ExpectedConditions.visibilityOfElementLocated(statusIcon));
		Assert.assertTrue(icon.isDisplayed());
	}
}




