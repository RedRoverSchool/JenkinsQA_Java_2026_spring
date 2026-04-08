package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;


public class ManageJenkinsPage2Test extends BaseTest {

    private static final By SYSTEM_CONFIGURATION_NAME = By.xpath("//h2[@class='jenkins-section__title' and contains(text(), 'System Configuration')]");
    private static final By SECURITY_NAME = By.xpath("//h2[@class='jenkins-section__title' and contains(text(), 'Security')]");
    private static final By STATUS_INFORMATION_NAME = By.xpath("//h2[@class='jenkins-section__title' and contains(text(),'Status Information')]");
    private static final By TROUBLESHOOTING_NAME = By.xpath("//h2[@class='jenkins-section__title' and contains(text(),'Troubleshooting')]");
    private static final By TOOLS_AND_ACTIONS_NAME = By.xpath("//h2[@class='jenkins-section__title' and contains(text(),'Tools and Actions')]");
    private static final By MODULE_ACTION_BTN = By.xpath("//div[@class='jenkins-section__item']");
    private static final By PAGE_BY_NAME_OPENED_HEADER_LOCATOR = By.xpath(
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

    private void  assertSubModulesName(String systemConfigName, String securityNameText,
                                     String statusInfoName, String troubleshootingNameText,
                                     String toolsActionsName) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement systemModule = getDriver().findElement(SYSTEM_CONFIGURATION_NAME);
        wait.until(ExpectedConditions.visibilityOf(systemModule));
        assert systemModule.getText().contains(systemConfigName);

        WebElement securityModule = getDriver().findElement(SECURITY_NAME);
        wait.until(ExpectedConditions.visibilityOf(securityModule));
        assert securityModule.getText().contains(securityNameText);

        WebElement statusModule = getDriver().findElement(STATUS_INFORMATION_NAME);
        wait.until(ExpectedConditions.visibilityOf(statusModule));
        assert statusModule.getText().contains(statusInfoName);

        WebElement troubleshootingModule = getDriver().findElement(TROUBLESHOOTING_NAME);
        wait.until(ExpectedConditions.visibilityOf(troubleshootingModule));
        assert troubleshootingModule.getText().contains(troubleshootingNameText);

        WebElement toolsModule = getDriver().findElement(TOOLS_AND_ACTIONS_NAME);
        wait.until(ExpectedConditions.visibilityOf(toolsModule));
        assert toolsModule.getText().contains(toolsActionsName);
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }
    private void  clickOnActionBtn(String actionNameBtn) {
        WebDriverWait wait = getWait();
        List<WebElement> actionButtons = getDriver().findElements(MODULE_ACTION_BTN);

        wait.until(ExpectedConditions.presenceOfElementLocated(MODULE_ACTION_BTN));

        for (WebElement btn : actionButtons) {
            if (btn.getText().contains(actionNameBtn)) {
                btn.click();
                return;
            }
        }
    }

    private void  verifyPageUrl(String actionNameBtn) {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, 0);");

        String expectedUrl = switch (actionNameBtn) {
            case "System" -> "configure";
            case "Tools" -> "Tools";
            case "Plugins" -> "pluginManager";
            case "Nodes" -> "computer";
            case "Clouds" -> "cloud";
            case "Appearance" -> "appearance";
            default -> throw new IllegalArgumentException("Неизвестное действие: " + actionNameBtn);
        };

        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.urlContains(expectedUrl));
    }

    private void  checkPageByNameOpened(String pageName) {
        WebDriverWait wait = getWait();
        List<WebElement> headers = getDriver().findElements(PAGE_BY_NAME_OPENED_HEADER_LOCATOR);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PAGE_BY_NAME_OPENED_HEADER_LOCATOR));

        for (WebElement header : headers) {
            if (header.getText().contains(pageName)) {
                return;
            }
        }
    }
}

