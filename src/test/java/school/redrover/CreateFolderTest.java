package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateFolderTest extends BaseTest {


    @Test
    public void createFolderTest() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"))
                .click();


        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys("TestFolder");
        getDriver().findElement(By.xpath(
                        "//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]"))
                .click();

        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//button[@value='Save']"))
                .click();

        assertEquals(
                getDriver().findElement(By.className("job-index-headline")).getText(),
                "TestFolder");

    }

    @Test
    public void createNestedFolderTest(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']"))
                .click();


        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys("ParentFolder");
        getDriver().findElement(By.xpath(
                        "//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]"))
                .click();

        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//button[@value='Save']"))
                .click();


        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']"))
                .sendKeys("ChildFolder");
        getDriver().findElement(By.xpath(
                        "//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]"))
                .click();
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        getDriver().findElement(By.xpath("//button[@value='Save']"))
                .click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='main-panel' and contains(text(), 'Full folder name: ParentFolder/ChildFolder')]")).getText().contains("ParentFolder/ChildFolder"));

    }


}
