package school.redrover.page.plugin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class PluginPage extends BasePage {

    @FindBy(xpath = "//a[@href='/manage/pluginManager/available']")
    private WebElement availablePluginsButton;

    @FindBy(xpath = "//a[@href='/manage/pluginManager/installed']")
    private WebElement installedPluginsButton;

    public PluginPage(WebDriver driver) {
        super(driver);
    }

    public AvailablePluginsPage clickAvailablePluginsButton() {
        availablePluginsButton.click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search available plugins']")));

        return new AvailablePluginsPage(getDriver());
    }

    public InstalledPluginsPage clickInstalledPluginsButton() {
        installedPluginsButton.click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search installed plugins']")));

        return new InstalledPluginsPage(getDriver());
    }


}
