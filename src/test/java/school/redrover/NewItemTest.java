package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class NewItemTest extends BaseTest {

    private static final String JOB_NAME = "existing_job_01";

    @Test
    public void testSelectItemTypeWithValidName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys(JOB_NAME);
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1"))).getText(), JOB_NAME);
    }

    @Test(dependsOnMethods = "testSelectItemTypeWithValidName")
    public void testDuplicateName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/']")));
        getDriver().findElement(By.xpath("//a[@href='/']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']")));

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/view/all/newJob']"))).click();
        getDriver().findElement(By.id("name")).sendKeys(JOB_NAME);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

        Assert.assertEquals(getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("itemname-invalid"))).getText(), "» A job already exists with the name ‘existing_job_01’");
    }

    @Test
    public void testSelectAnItemType() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys("Select an item type test");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

    @Test
    public void testSelectItemTypeWithEmptyName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testSelectItemTypeWithInvalidName() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys("$");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}
