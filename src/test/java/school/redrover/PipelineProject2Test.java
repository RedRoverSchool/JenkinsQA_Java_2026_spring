package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class PipelineProject2Test extends BaseTest {

    private static final String PROJECT_NAME = "MyPipelineProject";

    @Test
    public void testCreateWithValidName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.id("name")))).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        getDriver().findElement(By.xpath("//span[@class='jenkins-mobile-hide']")).click();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jenkins-table__link > span:first-child"))).getText(),
                PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreateWithValidName")
    public void testCreateWithDuplicateName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated((By.id("name")))).sendKeys(PROJECT_NAME);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='itemname-invalid']")).getText(),
                "» A job already exists with the name ‘%s’".formatted(PROJECT_NAME));
    }

    @Test
    public void testCreateWithEmptyName() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Pipeline']"))).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='itemname-required']"))).getText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}
