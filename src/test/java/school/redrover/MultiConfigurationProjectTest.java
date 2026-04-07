package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class MultiConfigurationProjectTest extends BaseTest {

    @Test

    public void testMultiConfigurationProject () {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tasks']/div[1]/span/a")));

        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("test");
        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();

        WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button")));

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

}
