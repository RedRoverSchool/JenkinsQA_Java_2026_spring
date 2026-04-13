package school.redrover;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class UserProfileTest extends BaseTest {

    public void openUserProfile() {
        getDriver().findElement(By.xpath("//a[@href='/user/admin']")).click();
    }

    @Test
    public void testOpenUserProfile() {
        openUserProfile();

        String userName = ProjectUtils.getUserName();

        Assert.assertTrue(
                getDriver().findElement(By.xpath("//*[@id='main-panel']"))
                        .getText().contains("Jenkins User ID: " + userName));
    }
}
