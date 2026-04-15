package school.redrover;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.ProjectUtils;

public class UserProfileTest extends BaseTest {

    private static final By PROFILE_BUTTON = By.id("root-action-UserAction");



    public void openUserProfile() {
        getDriver().findElement(PROFILE_BUTTON).click();
    }



    @Test
    public void testUserProfileButtonVisible() {
        Assert.assertTrue(getDriver().findElement(PROFILE_BUTTON).isDisplayed());
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
