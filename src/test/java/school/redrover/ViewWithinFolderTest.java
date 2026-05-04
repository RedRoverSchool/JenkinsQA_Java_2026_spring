package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;

public class ViewWithinFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "NewFolder";
    private static final String VIEW_NAME = "NewViewForFolder";

    @Ignore
    @Test
    public void testCreateMyView(){

        TestUtils.createJob(getDriver(), FOLDER_NAME, TestUtils.JobType.FOLDER);

        getDriver().findElement(By.xpath("//a[@class='app-jenkins-logo']")).click();
        getDriver().findElement(By.xpath("//a[@href='job/" + FOLDER_NAME + "/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/"+ FOLDER_NAME + "/newView']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(VIEW_NAME);
        getDriver().findElement(By.xpath("//label[@for='hudson.model.MyView']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//li[@aria-current='page']")).getText(),
                VIEW_NAME);
    }
}