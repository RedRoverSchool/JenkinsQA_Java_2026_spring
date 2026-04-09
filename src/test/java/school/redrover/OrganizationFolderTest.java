package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import static java.sql.DriverManager.getDriver;

public class OrganizationFolderTest extends BaseTest {

    @Test
    public void testCreateOrganizationFolder() {
        WebElement createJobButton = getDriver().findElement(By.xpath("//a[@href = 'newJob']"));
        createJobButton.click();

        WebElement enterItemNameField = getDriver().findElement(By.xpath("//input[@class = 'jenkins-input']"));
        enterItemNameField.click();
        enterItemNameField.sendKeys("New Organization Folder");

        WebElement selectOrganizationFolder = getDriver().findElement(By.xpath("//li[@class = 'jenkins_branch_OrganizationFolder']"));
        selectOrganizationFolder.click();

        WebElement okButton = getDriver().findElement(By.xpath("//button[@type = 'submit']"));
        okButton.click();

        WebElement displayNameField = getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']"));
        displayNameField.click();
        displayNameField.sendKeys("Organization Folder 1");

        WebElement descriptionField = getDriver().findElement(By.xpath("//textarea[@name='_.description']"));
        descriptionField.click();
        descriptionField.sendKeys("New projects");

        WebElement saveButton = getDriver().findElement(By.xpath("//button[@name='Submit']"));
        saveButton.click();


        WebElement textDisplayName = getDriver().findElement(By.xpath("//div[contains(text(),'Folder name: New Organization Folder')]"));
        String text = textDisplayName.getText();
        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[contains(text(),'Organization Folder 1')]")).getText(),
                "Organization Folder 1");
        Assert.assertTrue(text.contains("New Organization Folder"));
        Assert.assertEquals(getDriver().findElement(By.xpath("//div[contains(text(),'New projects')]")).getText(),
                "New projects");
    }
}
