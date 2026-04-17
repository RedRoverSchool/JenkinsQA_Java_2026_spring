package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import java.util.ArrayList;
import java.util.List;

public class CreateNewItemTest extends BaseTest {

    @Test
    public void testCheckItemTypesForSelect() {
        final List<String> expectedItemTypeList = List.of(
                "Pipeline",
                "Freestyle project",
                "Multi-configuration project",
                "Folder",
                "Multibranch Pipeline",
                "Organization Folder"
        );

        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        List<WebElement> itemTypeList = getDriver().findElements(By.xpath("//span[@class='label']"));

        List<String> actualItemTypeList = new ArrayList<>();
        Assert.assertNotEquals(itemTypeList.size(), 0);
        for (WebElement element : itemTypeList) {
            actualItemTypeList.add(element.getText());
        }

        Assert.assertEquals(expectedItemTypeList, actualItemTypeList);
    }

    @Test
    public void testCreateEmptyItem() {
        getDriver().findElement(By.xpath("//a[.//span[text()='New Item']]")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#itemname-required")).getText(),"» This field cannot be empty, please enter a valid name");
    }
}
