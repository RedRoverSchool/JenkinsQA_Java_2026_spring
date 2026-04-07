package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

public class BuildExecutorStatusTest extends BaseTest {

    @Test
    public void testBuildExecutorStatus() {

        getDriver().findElement(By.xpath("//a[@href='/computer/']")).click();
        WebElement title = getDriver().findElement(By.xpath("//h1"));

        Assert.assertEquals(title.getText(), "Nodes");
    }
}
