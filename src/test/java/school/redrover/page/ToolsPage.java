package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.page.common.BasePage;

import java.util.List;

public class ToolsPage extends BasePage {

    public ToolsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//select[contains(@class,'jenkins-select__input')])[1]")
    private WebElement mavenOption;

    @FindBy(xpath = "(//input[@name='_.path'])[1]")
    private WebElement javaHomeField;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "(//select[contains(@class,'jenkins-select__input')])[2]")
    private WebElement globalMavenOption;

    @FindBy(xpath = "(//input[@name='_.path'])[2]")
    private WebElement globalPathField;

    @FindBy(xpath = "//button[contains(text(),'JDK installations')]")
    private WebElement JDKInstallationsButton;

    @FindBy(xpath = "//button[contains(text(), 'Add JDK')]")
    private WebElement addJDKButton;

    @FindBy(name = "_.name")
    private WebElement nameField;

    @FindBy(name = "_.home")
    private WebElement pathField;

    @FindBy(xpath = "//span[@tooltip='One or more fields in this block have been edited.']")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    private List<WebElement> deleteButtons;

    @FindBy(id = "settings-search-bar")
    private WebElement searchBar;

    public ToolsPage selectMavenOption(String option) {
        new Select(mavenOption).selectByVisibleText(option);

        return this;
    }

    public boolean isPathFieldAppears() {
        return getWait5().until(ExpectedConditions.visibilityOf(javaHomeField)).isDisplayed();
    }

    public ManagePage clickSaveButton() {
        saveButton.click();

        getWait10().until(ExpectedConditions.visibilityOf(searchBar));

        return new ManagePage(getDriver());
    }

    public ToolsPage selectGlobalMavenOption(String option) {
        new Select(globalMavenOption).selectByVisibleText(option);

        return this;
    }

    public boolean isGlobalPathFieldAppears() {
        return getWait5().until(ExpectedConditions.visibilityOf(globalPathField)).isDisplayed();
    }

    public ToolsPage clickJDKInstallationsButton() {
        JDKInstallationsButton.click();

        return this;
    }

    public ToolsPage clickAddJDKButton() {
        addJDKButton.click();

        return this;
    }

    public ToolsPage setJDKName(String name) {

        if(!nameField.getAttribute("value").isEmpty()) {
            nameField.clear();
        }

        nameField.sendKeys(name);

        return this;
    }

    public ToolsPage setJavaPath(String path) {

        if(!pathField.getAttribute("value").isEmpty()) {
            pathField.clear();
        }

        pathField.sendKeys(path);

        return this;
    }

    public boolean isEditDisplayed() {
        return !editButtons.isEmpty();
    }

    public ToolsPage deleteAllJDKs() {
        while (!deleteButtons.isEmpty()) {
            WebElement currentButton = deleteButtons.get(0);
            currentButton.click();
            getWait5().until(ExpectedConditions.stalenessOf(currentButton));
        }

        return this;
    }

    public List<String> getJDKData() {
        return List.of(
                nameField.getAttribute("value"),
                pathField.getAttribute("value")
        );
    }
}