package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class UserProfileTest extends BaseTest {

    public void openUserProfile() {
        getDriver().findElement(By.id("root-action-UserAction")).click();
    }

    @Test
    public void testOpenUserProfile() {

        openUserProfile();

        String actualText = getWait10()
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h1")))
                .getText();

        Assert.assertEquals(actualText, ProjectUtils.getUserName());

    }
}
