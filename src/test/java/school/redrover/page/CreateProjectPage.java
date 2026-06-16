package school.redrover.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.TestUtils;
import school.redrover.page.common.BasePage;
import school.redrover.page.project.MultiConfigurationProjectPage;
import school.redrover.page.project.config.FolderConfigPage;
import school.redrover.page.project.config.FreestyleProjectConfigPage;
import school.redrover.page.project.config.MultibranchConfigPage;
import school.redrover.page.project.config.PipelineProjectConfigPage;

public class CreateProjectPage extends BasePage {

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "itemname-invalid")
    private WebElement errorInvalidName;

    @FindBy(id = "itemname-required")
    private WebElement errorNameRequired;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    @FindBy(xpath = "//span[text()='Pipeline']")
    private WebElement pipelineOption;

    @FindBy(xpath = "//span[text()='Folder']")
    private WebElement folderOption;

    @FindBy(xpath = "//span[text()='Multi-configuration project']")
    private WebElement optionMultiConfiguration;

    @FindBy(xpath = "//li[@class='hudson_model_FreeStyleProject']")
    private WebElement optionFreestyleProject;

    @FindBy(xpath = "//*[@id='main-panel']/h1")
    private WebElement errorTitle;

    private static final By BUTTON_OK = By.name("Submit");

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter an item name")
    public CreateProjectPage setProjectName(String name) {
        inputName.sendKeys(name);

        return this;
    }

    public CreateProjectPage scrollToTypeOfProject(TestUtils.JobType jobType) {
        WebElement jobElement = getDriver().findElement(By.xpath("//span[text()='%s']".formatted(jobType.getDisplayName())));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", jobElement);
        jobElement.click();

        return this;
    }

    public CreateProjectPage enterProjectNameToCopyFromField(String projectName) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("from"))).sendKeys(projectName);

        return this;
    }

    public String getEmptyStateMessage() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".jenkins-dropdown__placeholder"))).getText();
    }

    public MultibranchConfigPage selectMultibranchAndClickOk() {
        getDriver().findElement(By.xpath("//span[text()='Multi-configuration project']")).click();
        okButton.click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_OK));

        return new MultibranchConfigPage(getDriver());
    }

    public CreateProjectPage selectFreeStyleProject() {
        optionFreestyleProject.click();

        return this;
    }

    public FreestyleProjectConfigPage selectFreestyleProjectAndClickOk() {
        optionFreestyleProject.click();
        okButton.click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_OK));

        return new FreestyleProjectConfigPage(getDriver());
    }

    @Step("Select Folder and click OK")
    public FolderConfigPage selectFolderProjectAndClickOk() {
        folderOption.click();
        okButton.click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));

        return new FolderConfigPage(getDriver());
    }

    public MultiConfigurationProjectPage selectMulticonfigAndClickOk() {
        optionMultiConfiguration.click();
        okButton.click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_OK));

        return new MultiConfigurationProjectPage(getDriver());
    }

    public CreateProjectPage selectPipelineProjectAndWaitError() {
        pipelineOption.click();

        return this;
    }

    public PipelineProjectConfigPage selectPipelineProjectAndClickOk() {
        pipelineOption.click();
        okButton.click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_OK));

        return new PipelineProjectConfigPage(getDriver());
    }

    public CreateProjectPage selectItemType(TestUtils.JobType jobType) {
        getDriver().findElement(By.xpath("//span[text()='%s']".formatted(jobType.getDisplayName()))).click();

        return this;
    }

    public String getErrorText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("itemname-invalid"))).getText();
    }

    public CreateProjectPage selectFolder() {
        getDriver().findElement(By.xpath("//li[contains(@class,'com_cloudbees_hudson_plugins_folder_Folder')]")).click();

        return this;
    }

    public <JobConfigPage extends BasePage> JobConfigPage clickOK(JobConfigPage jobConfig) {
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(BUTTON_OK));

        return jobConfig;
    }

    public ErrorNamePage clickOKWithError() {
        okButton.click();
        getWait10().until(ExpectedConditions.visibilityOf(errorTitle));

        return new ErrorNamePage(getDriver());
    }

    public String getErrorEmptyText() {
        return getWait5().until(ExpectedConditions.visibilityOf(errorNameRequired)).getText();
    }

    public String getErrorInvalidText() {
        return getWait5().until(ExpectedConditions.visibilityOf(errorInvalidName)).getText();
    }

    public boolean isOkButtonEnabled() {
        return okButton.isEnabled();
    }

    public CreateProjectPage selectProjectDropDown() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-dropdown__item"))).click();

        return this;
    }
}
