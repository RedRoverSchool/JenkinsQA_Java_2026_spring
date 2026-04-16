package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class FolderConfigurationTest extends BaseTest {

    private void createFolder(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
    }

    private void goToMainPage() {
        ProjectUtils.get(getDriver());
    }

    @Test
    public void testRename() {

        final String FOLDER_NAME = "FolderName";
        final String FOLDER_NEW_NAME = "FolderNewName";

        createFolder(FOLDER_NAME);
        goToMainPage();

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-menu-dropdown-chevron']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Configure']"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(FOLDER_NEW_NAME);
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit']"))).click();

        WebElement name = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

        Assert.assertEquals(name.getText(), FOLDER_NEW_NAME);
    }

    @Test
    public void testAddDescription() {
        createFolder("TestFolder");

        getDriver().findElement(By.name("_.description")).sendKeys("DescriptionForTest");

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("view-message"))).getText(), "DescriptionForTest");
    }
}