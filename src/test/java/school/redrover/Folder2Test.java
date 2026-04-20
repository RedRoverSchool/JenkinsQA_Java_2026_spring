package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class Folder2Test extends BaseTest {

    public static final String FOLDER_NAME = "New Folder";

    @Test
    public void testNewItemPageOpen() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1")).getText(), "New Item");
    }

    @Test (dependsOnMethods = "testNewItemPageOpen")
    public void testNameEnterAndSelectFolderType() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement enterItemNameField = getDriver().findElement(By.id("name"));
        enterItemNameField.click();
        enterItemNameField.sendKeys(FOLDER_NAME);

        WebElement folderRadioButton = getDriver().findElement(By.xpath("//li[@class = 'com_cloudbees_hudson_plugins_folder_Folder']"));
        folderRadioButton.click();

        Assert.assertEquals(FOLDER_NAME, enterItemNameField.getAttribute("value"));
        Assert.assertTrue(folderRadioButton.getAttribute("class").contains("active"));
    }
}
