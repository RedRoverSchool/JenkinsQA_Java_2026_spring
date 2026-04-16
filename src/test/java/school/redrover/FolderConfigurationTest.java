package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FolderConfigurationTest extends BaseTest {
    public static final String FOLDER_NAME = "FolderInitial";
    public static final By FOLDER_NAME_MAIN_PAGE = By.cssSelector(".jenkins-table__link > span:first-child");

    private void createFolder(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
    }

    private void goToMainPage() {
        WebElement logo = getWait10().until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.app-jenkins-logo")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/view/all/newJob']"))).isDisplayed();
    }

    private void goToConfigPage() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.className("jenkins-menu-dropdown-chevron"))).click();
    }

    @Test
    public void testCreate() {
        createFolder(FOLDER_NAME);
        goToMainPage();

        Assert.assertEquals(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(FOLDER_NAME_MAIN_PAGE)).getText(), FOLDER_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testRename() {
        final String FOLDER_NEW_NAME = "FolderNewName";

        goToMainPage();
        goToConfigPage();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Configure']"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(FOLDER_NEW_NAME);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']"))).click();

        goToMainPage();

        Assert.assertEquals(getWait10().until(ExpectedConditions.visibilityOfElementLocated(FOLDER_NAME_MAIN_PAGE)).getText(), FOLDER_NEW_NAME);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testAddDescription() {
        String descriptiontext = "DescriptionForTest";

        goToConfigPage();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Configure']"))).click();

        getDriver().findElement(By.name("_.description")).sendKeys(descriptiontext);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("view-message"))).getText(), descriptiontext);
    }
}