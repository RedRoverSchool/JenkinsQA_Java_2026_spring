package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class FolderConfigurationTest extends BaseTest {

    private static final String FOLDER_NAME = "TestFolder_Name";
    private static final By DROP_DOWN_MENU = By.xpath("//button[@class='jenkins-menu-dropdown-chevron']");
    private static final By DROP_DOWN_MENU_CONFIGURE = By.xpath("//a[normalize-space()='Configure']");
    private static final By DESCRIPTION_INPUT_FOLDER = By.name("_.description");

    private void createFolder(String name) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(name);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();
    }

    private void goToMainPage() {
        ProjectUtils.get(getDriver());
    }

    @Test
    public void testRename() {

        final String FOLDER_NEW_NAME = "TestFolder_NewName";

        createFolder(FOLDER_NAME);
        goToMainPage();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(DROP_DOWN_MENU)).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(DROP_DOWN_MENU_CONFIGURE)).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(FOLDER_NEW_NAME);
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
        WebElement name = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

        Assert.assertEquals(name.getText(), FOLDER_NEW_NAME);
    }

    @Test(dependsOnMethods = "testRename")
    public void testAddDescription() {

        getWait5().until(ExpectedConditions.elementToBeClickable(DROP_DOWN_MENU)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(DROP_DOWN_MENU_CONFIGURE)).click();
        getDriver().findElement(DESCRIPTION_INPUT_FOLDER).sendKeys("DescriptionForTest");
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("view-message"))).getText(), "DescriptionForTest");
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testChangeDescription() {
        goToMainPage();

        getWait5().until(ExpectedConditions.elementToBeClickable(DROP_DOWN_MENU)).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(DROP_DOWN_MENU_CONFIGURE)).click();
        getDriver().findElement(DESCRIPTION_INPUT_FOLDER).clear();
        getDriver().findElement(By.name("_.description")).sendKeys("NewDescriptionForTest");
        getWait5().until(ExpectedConditions.elementToBeClickable(By.name("Submit"))).click();

        Assert.assertEquals(getWait5().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("view-message"))).getText(), "NewDescriptionForTest");

    }

}