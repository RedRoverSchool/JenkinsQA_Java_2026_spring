package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.project.FreestyleProjectPage;

import java.util.List;
import java.util.Objects;

public class FreestyleProjectConfigPage extends BaseConfigPage<FreestyleProjectConfigPage> {

    By deleteWorkspaceBeforeBuildStartsCheckbox = By.name("hudson-plugins-ws_cleanup-PreBuildCleanup");
    By executeWindowsBatchCommandMenuItem = By.xpath("//button[contains(., 'Execute Windows batch command')]");
    By projectName = By.cssSelector("#breadcrumbs a");
    By daysToKeepArtifactsInput = By.name("_.artifactDaysToKeepStr");
    By maxOfBuildToKeepWithArtifactsInput = By.name("_.artifactNumToKeepStr");
    By discardOldBuildsCheckbox = By.id("cb2");

    @FindBy(name = "_.daysToKeepStr")
    private WebElement daysToKeepBuildsInput;

    @FindBy(name = "_.numToKeepStr")
    private WebElement maxOfBuildToKeepInput;

    @FindBy(xpath = "//label[normalize-space()='Discard old builds']\n" +
            "    /ancestor::div[contains(@class,'optionalBlock')]\n" +
            "    //button[normalize-space()='Advanced']")
    private WebElement advancedButton;

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FreestyleProjectConfigPage self() {
        return this;
    }

    public FreestyleProjectConfigPage fillDescription(String descriptiontext) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("description")))
                .sendKeys(descriptiontext);

        return this;
    }

    public FreestyleProjectConfigPage clickCheckBoxGitHub() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(text(),'GitHub project')]")))
                .click();

        return this;
    }

    public FreestyleProjectConfigPage fillGitURL(String repoURL) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                        By.name("_.projectUrlStr"))).
                sendKeys(repoURL);

        return this;
    }

    public FreestyleProjectConfigPage clickAddBuildStep() {
        WebElement addBuildStepButton = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@suffix='builder']")));

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView(true);", addBuildStepButton);

        getWait5().until(ExpectedConditions.elementToBeClickable(addBuildStepButton));
        addBuildStepButton.click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'jenkins-dropdown')]")));

        return this;
    }

    public FreestyleProjectConfigPage clickBuildStep(By locator) {
        getDriver().findElement(locator).click();

        return this;
    }

    public FreestyleProjectConfigPage clickExecuteWindowsBatchCommandMenuItem() {
        return clickBuildStep(executeWindowsBatchCommandMenuItem);
    }

    public boolean isBuildStepAdded() {
        return getDriver().findElement(
                By.xpath("//div[contains(., 'Execute Windows batch command')]")).isDisplayed();
    }

    public List<String> listOfBuildSteps() {
        return getDriver()
                .findElements(By.xpath("//div[@class='jenkins-dropdown jenkins-dropdown--compact']//button"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public FreestyleProjectPage clickSaveButton() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.name("Submit"))).click();

        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//li[@data-type='breadcrumb-item']//span[text()='Configure']")));

        return new FreestyleProjectPage(getDriver());
    }

    public FreestyleProjectConfigPage setDeleteWorkspaceBeforeBuildStartsCheckbox(boolean value) {
        WebElement checkbox = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//label[contains(.,'Delete workspace before build starts')]"))
        );

        if (checkbox.isSelected() != value && checkbox.isEnabled()) {
            ((JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

            checkbox.click();
        }

        return this;
    }

    public boolean isCheckBoxChecked(By locator) {
        return getWait10()
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .isSelected();
    }

    public boolean isDeleteWorkspaceBeforeBuildStartsCheckboxChecked() {
        return isCheckBoxChecked(deleteWorkspaceBeforeBuildStartsCheckbox);
    }

    public boolean idDiscardOldBuildsCheckboxChecked() {
        return isCheckBoxChecked(discardOldBuildsCheckbox);
    }


    public FreestyleProjectConfigPage clickOnBuildStep() {
        List<WebElement> dropdownItems = getWait5().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("button.jenkins-dropdown__item")));

        dropdownItems.getFirst().click();

        return this;
    }

    public FreestyleProjectConfigPage enterCommand(String text) {
        WebElement commandField = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//textarea[@name='command']")));

        commandField.sendKeys(text);

        return this;
    }

    public boolean clickDeleteButton() {
        WebElement deleteButton = getWait10().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//button[contains(@class, 'repeatable-delete')])[last()]")));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", deleteButton);

        return getDriver().findElement(By.xpath("//textarea[@name='command']")).isDisplayed();
    }

    public FreestyleProjectConfigPage enterDescription(String descriptionText) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='description']"))).click();
        getDriver().findElement(
                By.xpath("//textarea[@name='description']")).sendKeys(descriptionText);
        return this;

    }

    public FreestyleProjectConfigPage disableProjectToggle() {
        getDriver().findElement(
                By.xpath("//label[@class='jenkins-toggle-switch__label ']")).click();

        return this;
    }

    public Boolean getProjectState(String expectedState) {
        return getWait10().until(ExpectedConditions.textToBe(
                By.className("jenkins-toggle-switch__label"), expectedState));
    }

    public FreestyleProjectConfigPage selectBuildAfterOtherProjectsAreBuiltCheckbox() {
        By checkbox = By.name("jenkins-triggers-ReverseBuildTrigger");

        WebElement element = getWait5().until(ExpectedConditions.presenceOfElementLocated(checkbox));

        try {
            getWait10().until(
                    ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
        }

        return this;
    }

    public FreestyleProjectConfigPage enterMessageIntoProjectsToWatchField(String message) {
        getDriver().findElement(By.name("_.upstreamProjects")).sendKeys(message);

        return this;
    }

    public FreestyleProjectConfigPage selectTriggerEvenIfTheBuildFailsRadioButton() {
        getDriver().findElement(By.cssSelector("input[value = 'FAILURE']"));

        return this;
    }

    public FreestyleProjectConfigPage selectGitRadioButton() {
        WebElement gitOption = getDriver().findElement(By.xpath("//label[text()='Git']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gitOption);

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")));

        return this;
    }

    public FreestyleProjectConfigPage enterBranchSpecifier(String branch) {
        WebElement branchSpecifier = getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]")));

        branchSpecifier.clear();
        branchSpecifier.sendKeys(branch);

        return this;
    }

    public FreestyleProjectConfigPage enterRepositoryURL(String url) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.url"))).
                sendKeys(url);
        return this;
    }

    public String getRepositoryConnectionErrorMessage() {
        getWait10().until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//input[@name='_.url']/following::div[@class='error'][1]"),
                "Failed to connect to repository"));

        String actualRepositoryConnectionErrorMessage = getDriver().findElement(
                By.xpath("//input[@name='_.url']/following::div[@class='error'][1]")).getText();

        return actualRepositoryConnectionErrorMessage;
    }

    public String getRepositoryUrl() {
        return getDriver().findElement(By.name("_.url")).getAttribute("value");
    }

    public String getBranchSpecifierValue() {
        return getDriver().findElement(
                        By.xpath("//div[contains(text(), 'Branch Specifier')]/following::input[1]"))
                .getAttribute("value");
    }

    public String getDescriptionText() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.name("description"))).getAttribute("value");
    }

    public String getProjectName() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(projectName)).getText();
    }

    public boolean isCurrentUrlCorrect(String newItemName) {
        return Objects.requireNonNull(getDriver().getCurrentUrl())
                .contains("/job/" + newItemName + "/configure");
    }

    public FreestyleProjectConfigPage clickDiscardOldBuildsCheckbox() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Discard old builds']"))).click();

        return this;
    }

    public FreestyleProjectConfigPage enterMessageToDaysToKeepBuildsInput(String num) {
        WebElement element = getWait10().until(ExpectedConditions.visibilityOf(daysToKeepBuildsInput));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        getWait10().until(ExpectedConditions.elementToBeClickable(element)).click();
        daysToKeepBuildsInput.sendKeys(num);

        return this;
    }

    public String getDaysToKeepBuildsValue() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("_.daysToKeepStr"))).getAttribute("value");
    }

    public FreestyleProjectConfigPage enterMessageToMaxOfBuildToKeepInput(String num) {
        getWait10().until(ExpectedConditions.visibilityOf(maxOfBuildToKeepInput)).click();
        maxOfBuildToKeepInput.sendKeys(num);

        return this;
    }

    public String getMaxOfBuildToKeepValue() {
        return getDriver().findElement(By.name("_.numToKeepStr")).getAttribute("value");
    }

    public FreestyleProjectConfigPage clickAdvancedAccordion()  {
        WebElement element = getWait10().until(ExpectedConditions.visibilityOf(advancedButton));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        getWait10().until(ExpectedConditions.elementToBeClickable(element)).click();
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(daysToKeepArtifactsInput));

        return this;
    }

    public FreestyleProjectConfigPage enterMessagesToDaysToKeepArtifacts(String num) {
        WebElement input = getWait10().until(ExpectedConditions.elementToBeClickable(daysToKeepArtifactsInput));
        input.clear();
        input.sendKeys(num);

        return this;
    }

    public String getDaysToKeepArtifactsValue() {
        return getDriver().findElement(By.name("_.artifactDaysToKeepStr")).getAttribute("value");
    }

    public FreestyleProjectConfigPage enterMessageToMaxOfBuildsToKeepWithArtifacts(String num) {
        WebElement input = getWait10().until(ExpectedConditions.elementToBeClickable(maxOfBuildToKeepWithArtifactsInput));
        input.clear();
        input.sendKeys(num);

        return this;
    }

    public String getMaxOfBuildsToKeepWithArtifactsValue() {
        return getDriver().findElement(By.name("_.artifactNumToKeepStr")).getAttribute("value");
    }
}
