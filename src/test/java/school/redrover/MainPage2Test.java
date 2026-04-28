package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

import java.util.List;

public class MainPage2Test extends BaseTest {

    private static final String TEXT_DESCRIPTION_BUTTON = "Add description";
    private static final String DESC_MESSAGE = "Some description text here";
    private static final String UPDATED_DESC_MESSAGE = "Updated description";
    private static final String PIPELINE_NAME = "PipelineName";
    private static final String FOLDER_NAME = "FolderName";

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    @Test
    public void testAddDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("description"))).sendKeys(DESC_MESSAGE);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testUpdateDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(UPDATED_DESC_MESSAGE);
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@formnovalidate='formNoValidate']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getDriver().findElement(By.id("description-content")).getText(), UPDATED_DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testUpdateDescription")
    public void testCancelUpdateDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable((By.linkText("Edit description")))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).sendKeys(DESC_MESSAGE);
        getDriver().findElement(By.xpath("//button[text()='Cancel']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText(), UPDATED_DESC_MESSAGE);
    }

    @Test(dependsOnMethods = "testCancelUpdateDescription")
    public void testDeleteDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']"))).clear();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link"))).getText(),
                TEXT_DESCRIPTION_BUTTON);
    }

    @Test
    public void testOrderName() {
        TestUtils.createJob(getDriver(), getWait10(), PIPELINE_NAME, TestUtils.JobType.PIPELINE);
        goToMainPage();
        TestUtils.createJob(getDriver(), getWait10(), FOLDER_NAME, TestUtils.JobType.FOLDER);
        goToMainPage();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + PIPELINE_NAME + "']")));
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + FOLDER_NAME + "']")));

        List<String> actualOrder = getDriver().findElements(By.cssSelector(".jenkins-table__link.model-link.inside"))
                .stream()
                .map(WebElement::getText)
                .toList();

        Assert.assertEquals(actualOrder, actualOrder.stream().sorted().toList(), "Not an alphabetical order!");
    }
}