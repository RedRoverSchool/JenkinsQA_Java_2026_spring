package school.redrover;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.AddCredentialsPage;
import school.redrover.page.HomePage;

import java.time.format.DecimalStyle;
import java.util.List;

public class CredentialsTest extends BaseTest {

    private String id;

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify modal window 'Add Credentials' is displayed")
    @Test
    public void testAddCredentialsDialogOpen() {

        String dialogTitle = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .getDialogTitle();

        Assert.assertEquals(dialogTitle, "Add Credentials");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Username with password is created")
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Username with password could be deleted")
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify SSH Username with private key is created")
    @Test
    public void testAddSshUsernameWithKey(){

        List<String> credentialList= new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSSHCredentialsButton()
                .setSSHCredentials("test-cred","SSH key for prod server","deploy",true,true," RSA/Ed25519 key","MyStr0ngP@ss")
                .clickCreateButton()
                .getCredentialList("test-cred");

        Assert.assertEquals(credentialList.getFirst(), "test-cred");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify secret test is created")
    @Test
    public void addSecretTextCredentials() {

        long timestamp = System.currentTimeMillis();
        id = "test-" + timestamp;

        boolean isCredentialsCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretTextButton()
                .clickNextButton()
                .typeSecretText("my-secret")
                .typeID(id)
                .clickCreateButton()
                .isCredentialVisible(id);

        Assert.assertTrue(isCredentialsCreated);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify secret file is created")
    @Test
    public void testAddSecretFile() {

        long timestamp = System.currentTimeMillis();
        id = "test-" + timestamp;

                boolean isCredentialsCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretFileButton()
                .addSecretFile(id, "desc")
                .clickCreateButton()
                .isCredentialVisible(id);

        Assert.assertTrue(isCredentialsCreated);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify error message via attempting to create a Secret file credential without uploading a file")
    @Test
    public void testAddEmptySecretFile() {

        long timestamp = System.currentTimeMillis();
        id = "test-" + timestamp;

        boolean isValidationErrorDisplayed = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretFileButton()
                .doNotAddSecretFile(id,"Empty file")
                .clickCreateButton()
                //one more step
                .isErrorMessageVisible();

        Assert.assertTrue(isValidationErrorDisplayed,"Credentials creation failed");
    }
}