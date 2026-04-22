package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class MultiConfigurationProjectTest extends BaseTest {

    @Test
    public void testMultiConfigurationProject() {

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tasks']/div[1]/span/a")));

        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("test");
        getDriver().findElement(By.xpath("//li[@class='hudson_matrix_MatrixProject']")).click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.id("ok-button")));

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
}
