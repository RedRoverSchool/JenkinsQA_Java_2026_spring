package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class CredentialsTest extends BaseTest {

    @Ignore
    @Test
    public void testAddCredentialsDialogOpen() {

        String dialogTitle = new HomePage(getDriver())
                .clickManageJenkins()
                .goToCredentials()
                .openAddCredentialsDialog()
                .getDialogTitle();

        Assert.assertEquals(dialogTitle, "Add Credentials");
    }

    @Ignore
    @Test
    public void testCreateUsernamePasswordCredential() {

        long timestamp = System.currentTimeMillis();

        String id = "id-" + timestamp;
        String user = "user-" + timestamp;
        String pass = "pass-" + timestamp;
        String desc = "Test Description " + timestamp;

        boolean isCreated = new HomePage(getDriver())
                .clickManageJenkins()
                .goToCredentials()
                .openAddCredentialsDialog()
                .createUsernameWithPassword(user, pass, id, desc)
                .isCredentialVisible(id);

        Assert.assertTrue(isCreated,"Username with ID " + id + " is not found!");
    }
}