package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.view.CreateGlobalViewPage;
import school.redrover.page.view.GlobalViewPage;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement buttonNewJob;

    @FindBy(css = ".jenkins-table__link > span:first-child")
    private List<WebElement> projects;

    @FindBy(xpath = "//a[contains(@class, 'task-link')]//span[text()='Status']")
    private WebElement statusLink;

    @FindBy(css = "#search-results")
    private List<WebElement> searchList;

    @FindBy(xpath = "//button[@id='root-action-SearchAction']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='command-bar']")
    private WebElement searchInputField;

    @FindBy(xpath = "//button[contains(@href, 'doDelete')]")
    private WebElement buttonDelete;

    @FindBy(xpath = "//a[contains(@href, 'rename')]")
    private WebElement elementRename;

    @FindBy(id = "root-action-UserAction")
    private WebElement userButton;

    @FindBy(id = "description-link")
    private WebElement elementDescription;

    @FindBy(id = "description-content")
    private WebElement textDescription;

    @FindBy(xpath = "//a[@href='/newView']")
    private WebElement newView;

    @FindBy(xpath = "//button[@data-id='ok']")
    private WebElement buttonConfirmDelete;

    @FindBy(xpath = "//footer//a[contains(text(),'Jenkins')]")
    private WebElement versionJenkins;

    @FindBy(xpath = "//a[contains(text(),'About Jenkins')]")
    private WebElement aboutJenkins;

    @FindBy(xpath = "//a[@href = '/view/all/builds']")
    private  WebElement buttonBuildHistory;


    private static final String PROJECT_NAME = "//a[contains(@href, '%s')]/span";
    private static final String SEARCH_RESULT = "//*[@id='search-results']/a[@href='/job/%s/']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickItemNewJob() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonNewJob)).click();

        return new CreateProjectPage(getDriver());
    }

    public List<String> getProjectList() {
        return projects.stream()
                .map(WebElement::getText).toList();
    }

    public <ProjectPage extends BaseProjectPage> ProjectPage clickOnProject(String projectName, ProjectPage projectPage) {
        WebElement projectNameEl = getDriver().findElement(By.xpath(PROJECT_NAME.formatted(projectName)));
        new Actions(getDriver())
                .moveToElement(projectNameEl, 2, 2)
                .click()
                .perform();

        getWait5().until(ExpectedConditions.visibilityOf(statusLink));

        return projectPage;
    }

    public HomePage search(String name, boolean pressEnter) {
        getWait5().until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        searchInputField.sendKeys(name);

        if (pressEnter) {
            searchInputField.sendKeys(Keys.ENTER);
        }

        return new HomePage(getDriver());
    }

    public HomePage search(String name) {
        return search(name, false);  // По умолчанию не нажимаем Enter
    }

    public GlobalViewPage chooseSearchingResult(String name) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(SEARCH_RESULT, name)))).click();

        return new GlobalViewPage(getDriver());
    }

    public List<String> getSearchList() {
        return searchList.stream()
                .map(WebElement::getText).toList();
    }

    public HomePage openProjectDropdownMenu(String projectName) {
        WebElement row = getDriver().findElement(By.id("job_" + projectName));
        new Actions(getDriver()).moveToElement(row).perform();
        row.findElement(By.className("jenkins-menu-dropdown-chevron")).click();

        return this;
    }

    public HomePage clickDeleteInDropdown() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonDelete)).click();

        return this;
    }

    public RenameProjectPage clickRenameInDropdown() {
        getWait5().until(ExpectedConditions.elementToBeClickable(elementRename)).click();

        return new RenameProjectPage(getDriver());
    }

    public HomePage confirmDelete(String projectName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonConfirmDelete)).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.id("job_" + projectName)));

        return this;
    }

    public GlobalViewPage clickDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(elementDescription)).click();
        return new GlobalViewPage(getDriver());
    }

    public String getViewDescriptionText() {
        return textDescription.getText();
    }

    public CreateGlobalViewPage createGlobalView(){
        getWait5().until(ExpectedConditions.visibilityOf(newView)).click();
        return new CreateGlobalViewPage(getDriver());
    }

    public boolean isUserButtonDisplayed() {
        return getWait10().until(ExpectedConditions.visibilityOf(userButton)).isDisplayed();
    }

    public HomePage clickScheduleBuild(String jobName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='job/%s/build?delay=0sec'])[1]".formatted(jobName)))).click();

        return this;
    }

    public BuildHistoryPage clickBuildHistory() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonBuildHistory)).click();

        return new BuildHistoryPage(getDriver());
    }

    public HomePage scrollToBottom() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    public HomePage clickJenkinsVersionLink() {
        getWait5().until(ExpectedConditions.elementToBeClickable(versionJenkins)).click();
        return this;
    }

    public HomePage clickAboutJenkins() {
        getWait5().until(ExpectedConditions.elementToBeClickable(aboutJenkins)).click();
        return this;
    }

    public boolean isAboutJenkinsPresent() {
        return !getDriver().findElements(By.xpath("//a[contains(text(),'About Jenkins')]")).isEmpty();
    }
}
