package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;

public class ManageJenkinsPageSystemButtonTest extends BaseTest {

    private static final By HOME_DIRECTORY_LOCATOR = By.xpath("//a[@helpurl='/help/system-config/homeDirectory.html']/..");
    private static final By SYSTEM_MESSAGE_LOCATOR = By.xpath("//textarea[@name='system_message']/preceding::div[2]");
    private static final By QUIET_PERIOD_LOCATOR = By.xpath("//div[a[contains(@helpurl, 'quietPeriod')]]");
    private static final By SCM_LOCATOR = By.xpath("//div[@nameref='rowSetStart18']/div[contains(@class, 'jenkins-form-label')]");

    private void goToSystemPage() {
        getDriver().findElement(By.xpath("//a[@href='/manage']")).click();
        getDriver().findElement(By.xpath("//a[@href='configure']")).click();
    }

    @Test
    public void testSystemButtonOpensConfigurationPage() {

        goToSystemPage();

        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.titleIs("System - Manage Jenkins - Jenkins"));

        Assert.assertEquals(getDriver().getTitle(), "System - Manage Jenkins - Jenkins");

    }

    @Test
    public void testGeneralSectionContainsConfigurations() {

        goToSystemPage();

        List<By> locators = List.of(
                HOME_DIRECTORY_LOCATOR,
                SYSTEM_MESSAGE_LOCATOR,
                QUIET_PERIOD_LOCATOR,
                SCM_LOCATOR
        );

        List<String> actualLabels = locators.stream()
                .map(locator -> getDriver().findElement(locator).getText()
                        .replace("?", "").trim())
                .toList();

        List<String> expectedLabels = List.of(
                "Home directory",
                "System Message",
                "Quiet period",
                "SCM checkout retry count"
        );

        Assert.assertEquals(actualLabels, expectedLabels);
    }
}
