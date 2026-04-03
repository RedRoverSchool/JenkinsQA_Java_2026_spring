package school.redrover;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


import static org.testng.Assert.assertEquals;

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
}
