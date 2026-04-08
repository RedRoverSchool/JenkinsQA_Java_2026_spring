package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

@Ignore
public class DescriptionTest extends BaseTest {

    @Test
    public void testDescription () {
        String text = "hi";

        getDriver().findElement(By.id("description-link")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(text);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='description-content']")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description-content']")).getText(),text);
    }
}
