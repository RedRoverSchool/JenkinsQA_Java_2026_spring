package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.HomePage;

import java.util.List;

public class NewItemTest extends BaseTest {

    private static final String JOB_NAME = "existing_job_01";

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
                .getErrorInvalidText();

        Assert.assertEquals(actualError, "» A job already exists with the name ‘existing_job_01’");
    }

    @Test
    public void testEmptyName() {
        String actualError = new HomePage(getDriver())
                .goHomePage()
                .clickItemNewJob()
                .clickOutside()
                .getErrorEmptyText();

        Assert.assertEquals(actualError, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testInvalidName() {
        CreateProjectPage newItemPage = new HomePage(getDriver())
                .goHomePage()
                .clickItemNewJob()
                .setProjectName("$");

        Assert.assertEquals(newItemPage.getErrorInvalidText(), "» ‘$’ is an unsafe character");
        Assert.assertFalse(newItemPage.isOkButtonEnabled(), "The OK button should be inactive");
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
