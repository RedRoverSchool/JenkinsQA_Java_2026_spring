package school.redrover.page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;
import school.redrover.page.common.BaseProjectPage;
import school.redrover.page.external.CommandPalettePage;
import school.redrover.page.view.create.CreateGeneralViewPage;
import school.redrover.page.view.GeneralViewPage;
import school.redrover.page.user.UserPage;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='/view/all/newJob']")
    private WebElement buttonNewJob;

    @FindBy(css = ".jenkins-table__link > span:first-child")
    private List<WebElement> projects;

    @FindBy(css = "#search-results")
    private List<WebElement> searchList;

    @FindBy(xpath = "//input[@id='command-bar']")
    private WebElement searchInputField;

    @FindBy(xpath = "//button[contains(@href, 'doDelete')]")
    private WebElement buttonDelete;

    @FindBy(xpath = "//a[contains(@href, 'confirm-rename')]")
    private WebElement elementRename;

    @FindBy(id = "description-link")
    private WebElement elementDescription;

    @FindBy(id = "description-content")
    private WebElement textDescription;

    @FindBy(xpath = "//a[@href='/newView']")
    private WebElement newView;

    @FindBy(xpath = "//button[@data-id='ok']")
    private WebElement buttonConfirmDelete;

    private static final String SEARCH_RESULT = "//*[@id='search-results']/a[@href='/job/%s/']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Click New Item button")
    public CreateProjectPage clickItemNewJob() {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonNewJob)).click();
        return new CreateProjectPage(getDriver());
    }

    @Step("Get list of projects on the main page")
    public List<String> getProjectList() {
        return projects.stream()
                .map(WebElement::getText)
                .toList();
    }

    public <ProjectPage extends BaseProjectPage> ProjectPage clickOnProject(String projectName, ProjectPage projectPage) {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a/span[text() = '%s']/..".formatted(projectName))))
                .click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return projectPage;
    }

    public HomePage clickSearchButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(By.id("root-action-SearchAction"))).click();
        return this;
    }

    public HomePage typeSearchInput(String inputText, boolean pressEnter) {
        getWait5().until(ExpectedConditions.visibilityOf(searchInputField)).sendKeys(inputText);
        if (pressEnter) {
            searchInputField.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public CommandPalettePage typeEmptyInputAndPressOK() {
        getWait5().until(ExpectedConditions.visibilityOf(searchInputField)).sendKeys("", Keys.ENTER);
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("command-palette")));

        return new CommandPalettePage(getDriver());
    }

    public <T extends BasePage> T typeSearchInputAndGoToResultsPage(String inputText, T returnPage) {
        searchInputField.sendKeys(inputText);

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'jenkins-command-palette__results__item') and contains( @href, '%s')]".formatted(inputText)))).click();
        getWait5().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#breadcrumbs .jenkins-breadcrumbs__list-item")));

        return returnPage;
    }

    public static String randomString(int length) {
        if (length <= 0) {
            return "";
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (i % 6 == 0 && i != 0) {
                result.append(' ');
            } else {
                char c = (char) ('a' + random.nextInt(26));
                result.append(c);
            }
        }
        return result.toString();
    }

    public boolean isNoResultDisplayed() {
        return getWait5().until(ExpectedConditions.textToBe(By.xpath("//div[@id='search-results']//span"), "No results for"));
    }

    public HomePage clearSearchField() {
        searchInputField.clear();
        return this;
    }

    public String getSearchInputValue() {
        return searchInputField.getAttribute("value");
    }

    public <T extends BasePage> T chooseSearchingResult(String jobname, T returnPage) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format(SEARCH_RESULT, jobname)))).click();
        return returnPage;
    }

    public List<String> getSearchList() {
        return searchList.stream()
                .map(WebElement::getText).toList();
    }

    @Step ("Open Drop-down menu for selected project")
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

    @Step ("Click Rename option in Drop-down menu")
    public RenameProjectPage clickRenameInDropdown() {
        getWait5().until(ExpectedConditions.elementToBeClickable(elementRename)).click();
        return new RenameProjectPage(getDriver());
    }

    public HomePage confirmDelete(String projectName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(buttonConfirmDelete)).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.id("job_" + projectName)));
        return this;
    }

    public GeneralViewPage clickDescription() {
        getWait10().until(ExpectedConditions.visibilityOf(elementDescription)).click();
        return new GeneralViewPage(getDriver());
    }

    public String getViewDescriptionText() {
        return textDescription.getText();
    }

    public String getAddDescriptionText() {
        return elementDescription.getText();
    }

    public CreateGeneralViewPage clickForNewView() {
        getWait5().until(ExpectedConditions.visibilityOf(newView)).click();
        return new CreateGeneralViewPage(getDriver());
    }

    public HomePage clickScheduleBuild(String jobName) {
        getDriver().findElement(By.xpath("//td[@class='jenkins-table__cell--tight']//a[contains(@class, 'jenkins-button') and @tooltip='Schedule a Build for %s']".formatted(jobName))).click();
        return this;
    }

    public BuildHistoryPage clickBuildHistory() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/view/all/builds']"))).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Build History of Jenkins"));

        return new BuildHistoryPage(getDriver());
    }

    public boolean isDashboardNotDisplayed() {
        try {
            return getWait2().until(
                    ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='dashboard']")));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public UserPage searchUser(String userName) {
        WebElement searchInput = getWait10().until(
                ExpectedConditions.visibilityOf(searchInputField));

        searchInput.sendKeys(userName);

        getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[@id='search-results']//a[contains(@href, '/user/%s')]"
                        .formatted(userName.toLowerCase())))).click();

        return new UserPage(getDriver());
    }
}
