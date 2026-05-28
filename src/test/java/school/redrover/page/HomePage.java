package school.redrover.page;

import org.openqa.selenium.*;
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

    @FindBy(css = "#search-results")
    private List<WebElement> searchList;

    @FindBy(xpath = "//button[@id='root-action-SearchAction']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//input[@id='command-bar']")
    private WebElement searchInputField;

    @FindBy(xpath = "//button[contains(@href, 'doDelete')]")
    private WebElement buttonDelete;

    @FindBy(xpath = "//a[contains(@href, 'rename')]")
    private WebElement elementRename;

    @FindBy(id = "description-link")
    private WebElement elementDescription;

    @FindBy(id = "description-content")
    private WebElement textDescription;

    @FindBy(xpath = "//a[@href='/newView']")
    private WebElement newView;

    @FindBy(xpath = "//button[@data-id='ok']")
    private WebElement buttonConfirmDelete;

    @FindBy(xpath = "//a[@href='/view/all/builds']")
    private WebElement buttonBuildHistory;

    @FindBy(xpath = "//footer//a[contains(text(),'REST API')]")
    private WebElement restApiLink;

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
        getDriver().findElement(By.xpath("//td/a/span[text() = '%s']/..".formatted(projectName)))
                .click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return projectPage;
    }

    public HomePage search(String name, boolean pressEnter) {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonSearch)).click();
        searchInputField.sendKeys(name);
        if (pressEnter) {
            searchInputField.sendKeys(Keys.ENTER);
        }
        return new HomePage(getDriver());
    }

    public HomePage search(String name) {
        return search(name, false);
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
        getWait10().until(ExpectedConditions.visibilityOf(elementDescription)).click();
        return new GlobalViewPage(getDriver());
    }

    public String getViewDescriptionText() {
        return textDescription.getText();
    }

    public String getAddDescriptionText() {
        return elementDescription.getText();
    }

    public CreateGlobalViewPage clickForNewView(){
        getWait5().until(ExpectedConditions.visibilityOf(newView)).click();
        return new CreateGlobalViewPage(getDriver());
    }

    public HomePage clickScheduleBuild(String jobName) {
        getDriver().findElement(By.xpath("//td[@class='jenkins-table__cell--tight']//a[contains(@class, 'jenkins-button') and @tooltip='Schedule a Build for %s']".formatted(jobName))).click();
        return this;
    }

    public BuildHistoryPage clickBuildHistory() {
        buttonBuildHistory.click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Build History of Jenkins"));

        return new BuildHistoryPage(getDriver());
    }

    public HomePage scrollToBottom() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    public RestApiPage clickRestApiLink() {
        getWait10().until(ExpectedConditions.elementToBeClickable(restApiLink)).click();
        return new RestApiPage(getDriver());
    }

    public String getRestApiLinkCursor() {
        return getWait10().until(ExpectedConditions.visibilityOf(restApiLink)).getCssValue("cursor");
    }

    public boolean isDashboardVisible() {
        try {
            return getWait10().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/view/')]"))
            ).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
