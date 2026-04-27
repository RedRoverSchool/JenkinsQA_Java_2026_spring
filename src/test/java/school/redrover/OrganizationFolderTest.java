package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;


public class OrganizationFolderTest extends BaseTest {
    public static final String ORG_FOLDER_NAME = "OrganizationFolder";
    public static final String DESCRIPTION_TEXT = "Description: New project";
    public static final String DISPLAY_NAME = "OrgFolderDisplayName";

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    @Test
    public void testCreate() {
        TestUtils.createJob(getDriver(), getWait10(), ORG_FOLDER_NAME, TestUtils.JobType.FOLDER);
        goToMainPage();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".jenkins-table__link > span:first-child"))).getText(), ORG_FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(ORG_FOLDER_NAME)))).click();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='description']"))).sendKeys(DESCRIPTION_TEXT);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Save']"))).click();

        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(By.id("description-content"), DESCRIPTION_TEXT));

        Assert.assertEquals(
                getDriver().findElement(By.id("description-content")).getText(), DESCRIPTION_TEXT);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDisplayName () {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[@href='job/%s/']".formatted(ORG_FOLDER_NAME)))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/job/%s/configure']".formatted(ORG_FOLDER_NAME)))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(DISPLAY_NAME);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Save']"))).click();

        Assert.assertEquals(getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText(), DISPLAY_NAME);
    }
}
