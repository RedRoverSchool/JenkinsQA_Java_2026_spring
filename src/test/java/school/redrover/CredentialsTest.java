package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

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
        List<String> credentialList= new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSSHCredentialsButton()
                .setSSHCredentials("test-cred","SSH key for prod server","deploy",true,true," RSA/Ed25519 key","MyStr0ngP@ss")
                .clickCreateButton()
                .getCredentialList();

        //Assert.assertEquals(credentialList.size(), 1); нужно реализовать удаление креденшена для этой строчки
        Assert.assertEquals(credentialList.getLast(), "test-cred");
    }

    @Ignore
    @Test(dependsOnMethods = "testAddSshUsernameWithKey")
    public void testDeleteCredentialSshUser() {

        boolean isDeleted = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickDeleteCredential("prod-deploy-key")
                .isCredentialDeleted("prod-deploy-key");

        Assert.assertTrue(isDeleted,
                "Username with ID " + id + " is still found!");
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
                .typeID("test-Id")
                .clickCreateButton()
                .isCredentialVisible("test-Id");

        Assert.assertTrue(isCredentialsCreated);
    }
}