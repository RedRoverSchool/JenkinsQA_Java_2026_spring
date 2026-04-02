package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FreestyleProjectTest extends BaseTest {

    @Test
    public void testCreateFreestyleProject() {
        String testProjectName = "test";

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(testProjectName);
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//a[@class='app-jenkins-logo']")).click();

        Assert.assertEquals(getDriver().findElement(
                By.xpath("//*[@class='jenkins-table__link model-link inside']")).getText(),
                testProjectName);
    }
}
