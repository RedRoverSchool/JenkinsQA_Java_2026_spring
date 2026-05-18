package school.redrover.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.CreateProjectPage;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.project.config.FolderConfigPage;
import school.redrover.page.view.CreateFolderViewPage;
import school.redrover.page.view.FolderViewPage;

import java.util.regex.Pattern;

public class FolderProjectPage extends BaseProjectPage {

    @FindBy(xpath = "//a[contains(@href, 'rename')]")
    private WebElement renameButtonSideMenu;

    @FindBy(linkText = "Configure")
    private WebElement configureButtonSideMenu;

    @FindBy(xpath = "//a[contains(@href, 'newJob')]")
    private WebElement newItemButton;

    @FindBy(xpath = "//a[contains(@href, 'newView')]")
    private WebElement newViewButton;

    @FindBy(xpath = "//button[@value='Rename']")
    private WebElement renameButton;

    @FindBy(id = "description-link")
    private WebElement addDescriptionButton;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionInput;

    @FindBy(xpath = "//button[@value='Save']")
    private WebElement saveDescriptionButton;

    @FindBy(id = "description-content")
    private WebElement descriptionText;

    @FindBy(xpath = "//div[@class='tabBar']//a[contains(@href, 'view')]")
    private WebElement viewName;


    public FolderProjectPage(WebDriver driver) {
        super(driver);
    }

    public FolderProjectPage clickRenameSideMenu() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));
        renameButtonSideMenu.click();

        return this;
    }

    public FolderProjectPage enterNewName(String newName) {
        WebElement newNameElement = getWait2().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newName']")));
        newNameElement.clear();
        newNameElement.sendKeys(newName);

        return this;
    }

    public FolderProjectPage clickRenameButton() {
        renameButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("svg[tooltip='Folder']")));

        return this;
    }

    public FolderProjectPage clickAddDescription() {
        addDescriptionButton.click();
        getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='description']")));

        return this;
    }

    public FolderProjectPage enterDescription(String description) {
        descriptionInput.sendKeys(description);

        return this;
    }

    public FolderProjectPage clickSaveDescription() {
        saveDescriptionButton.click();
        getWait2().until(ExpectedConditions.textMatches(By.id("description-content"), Pattern.compile("\\S")));

        return this;
    }

    public String getDescriptionText() {
        return descriptionText.getText();
    }

    public FolderConfigPage clickConfigure() {
        configureButtonSideMenu.click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Configuration"));

        return new FolderConfigPage(getDriver());
    }

    public CreateProjectPage clickNewItem() {
        newItemButton.click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "New Item"));

        return new CreateProjectPage(getDriver());
    }

    public CreateFolderViewPage clickNewView() {
        newViewButton.click();
        getWait2().until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[contains(@class, 'jenkins-form-label')]"), "View name"));

        return new CreateFolderViewPage(getDriver());
    }

    public String getCurrentViewName() {
        return viewName.getText();
    }

    public FolderViewPage clickOnView () {
        viewName.click();
        return new FolderViewPage(getDriver());

    }
}
