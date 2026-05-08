package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class PipelineTest extends BaseTest {

    @Test
    public void testCreatePipeline() {
        final String projectName = "new Pipeline";

        List<String> projectList = new HomePage(getDriver()).clickItemNewJob()
                .setProjectName(projectName)
                .createPipeline()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectList.getFirst(), projectName);
    }

    @Test
    public void testDisableProject() {
        String projectName = "Pipeline_" + System.currentTimeMillis();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(projectName);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Pipeline']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait10().until(ExpectedConditions.urlContains("/configure"));

        WebElement disableControl = getWait10().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Disable')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", disableControl);
        try {
            disableControl.click();
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", disableControl);
        }

        getWait10().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();
        getWait10().until(ExpectedConditions.urlContains("/job/" + projectName + "/"));

        WebElement warningMessage = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(), 'This project is currently disabled') or contains(normalize-space(), 'This project is disabled')]")));
        Assert.assertTrue(warningMessage.isDisplayed(), "Disabled warning message should be displayed");

        WebElement enableButton = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Enable' or contains(normalize-space(), 'Enable')]")));
        Assert.assertTrue(enableButton.isDisplayed(), "Enable button should be displayed");
    }
}
