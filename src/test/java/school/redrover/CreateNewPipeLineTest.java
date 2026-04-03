package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class CreateNewPipeLineTest extends BaseTest {
    @Test
    public void CreateNewPipeLine() {

        getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]//a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']"))
                .sendKeys("FirstTestPipeLine");
        getDriver().findElement(
                By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@id='main-panel']//textarea"))
                .sendKeys("Creation of new test pipeline");
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']//button[1]")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description-content']")).getText()
                , "Creation of new test pipeline");
    }
}
