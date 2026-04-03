package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.time.Duration;


public class CreateOrganizationFolder extends BaseTest {

    @Test
    public void createOrganizationFolderTest(){

        final String projectName = "Make Man Utd great again";

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//li[@class='jenkins_branch_OrganizationFolder']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']")).sendKeys(projectName);
        getDriver().findElement(By.xpath("//select[@class='jenkins-select__input dropdownList']" +
                "//option[text()='All branches get the same properties']")).click();
        getDriver().findElement(By.xpath("//option[text()='Named branches get different properties']")).click();
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        WebElement homeIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='jenkins-mobile-hide']")));
        homeIcon.click();

        String actual = getDriver().findElement(By.xpath("//table[@id='projectstatus']" +
                "//a[@class='jenkins-table__link model-link inside']")).getText();

        Assert.assertEquals(actual, projectName);

    }
}
