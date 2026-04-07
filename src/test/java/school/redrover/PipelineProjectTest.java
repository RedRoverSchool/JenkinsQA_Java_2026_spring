package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class PipelineProjectTest extends BaseTest {

    @Test
    public void testCreate() {
        final String pipelineName = "PipelineName";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                pipelineName);
    }

    @Test
    public void testCreateWithoutNameShowsError() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");

        Assert.assertTrue(
                getDriver().findElement(By.id("ok-button")).isDisplayed());
    }
}
