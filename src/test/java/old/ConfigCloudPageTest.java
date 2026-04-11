package old;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ConfigCloudPageTest extends BaseTest {

    @Test
    public void testTextItemsPage() {
        final List<String> textExpected = List.of(
                "Clouds",
                "There is no plugin installed that supports clouds.",
                "Install a plugin",
                "Learn more about distributed builds"
        );

        List<String> textActual = new ArrayList<>();

        getDriver().findElement(By.xpath("//a[@href='cloud/']")).click();

        textActual.add(getDriver().findElement(By.xpath("//h1")).getText());
        textActual.add(getDriver().findElement(By.xpath("//p")).getText());
        textActual.add(getDriver().findElement(By.xpath("(//section//span)[1]")).getText());
        textActual.add(getDriver().findElement(By.xpath("(//section//span)[3]")).getText());

        Assert.assertEquals(textActual, textExpected);



    }




}
