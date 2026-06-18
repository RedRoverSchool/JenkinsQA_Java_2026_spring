package school.redrover.page.plugin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AvailablePluginsPage extends PluginPage {

    @FindBy(xpath = "//input[@placeholder='Search available plugins']")
    private WebElement inputSearchAvailablePlugins;

    @FindBy(xpath = "//button[@id='button-install']")
    private WebElement installButton;

    AvailablePluginsPage (WebDriver driver) {
        super(driver);
    }

    public DownloadProgressPage clickInstallButton() {
        installButton.click();

        return new DownloadProgressPage(getDriver());
    }

    public AvailablePluginsPage setSearchPluginName(String pluginName) {
        inputSearchAvailablePlugins.sendKeys(pluginName);
        return this;
    }

    public AvailablePluginsPage selectSearchResult(String pluginName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[.//a[@class='jenkins-table__link' and contains(text(), '" + pluginName + "')]]//label"))).click();
        return this;
    }
}
