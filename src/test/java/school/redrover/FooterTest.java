package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class FooterTest extends BaseTest {

    @Test
    public void testCheckingNavigationAPIPage() {

        getDriver().findElement(By.xpath("//a[@class='jenkins-button jenkins-button--tertiary rest-api']")).click();
        WebElement pageName = getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']"));

        Assert.assertEquals(pageName.getText(),"API");
    }
}
