package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class ItemNameBlankTest extends BaseTest {

    @Test
    public void testItemNameBlank(){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).click();
        getDriver().findElement(By.xpath("/html")).click();

        WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-required")));

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");
    }
}
