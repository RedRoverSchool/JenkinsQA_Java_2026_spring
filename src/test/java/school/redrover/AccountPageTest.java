package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;

public class AccountPageTest extends BaseTest {

    @Test
    public void testGetAccountPagePath1() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        getDriver().findElement(By.id("root-action-UserAction")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/user/user/account']"))).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='main-panel']//h1")).getText().trim(),
                "Account"
        );
    }

}
