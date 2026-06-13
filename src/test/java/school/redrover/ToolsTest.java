package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.manage.ToolsPage;
import java.util.List;

public class ToolsTest extends BaseTest {

    @Test
    public void testAddJDK() {
        boolean isEditButtonAppears = new HomePage(getDriver())
                .clickManageButton()
                .clickToolsButton()
                .clickAddJDKButton()
                .setJDKName("TestName")
                .setJavaPath("/test/path/toJDK")
                .clickSaveButton()
                .clickManageButton()
                .clickToolsButton()
                .isEditDisplayed();

        Assert.assertTrue(isEditButtonAppears);
    }
}
