package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.TestUtils;
import school.redrover.page.HomePage;
import java.util.ArrayList;
import java.util.List;
import static school.redrover.common.TestUtils.JobType.FREESTYLE;

public class BuildHistoryTest extends BaseTest {

    private final static String PROJECT_NAME = "NewProject";

    @Test
    public void testEmptyBuild(){
        new HomePage(getDriver()).clickBuildHistory();
        List<String> buildHistoryList = new ArrayList<>();

        try {
            for (WebElement webElement
                    : getWait5().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='jenkins-table__link model-link']")))) {
                buildHistoryList.add(webElement.getText());
            }
        } catch (Exception e) {}

        Assert.assertEquals(buildHistoryList.size(), 0);
    }

    @Test
    public void testDeleteWarningMessage(){
        String warningMessage = TestUtils.createJob(getDriver(), PROJECT_NAME, FREESTYLE)
                .clickScheduleBuild(PROJECT_NAME)
                .clickBuildHistory()
                .clickDropDownMenu(PROJECT_NAME)
                .clickDeleteBuild()
                .getWarningMessage();

        Assert.assertTrue(warningMessage.contains("Delete the build"));
    }
}