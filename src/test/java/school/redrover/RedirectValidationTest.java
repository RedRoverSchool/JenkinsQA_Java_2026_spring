package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class RedirectValidationTest extends BaseTest {
    @Test
    public void testCheckRedirect() {
        getDriver().findElement(By.xpath("//*[@class='task '][1]")).click();
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//h1")).getText(),
                "New Item");
    }

    @Ignore
    @Test
    public void testCheckMessageEnabled() {
        getDriver().findElement(By.xpath("//*[@class='task '][1]")).click();
        getDriver().findElement(By.xpath("//h1")).click();
        WebElement validation = getDriver().findElement(By.xpath("//div[@id='itemname-required']"));
        Assert.assertFalse(
                validation.getAttribute("class").contains("input-message-disabled")
        );
    }
    @Ignore
    @Test
    public void testCheckMessageDisabled() {
        getDriver().findElement(By.xpath("//*[@class='task '][1]")).click();
        getDriver().findElement(By.xpath("//h1")).click();
        WebElement validation = getDriver().findElement(By.xpath("//div[@id='itemname-required']"));
        WebElement element = getDriver().findElement(By.xpath("//input[@id='name']"));
        element.sendKeys("Абракадабра");
        Assert.assertTrue(
                validation.getAttribute("class").
                        contains("input-message-disabled")
        );
    }
}
