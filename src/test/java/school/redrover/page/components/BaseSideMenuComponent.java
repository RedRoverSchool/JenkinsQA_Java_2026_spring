package school.redrover.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.RenameProjectPage;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.common.BasePage;
import school.redrover.page.view.config.ViewMyConfigPage;

public class BaseSideMenuComponent<T extends BasePage> extends BasePage {

    @FindBy(xpath = "//a[contains(@class, 'task-link')][.//span[text()='Status']]")
    private WebElement statusButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/configure')]")
    private WebElement configureButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@data-title, 'Delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@id='side-panel']//a[contains(@href, '/confirm-rename')]")
    private WebElement renameButton;

    @FindBy(xpath = "//a[contains(@href, '/configure') and .//span[normalize-space()='Edit View']]")
    private WebElement editViewButton;

    protected final T parentPage;

    public BaseSideMenuComponent(WebDriver driver, T parentPage) {
        super(driver);
        this.parentPage = parentPage;
    }

    public T clickStatus() {
        statusButton.click();
        return parentPage;
    }

    public HomePage clickDelete() {
        deleteButton.click();
        getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-link")));

        return new HomePage(getDriver());
    }

    public RenameProjectPage clickRename() {
        renameButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='Submit' and @value='Rename']")));

        return new RenameProjectPage(getDriver());
    }

    public <ConfigPage extends BaseConfigPage> ConfigPage clickConfigure(ConfigPage configPage) {
        configureButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));

        return configPage;
    }

    public ViewMyConfigPage clickEditView() {
      getWait5().until(ExpectedConditions.elementToBeClickable(editViewButton)).click();
      return new ViewMyConfigPage(getDriver());
    }
}
