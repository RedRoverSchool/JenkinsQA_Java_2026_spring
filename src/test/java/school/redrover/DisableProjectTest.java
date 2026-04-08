package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class DisableProjectTest extends BaseTest {
    @Test
    public void testDisableProject(){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("Freestyle");
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//*[@id='toggle-switch-enable-disable-project']/label")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//*[@id='enable-project']/button")).isEnabled(),
                "The button 'Turn on' must be active");
    }
}
