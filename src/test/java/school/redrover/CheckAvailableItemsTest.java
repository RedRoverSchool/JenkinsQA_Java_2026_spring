package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class CheckAvailableItemsTest extends BaseTest {

    @Test
    public void testCheckAvailableItems(){

        List<String> expectedItems = new ArrayList<>(List.of("Freestyle project", "Pipeline", "Multi-configuration project",
                "Folder", "Multibranch Pipeline", "Organization Folder"));
        List<String> actualItems = new ArrayList<>();

        getDriver().findElement(By.xpath("//span[text()='Create a job']")).click();
        List<WebElement> actualWebItems = getDriver().findElements(By.xpath("//div[@id='items']//span[@class='label']"));

        for (WebElement i : actualWebItems) {
            actualItems.add(i.getText());
        }

        expectedItems.sort(String::compareTo);
        actualItems.sort(String::compareTo);

        Assert.assertEquals(actualItems, expectedItems);
    }
}
