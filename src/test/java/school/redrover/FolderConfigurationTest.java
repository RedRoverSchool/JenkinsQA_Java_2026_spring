package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FolderConfigurationTest extends BaseTest {

    private static final String FOLDER_NAME1 = "FolderName1";
    private static final String FOLDER_NAME2 = "FolderName2";

    @Test
    public void testRename() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='name']"))).sendKeys(FOLDER_NAME1);
        getDriver().findElement(By.xpath("//span[text()='Folder']")).click();
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ok-button']"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        getDriver().findElement(By.xpath("//span[text()='Jenkins']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jenkins-table__link model-link inside']")));
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='jenkins-menu-dropdown-chevron']"))).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Configure']"))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='_.displayNameOrNull']"))).sendKeys(FOLDER_NAME2);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        getDriver().findElement(By.xpath("//span[text()='Jenkins']")).click();

        WebElement newName = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jenkins-table__link model-link inside']")));

        Assert.assertEquals(newName.getText(), FOLDER_NAME2);

    }
}
