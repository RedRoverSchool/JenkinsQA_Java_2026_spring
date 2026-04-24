package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultibranchPipelineTest extends BaseTest {

	@Test
	public void testCreate() {
		final String projectName = "project01";

		getDriver().findElement(By.xpath("//a[contains(@class, 'task-link-no-confirm') and contains(@it, 'hudson')]")).click();

		WebElement nameInput = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		nameInput.clear();
		nameInput.sendKeys(projectName);

		getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']")).click();
		getDriver().findElement(By.id("ok-button")).click();

		getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.className("app-jenkins-logo"))).click();

		WebElement actualProjectName = getWait10().until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'%s')]".formatted(projectName))));
		Assert.assertEquals(actualProjectName.getText(), projectName);
	}
}
