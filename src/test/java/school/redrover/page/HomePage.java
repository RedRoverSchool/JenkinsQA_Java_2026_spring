package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.BuildHistoryTest;

import java.util.List;

public class HomePage extends BasePage {

    private static final By SEARCH_BUTTON = By.xpath("//button[@id='root-action-SearchAction']");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//input[@id='command-bar']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CreateProjectPage clickItemNewJob() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/newJob']"))).click();

        return new CreateProjectPage(getDriver());
    }

    public List<String> getProjectList() {
        return getDriver().findElements(By.cssSelector(".jenkins-table__link>span:first-child"))
                .stream().map(WebElement::getText).toList();
    }

    public <JobPage extends BasePage> JobPage clickOnProject(JobPage jobpage, String projectName) {
        WebElement projectNameEl = getDriver().findElement(By.xpath("//a[contains(@href, '%s')]/span".formatted(projectName)));
        new Actions(getDriver())
                .moveToElement(projectNameEl, 2, 2)
                .click()
                .perform();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return jobpage;
    }

    public HomePage search(String name, boolean pressEnter) {
        getWait5().until(ExpectedConditions.elementToBeClickable(
                SEARCH_BUTTON)).click();

        WebElement input = getDriver().findElement(SEARCH_INPUT_FIELD);
        input.sendKeys(name);

        if (pressEnter) {
            input.sendKeys(Keys.ENTER);
        }

        return new HomePage(getDriver());
    }

    public HomePage search(String name) {
        return search(name, false);  // По умолчанию не нажимаем Enter
    }

    public GlobalViewPage chooseSearchingResult(String name) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//*[@id='search-results']/a[@href='/job/%s/']", name)))).click();

        return new GlobalViewPage(getDriver());
    }

    public List<String> getSearchList() {
        return getDriver().findElements(By.cssSelector("#search-results"))
                .stream().map(WebElement::getText).toList();
    }

    public HomePage openProjectDropdownMenu(String projectName) {
        WebElement row = getDriver().findElement(By.id("job_" + projectName));
        new Actions(getDriver()).moveToElement(row).perform();
        row.findElement(By.className("jenkins-menu-dropdown-chevron")).click();

        return this;
    }

    public HomePage clickDeleteInDropdown() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@href, 'doDelete')]"))).click();

        return this;
    }

    public HomePage confirmDelete(String projectName) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='ok']"))).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.id("job_" + projectName)));

        return this;
    }

    public GlobalViewPage clickDescription() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("description-link"))).click();

        return new GlobalViewPage(getDriver());
    }

    public BuildHistoryPage clickBuildHistory(){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view/all/builds']"))).click();

        return new BuildHistoryPage(getDriver());
    }

    public String getViewDescriptionText() {
        return getDriver().findElement(By.id("description-content")).getText();
    }

    public ManagePage clickManageJenkins() {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("root-action-ManageJenkinsAction"))).click();
        return new ManagePage(getDriver());
    }

}
