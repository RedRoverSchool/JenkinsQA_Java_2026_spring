package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AccountPageTest extends BaseTest {

    @Test
    public void testGetAccountPagePath1() {
        getDriver().findElement(By.id("root-action-UserAction")).click();
        getDriver().findElement(By.xpath("//a[@href='/user/user/account']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='main-panel']//h1")).getText().trim(),
                "Account"
        );
    }

}
