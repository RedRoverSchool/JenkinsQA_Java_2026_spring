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
import java.util.UUID;

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

        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        id = "test-" + uniqueId;
        String user = "user-" + uniqueId;
        String pass = "pass-" + uniqueId;
        String desc = "Test Description " + uniqueId;

        boolean isCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .createUsernameWithPassword(user, pass, id, desc)
                .clickCreateButton()
                .isCredentialVisible(id);

        Assert.assertTrue(isCreated,"Username with ID %s is not found!".formatted(id));
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
                "Username with ID %s is still found!".formatted(id));
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
    public void testAddSecretTextCredentials() {

        String uniqueId = "test-" + UUID.randomUUID().toString();
        id = "test-" + uniqueId;

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

        String uniqueId = "test-" + UUID.randomUUID().toString();
        id = "test-" + uniqueId;

                boolean isCredentialsCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretFileButton()
                .addSecretFile()
                .typeID(id)
                .typeDescription("Desc")
                .clickCreateButton()
                .isCredentialVisible(id);

        Assert.assertTrue(isCredentialsCreated);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify error message via attempting to create a Secret file credential without uploading a file")
    @Test
    public void testAddEmptySecretFile() {

        String uniqueId = "test-" + UUID.randomUUID().toString().substring(0, 8);
        id = uniqueId;

        boolean isValidationErrorDisplayed = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretFileButton()
                .typeID(id)
                .typeDescription("Desc")
                .clickCreateButton()
                .isErrorMessageVisible();

        Assert.assertTrue(isValidationErrorDisplayed,"Credentials creation failed");
    }
}