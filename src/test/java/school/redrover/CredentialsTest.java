package school.redrover;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.manage.CredentialsPage;

import java.util.List;
import java.util.UUID;

public class CredentialsTest extends BaseTest {

    private String credentialId;
    private String desc;

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
        credentialId = "test-" + uniqueId;
        String user = "user-" + uniqueId;
        String pass = "pass-" + uniqueId;
        desc = "Test Description " + uniqueId;

        SoftAssert softAssert = new SoftAssert();

        CredentialsPage credentialsPage = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .createUsernameWithPassword(user, pass, credentialId, desc)
                .clickCreateButton();

        softAssert.assertTrue(credentialsPage.isCredentialVisible(credentialId),
                "Username with ID %s is not found!".formatted(credentialId));

        softAssert.assertEquals(credentialsPage.getCredentialDescription(credentialId),desc);
        softAssert.assertTrue(credentialsPage.isCredentialTagsVisible(credentialId),
                "Login and password mask line  '%s' not found!".formatted(credentialId));
        softAssert.assertAll();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description()
    @Test(dependsOnMethods ="testCreateUsernamePasswordCredential")
    public void testUpdateCredentials() {

        String newUsername = "newUsername";
        SoftAssert softAssert = new SoftAssert();

        CredentialsPage credentialsPage = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickUpdateCredentialButton(credentialId)
                .updateUsername(newUsername)
//                .clickChangePasswordButton()
//                .UpdateDescriprion()
                .clickSaveButton();

        softAssert.assertTrue(credentialsPage.isCredentialVisible(credentialId),
                "Username with ID %s is not found!".formatted(credentialId));

//        softAssert.assertEquals(credentialsPage.getCredentialDescription(credentialId),desc);
        softAssert.assertTrue(credentialsPage.isCredentialTagsVisible(credentialId),
                "Login and password mask line  '%s' not found!".formatted(credentialId));

        softAssert.assertAll();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Username with password could be deleted")
    @Test(dependsOnMethods = "testCreateUsernamePasswordCredential")
    public void testDeleteCredentials() {

        boolean isDeleted = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickDeleteCredential(credentialId)
                .isCredentialDeleted(credentialId);

        Assert.assertTrue(isDeleted,
                "Username with ID %s is still found!".formatted(credentialId));
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

        String uniqueId = UUID.randomUUID().toString();
        credentialId = "test-" + uniqueId;

        boolean isCredentialsCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretTextButton()
                .clickNextButton()
                .typeSecretText("my-secret")
                .typeID(credentialId)
                .clickCreateButton()
                .isCredentialVisible(credentialId);

        Assert.assertTrue(isCredentialsCreated);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify secret file is created")
    @Test
    public void testAddSecretFile() {

        String uniqueId = UUID.randomUUID().toString();
        credentialId = "test-" + uniqueId;

                boolean isCredentialsCreated = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretFileButton()
                .addSecretFile()
                .typeID(credentialId)
                .typeDescription("Desc")
                .clickCreateButton()
                .isCredentialVisible(credentialId);

        Assert.assertTrue(isCredentialsCreated);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify error message via attempting to create a Secret file credential without uploading a file")
    @Test
    public void testAddEmptySecretFile() {

        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        credentialId = "test-" + uniqueId;

        boolean isValidationErrorDisplayed = new HomePage(getDriver())
                .clickManageButton()
                .clickCredentials()
                .clickAddCredentialsButton()
                .clickSecretFileButton()
                .typeID(credentialId)
                .typeDescription("Desc")
                .clickCreateButton()
                .isErrorMessageVisible();

        Assert.assertTrue(isValidationErrorDisplayed,"Credentials creation failed");
    }
}
