package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.ArrayList;
import java.util.List;

public class BuildHistoryTest extends BaseTest {

    @Ignore
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
}
