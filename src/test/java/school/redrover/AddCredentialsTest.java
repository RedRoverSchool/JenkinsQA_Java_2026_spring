package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class AddCredentialsTest extends BaseTest {
    @Test
    public void testAddCredentialsButtonActive(){
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();
        getDriver().findElement(By.xpath("//a[@href='credentials']")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//div[@id='main-panel']//h1")).getText(),
                "Credentials");
        Assert.assertTrue(getDriver().findElement(By.xpath("//button[contains(text(),'Add')]"))
                        .isEnabled(), "'Add credentials' button is active!");
    }
}
