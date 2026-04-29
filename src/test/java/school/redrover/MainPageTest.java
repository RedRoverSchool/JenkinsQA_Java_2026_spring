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

public class MainPageTest extends BaseTest {

    private static final String PIPELINE_NAME = "PipelineName";
    private static final String FOLDER_NAME = "FolderName";

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
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