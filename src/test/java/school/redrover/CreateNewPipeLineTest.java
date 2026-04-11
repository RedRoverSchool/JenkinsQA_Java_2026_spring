package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;
@Ignore
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

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='page-header']/div[1]//a"))).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='job_FirstTestPipeLine']/td[3]/a"))
                        .getText()
                , "FirstTestPipeLine");
    }

    @Test
    public void CreateOneMorePipeLine() {
        List<String> pipelines = List.of("Second", "Third", "Fourth");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));

        getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]//a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']"))
                .sendKeys("FirstTestPipeLine");
        getDriver().findElement(
                By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@id='main-panel']//textarea"))
                .sendKeys("First test pipeline creation");
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']//button[1]")).click();

        for (String item : pipelines) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='page-header']/div[1]//a"))).click();
            getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]//a/span[1]")).click();
            getDriver().findElement(By.xpath("//*[@id='name']"))
                    .sendKeys(item + "TestPipeLine");
            getDriver().findElement(
                    By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
            getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
            getDriver().findElement(By.xpath("//*[@id='main-panel']//textarea"))
                    .sendKeys(item + " test pipeline creation");
            getDriver().findElement(By.xpath("//*[@id='bottom-sticker']//button[1]")).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='page-header']/div[1]//a"))).click();

            Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='job_" + item + "TestPipeLine']/td[3]/a"))
                            .getText()
                    , item + "TestPipeLine");
        }
    }
}