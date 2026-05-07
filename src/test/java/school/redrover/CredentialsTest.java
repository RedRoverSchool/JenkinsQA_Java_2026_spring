package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class CredentialsTest extends BaseTest {

    private String id;

    @Test
    public void testAddCredentialsDialogOpen() {

        String dialogTitle = new HomePage(getDriver())
                .clickManageJenkins()
                .clickCredentialsButton()
                .openAddCredentialsDialog()
                .getDialogTitle();

        Assert.assertEquals(dialogTitle, "Add Credentials");
    }

    @Test
    public void testCreateUsernamePasswordCredential() {

        long timestamp = System.currentTimeMillis();
        id = "id" + timestamp;
        String user = "user-" + timestamp;
        String pass = "pass-" + timestamp;
        String desc = "Test Description " + timestamp;

        boolean isCreated = new HomePage(getDriver())
                .clickManageJenkins()
                .clickCredentialsButton()
                .openAddCredentialsDialog()
                .createUsernameWithPassword(user, pass, id, desc)
                .isCredentialVisible(id);

        Assert.assertTrue(isCreated,"Username with ID " + id + " is not found!");
    }

    @Test(dependsOnMethods = "testCreateUsernamePasswordCredential")
    public void testDeleteCredentials() {

        boolean isDeleted = new HomePage(getDriver())
                .clickManageJenkins()
                .clickCredentialsButton()
                .deleteCredentialAction(id)
                .isCredentialDeleted(id);

        Assert.assertTrue(isDeleted,
                "Username with ID " + id + " is still found!");
    }
}