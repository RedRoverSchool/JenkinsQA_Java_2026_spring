package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;

import java.util.List;

public class NewItemTest extends BaseTest {

    private static final String JOB_NAME = "existing_job_01";
    private static final String ERROR_MESSAGE = "» A job already exists with the name ‘existing_job_01’";

    @Test
    public void testValidName() {
        List<String> projectNames = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(JOB_NAME)
                .selectPipelineProjectAndClickOk()
                .goHomePage()
                .getProjectList();

        Assert.assertEquals(projectNames.size(), 1);
        Assert.assertEquals(projectNames.getFirst(), JOB_NAME);
    }

    @Test(dependsOnMethods = "testValidName")
    public void testDuplicateName() {
        String actualError = new HomePage(getDriver())
                .goHomePage()
                .clickItemNewJob()
                .setProjectName(JOB_NAME)
                .selectPipelineProject()
                .getErrorText();

        Assert.assertEquals(actualError, "» A job already exists with the name ‘existing_job_01’");
    }

    @Test
    public void testSelectAnItemType() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys("Select an item type test");
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertTrue(getDriver().findElement(By.id("ok-button")).isEnabled());
    }

    @Test
    public void testSelectItemTypeWithEmptyName() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.xpath("//div[contains(text(), 'Build, test')]")).click();

        Assert.assertEquals(getDriver().findElement(By.id("itemname-required")).getText(),
                "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testSelectItemTypeWithInvalidName() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.linkText("New Item"))).click();
        getDriver().findElement(By.id("name")).sendKeys("$");

        Assert.assertFalse(getDriver().findElement(By.id("ok-button")).isEnabled());
    }
    @DataProvider(name = "invalid characters")
    public Object[][] getData() {
            return new Object[][]{{"@"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"},{"!"}
        };
    }
    @Test(dataProvider = "invalid characters")
    public void testInvalidCharactersInName(String invalidCharacter) {
        String invalidProjectName = "test" + invalidCharacter;

        String errorMessage = new HomePage(getDriver())
                .clickItemNewJob()
                .setProjectName(invalidProjectName)
                .selectPipelineProject()
                .getErrorInvalidText();

        Assert.assertEquals(errorMessage, "» ‘" + invalidCharacter + "’ is an unsafe character");
    }
}
