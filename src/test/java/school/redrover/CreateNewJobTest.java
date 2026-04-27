package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewJobTest extends BaseTest {

    @Test
    public void testCreateNewJob() {
        getDriver().findElement(By.xpath("//a[@href = 'newJob']")).click();

        WebElement itemNameField = getDriver().findElement(By.id("name"));
        itemNameField.click();
        itemNameField.sendKeys("test_job_1");

        getDriver().findElement(By.xpath("//li[@class = 'jenkins_branch_OrganizationFolder']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("page-header")));
        getDriver().findElement(By.id("jenkins-head-icon")).click();

        WebElement table = getDriver().findElement(By.xpath("//tr/td[3]"));
        WebElement itemType = getDriver().findElement(By.xpath("//*[@title='Organization Folder']"));

        Assert.assertEquals(table.getText(), "test_job_1");
        Assert.assertTrue(itemType.isDisplayed());
    }
}
