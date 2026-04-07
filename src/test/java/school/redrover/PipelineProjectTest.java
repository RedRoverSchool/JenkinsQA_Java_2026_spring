package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class PipelineProjectTest extends BaseTest {

    public static final String PIPELINE_NAME = "PipelineName";

    private WebDriverWait getWait5() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }

    private void createPipeline(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();

        getDriver().findElement(By.id("ok-button")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("jenkins-head-icon"))).click();

    }

    @Test
    public void testCreate() {
        createPipeline(PIPELINE_NAME);

        Assert.assertEquals(
                getDriver().findElement(By.cssSelector(".jenkins-table__link > span:first-child")).getText(),
                PIPELINE_NAME);
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

    @Test
    public void testAddDescription() {
        final String description = "SomeDescription";

        createPipeline(PIPELINE_NAME);

        getWait5().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td/a[@href='job/%s/']".formatted(PIPELINE_NAME)))).click();
        getDriver().findElement(By.id("description-link")).click();

        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        Assert.assertEquals(
                getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content"))).getText(),
                description);
    }
}
