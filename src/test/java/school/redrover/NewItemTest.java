package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
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
                .getErrorText();

        Assert.assertEquals(actualError, "» A job already exists with the name ‘existing_job_01’");
    }

    @Test
    public void testEmptyName() {
        String actualError = new HomePage(getDriver())
                .goHomePage()
                .clickItemNewJob()
                .getErrorText();

        Assert.assertTrue(actualError.contains("This field cannot be empty, please enter a valid name"),
                "Текст ошибки не совпадает. Ожидался текст о пустом поле, но получено: " + actualError);
    }

    @Test
    public void testInvalidName() {
        boolean isOkButtonEnabled = new HomePage(getDriver())
                .goHomePage()
                .clickItemNewJob()
                .setProjectName("$")
                .isOkButtonEnabled();

            Assert.assertFalse(isOkButtonEnabled, "Кнопка 'OK' должна быть заблокирована при вводе символа '$'");
    }
}
