package school.redrover.page.manage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.page.common.BasePage;

import java.util.Arrays;
import java.util.List;

public class ToolsPage extends BasePage {

    public ToolsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//select[contains(@class,'jenkins-select__input')])[1]")
    private WebElement mavenOption;

    @FindBy(xpath = "//div[@name='settingsProvider']//input[@name='_.path']")
    private WebElement javaHomeField;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "(//select[contains(@class,'jenkins-select__input')])[2]")
    private WebElement globalMavenOption;

    @FindBy(xpath = "//div[@name='globalSettingsProvider']//input[@name='_.path']")
    private WebElement globalPathField;

    @FindBy(xpath = "//button[contains(text(), 'Add JDK')]")
    private WebElement addJDKButton;

    @FindBy(xpath = "//input[contains(@checkurl, 'hudson.model.JDK/checkName')]")
    private WebElement nameField;

    @FindBy(xpath = "//input[contains(@checkurl, 'hudson.model.JDK/checkHome')]")
    private WebElement pathField;

    @FindBy(xpath = "//span[@tooltip='One or more fields in this block have been edited.']")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    private List<WebElement> deleteButtons;

    @FindBy(id = "settings-search-bar")
    private WebElement searchBar;

    @FindBy(xpath = "//div[@descriptorid='hudson.plugins.git.GitTool']")
    private WebElement gitInstallationSection;

    @FindBy(xpath = "//button[contains(@class, 'jenkins-dropdown__item') and normalize-space(.)='Git']")
    private WebElement firstDropdownItem;

    @FindBy(xpath = "//input[@checkurl = '/manage/descriptorByName/hudson.plugins.git.GitTool/checkName']")
    private WebElement gitNameField;

    @FindBy(xpath = "//input[@checkurl = '/manage/descriptorByName/hudson.plugins.git.GitTool/checkHome']")
    private WebElement gitPathField;

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
        WebElement jdkButton = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(normalize-space(.), 'JDK installations')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", jdkButton);
        getWait2().until(ExpectedConditions.elementToBeClickable(jdkButton)).click();

        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(normalize-space(.), 'Add JDK')]")));

        return this;
    }

    public ToolsPage clickAddJDKButton() {
        WebElement addJDKButton = getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(normalize-space(.), 'Add JDK')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", addJDKButton);
        getWait2().until(ExpectedConditions.elementToBeClickable(addJDKButton)).click();

        return this;
    }

    public ToolsPage setJDKName(String name) {
        WebElement nameField = getWait2().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@checkurl, 'hudson.model.JDK/checkName')]")));
        nameField.clear();
        nameField.sendKeys(name);

        return this;
    }

    public ToolsPage setJavaPath(String path) {
        getWait5().until(ExpectedConditions.visibilityOf(pathField));
        pathField.clear();
        pathField.sendKeys(path);

        return this;
    }

    public boolean isEditDisplayed() {
        return !editButtons.isEmpty();
    }

    public int getJDKsCount() {
        int visibleButtons = 0;
        for (WebElement button : deleteButtons) {
            if (button.isDisplayed()) {
                visibleButtons++;
            }
        }
        return visibleButtons;
    }

    public ToolsPage deleteAllJDKs() {
        while (getJDKsCount() > 0) {
            WebElement currentButton = deleteButtons.getFirst();

            currentButton.click();
            getWait5().until(ExpectedConditions.stalenessOf(currentButton));
        }

        return this;
    }

    public List<String> getJDKData() {
        return Arrays.asList(
                nameField.getAttribute("value"),
                pathField.getAttribute("value")
        );
    }

    public ToolsPage clickAddGitButton() {
        WebElement addGitButton = getDriver().findElement(By.xpath("//button[contains(@class, 'jenkins-button') and contains(text(), 'Add Git')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", addGitButton);
        addGitButton.click();

        return this;
    }

    public ToolsPage selectDropDownItem() {
        getWait5().until(ExpectedConditions.elementToBeClickable(firstDropdownItem));
        firstDropdownItem.click();

        return this;
    }

    public boolean isGitInstallationsAppears() {
        getWait10().until(ExpectedConditions.visibilityOf(gitInstallationSection));
        return gitInstallationSection.isDisplayed ();
    }

    public ToolsPage setGitName (String name) {
        getWait5().until(ExpectedConditions.visibilityOf(gitNameField));
        gitNameField.clear();
        gitNameField.sendKeys(name);

        return this;
    }

    public ToolsPage setGitPath (String path) {
        getWait5().until(ExpectedConditions.visibilityOf(gitPathField));
        gitPathField.clear();
        gitPathField.sendKeys(path);

        return this;
    }

    public List<String> getGitData() {
        return Arrays.asList(
                gitNameField.getAttribute("value"),
                gitPathField.getAttribute("value")
        );
    }
}
