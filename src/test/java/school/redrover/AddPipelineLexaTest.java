package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddPipelineLexaTest extends BaseTest{

    @Test
    public void testNumberOne() {

        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]/div/section[1]/ul/li/a")).
                click();

        getDriver().findElement(By.id("name")).sendKeys("Lexa");
        getDriver().findElement(By.xpath("//*[@id=\"j-add-item-type-standalone-projects\"]/ul/li[1]")).
                click();
        getDriver().findElement(By.id("ok-button")).
                click();

        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/div[2]/div/div[2]/textarea")).
                sendKeys("Lexa");
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[1]/div[3]/div[1]/div/span/label")).
                click();
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[1]/div[3]/div[4]/div[2]/div/div/div[2]/div[3]/input")).
                sendKeys("42");
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[1]/div[3]/div[4]/div[2]/div/div/div[3]/div[3]/input")).
                sendKeys("67");
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/form/div[1]/section[2]/div[4]/div/div/div[2]/div[2]/div/div[1]/select/option[2]")).
                click();
        getDriver().findElement(By.xpath("//*[@id=\"bottom-sticker\"]/div/button[1]")).
                click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/div[1]/div[1]/h1")).getText(),
                "Lexa");
    }
}
