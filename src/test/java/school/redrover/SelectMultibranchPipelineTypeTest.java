package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class SelectMultibranchPipelineTypeTest extends BaseTest {

	@Test
	public void testSelectItemType() {
		getDriver().findElement(By.xpath("//a[contains(@class, 'task-link-no-confirm') and contains(@it, 'hudson')]")).click();

		WebElement nameInput = getDriver().findElement(By.id("name"));
		nameInput.clear();
		nameInput.sendKeys("33");

		WebElement multibranchPipelineOption = getDriver().findElement(By.xpath("//span[text()='Multibranch Pipeline']"));
		multibranchPipelineOption.click();

		getDriver().findElement(By.xpath("//*[@id='ok-button' ]")).click();

		Assert.assertEquals(
				getDriver().findElement(By.xpath("//div//*[text()='Configuration']")).getText(),
				"Configuration");

	}
}
