package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;


public class ManageJenkinsPage2Test extends BaseTest {

    private final By systemConfigurationName = By.xpath("//h2[@class='jenkins-section__title' and contains(text(), 'System Configuration')]");
    private final By securityName = By.xpath("//h2[@class='jenkins-section__title' and contains(text(), 'Security')]");
    private final By statusInformationName = By.xpath("//h2[@class='jenkins-section__title' and contains(text(),'Status Information')]");
    private final By troubleshootingName = By.xpath("//h2[@class='jenkins-section__title' and contains(text(),'Troubleshooting')]");
    private final By toolsAndActionsName = By.xpath("//h2[@class='jenkins-section__title' and contains(text(),'Tools and Actions')]");
    private final By moduleActionBtn = By.xpath("//div[@class='jenkins-section__item']");
    private final By pageByNameOpenedHeaderLocator = By.xpath(
            "//div[contains(@class,'jenkins-app-bar__content')] | " +
                    "//h1[contains(@class, 'jenkins-app-bar__title')] | " +
                    "//div[contains(@class, 'jenkins-breadcrumbs__list-item')] | " +
                    "//h1 | " +
                    "//h2");

    @Test(description = "Проверка страницы Manage Jenkins")
    public void testManageJenkinsModules() {

        getDriver().findElement(By.id("root-action-ManageJenkinsAction")).click();

        assertSubModulesName("System Configuration",
                "Security", "Status Information",
                "Troubleshooting", "Tools and Actions");

        clickOnActionBtn("System");
        verifyPageUrl("System");
        checkPageByNameOpened("System");
        getDriver().navigate().back();

        clickOnActionBtn("Tools");
        verifyPageUrl("Tools");
        checkPageByNameOpened("Tools");
        getDriver().navigate().back();

        clickOnActionBtn("Plugins");
        verifyPageUrl("Plugins");
        checkPageByNameOpened("Plugins");
        getDriver().navigate().back();

        clickOnActionBtn("Nodes");
        verifyPageUrl("Nodes");
        checkPageByNameOpened("Nodes");
        getDriver().navigate().back();

        clickOnActionBtn("Clouds");
        verifyPageUrl("Clouds");
        checkPageByNameOpened("Clouds");
        getDriver().navigate().back();
    }

    public void assertSubModulesName(String systemConfigName, String securityNameText,
                                     String statusInfoName, String troubleshootingNameText,
                                     String toolsActionsName) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement systemModule = getDriver().findElement(systemConfigurationName);
        wait.until(ExpectedConditions.visibilityOf(systemModule));
        assert systemModule.getText().contains(systemConfigName);

        WebElement securityModule = getDriver().findElement(securityName);
        wait.until(ExpectedConditions.visibilityOf(securityModule));
        assert securityModule.getText().contains(securityNameText);

        WebElement statusModule = getDriver().findElement(statusInformationName);
        wait.until(ExpectedConditions.visibilityOf(statusModule));
        assert statusModule.getText().contains(statusInfoName);

        WebElement troubleshootingModule = getDriver().findElement(troubleshootingName);
        wait.until(ExpectedConditions.visibilityOf(troubleshootingModule));
        assert troubleshootingModule.getText().contains(troubleshootingNameText);

        WebElement toolsModule = getDriver().findElement(toolsAndActionsName);
        wait.until(ExpectedConditions.visibilityOf(toolsModule));
        assert toolsModule.getText().contains(toolsActionsName);
    }

    public void clickOnActionBtn(String actionNameBtn) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        List<WebElement> actionButtons = getDriver().findElements(moduleActionBtn);

        wait.until(ExpectedConditions.presenceOfElementLocated(moduleActionBtn));

        for (WebElement btn : actionButtons) {
            if (btn.getText().contains(actionNameBtn)) {
                btn.click();
                return;
            }
        }
    }

    public void verifyPageUrl(String actionNameBtn) {
        ((org.openqa.selenium.JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, 0);");

        String expectedUrl = switch (actionNameBtn) {
            case "System" -> "configure";
            case "Tools" -> "Tools";
            case "Plugins" -> "pluginManager";
            case "Nodes" -> "computer";
            case "Clouds" -> "cloud";
            case "Appearance" -> "appearance";
            default -> throw new IllegalArgumentException("Неизвестное действие: " + actionNameBtn);
        };

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(expectedUrl));
    }

    public void checkPageByNameOpened(String pageName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        List<WebElement> headers = getDriver().findElements(pageByNameOpenedHeaderLocator);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pageByNameOpenedHeaderLocator));

        for (WebElement header : headers) {
            if (header.getText().contains(pageName)) {
                return;
            }
        }
    }
}

