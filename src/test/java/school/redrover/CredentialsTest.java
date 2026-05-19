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
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
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
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .createUsernameWithPassword(user, pass, id, desc)
                .clickCreateButton()
                .isCredentialVisible(id);

        Assert.assertTrue(isCreated,"Username with ID " + id + " is not found!");
    }

    @Test(dependsOnMethods = "testCreateUsernamePasswordCredential")
    public void testDeleteCredentials() {

        boolean isDeleted = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickDeleteCredential(id)
                .isCredentialDeleted(id);

        Assert.assertTrue(isDeleted,
                "Username with ID " + id + " is still found!");
    }

    @Test
    public void testAddSshUsernameWithKey(){
        new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSSHCredentialsButton()
                .setSSHCredentials("prod-deploy-key","SSH key for prod server","deploy",true,true,"MyStr0ngP@ss");
    }

    @Test
    public void addSecretTextCredentials() {
        boolean isCredentialsCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretTextButton()
                .clickNextButton()
                .typeSecretText("my-secret")
                .typeID("test-id")
                .clickCreateButton()
                .isCredentialVisible("test-id");

        Assert.assertTrue(isCredentialsCreated);
    }
}