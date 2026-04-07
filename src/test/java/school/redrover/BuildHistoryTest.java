package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.List;

public class BuildHistoryTest extends BaseTest {
    public void openBuildHistory() {
        getDriver().findElement(By.cssSelector("a[href='/view/all/builds']")).click();
    }

    @Test
    public void testEmptyBuildHistory() {
        openBuildHistory();
        List<WebElement> rows= getDriver().findElement(By.id("projectStatus")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        Assert.assertEquals(0, rows.size(), "Table should be empty");
    }
}
