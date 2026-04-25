package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class OrganizationFolderTest extends BaseTest {

    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//a[@href = 'newJob']")).click();
        getDriver().findElement(By.xpath("//input[@class = 'jenkins-input']"))
                .sendKeys("New Organization Folder");

        WebElement orgFolder = getDriver().findElement(
                By.xpath("//li[@class = 'jenkins_branch_OrganizationFolder']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", orgFolder);
        orgFolder.click();

        getDriver().findElement(By.xpath("//button[@type = 'submit']")).click();

        getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"))
                .sendKeys("Organization Folder 1");
        getDriver().findElement(By.xpath("//textarea[@name='_.description']"))
                .sendKeys("New projects");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//h1[contains(text(),'Organization Folder 1')]")).getText(),
                "Organization Folder 1");
        Assert.assertTrue(getDriver().findElement(
                By.xpath("//div[contains(text(),'Folder name: New Organization Folder')]")).getText()
                .contains("New Organization Folder"));
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[contains(text(),'New projects')]")).getText(),
                "New projects");
    }
}
