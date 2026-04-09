package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class Folder1Test extends BaseTest {

    @Test
    public void testCreateFolder() {

        String folderName = "Test_Folder";
        String displayName = "New Folder";
        String descriptionname = "Test description";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//li[@class='com_cloudbees_hudson_plugins_folder_Folder']")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        getDriver().findElement(By.xpath("//input[@class='jenkins-input validated  ']")).sendKeys(displayName);
        getDriver().findElement(By.xpath("//textarea[@name='_.description']")).sendKeys(descriptionname);

        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='job-index-headline page-headline']")));
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText(),
                displayName);
    }
}
