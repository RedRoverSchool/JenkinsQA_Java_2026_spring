package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ManageJenkinsPageTest extends BaseTest {

    private final List<String> expectedItems = List.of("System", "Tools", "Plugins", "Nodes", "Clouds",
            "Appearance", "Security", "Credentials", "Credential Providers", "Users", "System Information",
            "System Log", "Load Statistics", "About Jenkins", "Manage Old Data", "Reload Configuration from Disk",
            "Jenkins CLI", "Script Console", "Prepare for Shutdown"
    );

    @Test
    public void testManageJenkinsPageItems() {
        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();

        List<WebElement> items = getDriver().findElements(By.xpath("//div[@class='jenkins-section__item']/a/dl/dt"));

        List<String> actualItems = new ArrayList<>();
        for (WebElement item : items) {
            actualItems.add(item.getText());
        }

        Assert.assertEquals(actualItems, expectedItems);
    }

}
