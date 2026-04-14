package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class UserProfileTest extends BaseTest {

    String userName = ProjectUtils.getUserName();

    public void openUserProfile() {
        getDriver().findElement(By.id("root-action-UserAction")).click();
    }

    @Test
    public void testOpenUserProfile() {

        openUserProfile();

        Assert.assertTrue(
                getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-panel']")))
                        .getText().contains("Jenkins User ID: " + userName));

    }
}
