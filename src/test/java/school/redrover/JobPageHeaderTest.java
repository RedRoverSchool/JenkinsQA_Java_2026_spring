package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;


public class JobPageHeaderTest extends BaseTest {

    @Test
    public void testVerifyNewItemPageHeader() {

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        WebElement element = getDriver().findElement(By.xpath("//*[@id='add-item-panel']/h1"));
        String actualText = element.getText();
        String expecytedString = "New Item";

        Assert.assertEquals(actualText, expecytedString, "Текст не совпадает");

    }

}
