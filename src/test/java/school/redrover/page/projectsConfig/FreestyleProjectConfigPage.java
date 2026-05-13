package school.redrover.page.projectsConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.projects.FreestyleProjectPage;

import java.util.List;
import java.util.Objects;

public class FreestyleProjectConfigPage extends BaseConfigPage<FreestyleProjectConfigPage> {

    @FindBy(name = "description")
    private WebElement description;

    @FindBy(xpath ="//label[contains(text(),'GitHub project')]")
    private WebElement checkBoxGithubProject;

    @FindBy(name = "_.projectUrlStr")
    private WebElement inputFieldGitHubProjectURL;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(css = "h1.job-index-headline")
    private WebElement projectTitle;

    @FindBy(css = "input[name='githubProject'][type='checkbox']")
    private WebElement gitHubCheckBox;

    @FindBy(name = "_.projectUrlStr")
    private WebElement gitProjectUrl;

    @FindBy(xpath = "//ol[@id='breadcrumbs']//a[contains(@href, '/job/')]")
    private WebElement projectNameBreadcrumb;

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectNameBreadcrumbText() {
        return getWait10().until(ExpectedConditions.visibilityOf(projectNameBreadcrumb))
                .getText();
    }

    public boolean isOpenedForProject(String projectName) {
        return Objects.requireNonNull(getDriver().getCurrentUrl())
                .contains("/job/" + projectName + "/configure");
    }

    public String getDescriptionText() {
        return getWait10().until(ExpectedConditions.visibilityOf(description))
                .getAttribute("value");
    }

    public boolean isGitHubProjectSelected() {
        return getWait10().until(ExpectedConditions.visibilityOf(gitHubCheckBox))
                .isSelected();
    }

    public String getGitProjectUrl() {
        return getWait10().until(ExpectedConditions.visibilityOf(gitProjectUrl))
                .getAttribute("value");
    }

    public String getProjectTitle() {
        return getWait10().until(ExpectedConditions.visibilityOf(projectTitle))
                .getText();
    }

    public FreestyleProjectPage clickSubmitButton() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        // todo: изменить ожидание FreestyleProjectPage, чтобы не работало с url
        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlContains("configure")));

        return new FreestyleProjectPage(getDriver());
    }

    public FreestyleProjectConfigPage fillDescription(String descriptionText) {

        getWait10().until(ExpectedConditions.visibilityOf(description))
                .sendKeys(descriptionText);
        return this;
    }

    public FreestyleProjectConfigPage clickCheckBoxGitHub() {

        getWait10().until(
                ExpectedConditions.elementToBeClickable(checkBoxGithubProject))
                .click();

        return this;
    }

    public FreestyleProjectConfigPage fillGitURL(String repoURL) {

        getWait10().until(ExpectedConditions.visibilityOf(inputFieldGitHubProjectURL))
                .sendKeys(repoURL);
        return this;
    }

    public FreestyleProjectConfigPage clickAddBuildStep(){
        WebElement addBuildStepButton = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@suffix='builder']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", addBuildStepButton);
        getWait5().until(ExpectedConditions.elementToBeClickable(addBuildStepButton));
        addBuildStepButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'jenkins-dropdown')]")));
        return this;

    }

    public List <String> listOfBuildSteps(){
        return getDriver()
                .findElements(By.xpath("//div[@class='jenkins-dropdown jenkins-dropdown--compact']//button"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public FreestyleProjectPage clickSave() {

        getWait10().until(
                ExpectedConditions.elementToBeClickable(submitButton))
                .click();

        return new FreestyleProjectPage(getDriver());
    }


    public FreestyleProjectConfigPage  enableDeleteWorkspaceBeforeBuildStarts() {
        WebElement checkboxLabel = getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(.,'Delete workspace before build starts')]")));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});",
                checkboxLabel);

        checkboxLabel.click();
        return this;
    }
    public boolean isDeleteWorkspaceBeforeBuildStartsSelected() {
        return getWait10().until(ExpectedConditions.presenceOfElementLocated(By.name("hudson-plugins-ws_cleanup-PreBuildCleanup")))
                .isSelected();
    }
}
