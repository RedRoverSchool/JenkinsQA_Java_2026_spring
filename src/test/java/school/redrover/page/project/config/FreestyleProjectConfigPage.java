package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.project.FreestyleProjectPage;

import java.util.List;

public class FreestyleProjectConfigPage extends BaseConfigPage<FreestyleProjectConfigPage> {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FreestyleProjectConfigPage self() {
        return this;
    }

    public FreestyleProjectPage clickSubmitButton() {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();

        // todo: изменить ожидание FreestyleProjectPage, чтобы не работало с url
        getWait10().until(ExpectedConditions.not(ExpectedConditions.urlContains("configure")));

        return new FreestyleProjectPage(getDriver());
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

    public List <String> listOfBuildSteps(){
        return getDriver()
                .findElements(By.xpath("//div[@class='jenkins-dropdown jenkins-dropdown--compact']//button"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public FreestyleProjectPage clickSave() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                        By.name("Submit")))
                .click();

        return new FreestyleProjectPage(getDriver());
    }

    public FreestyleProjectConfigPage  enableDeleteWorkspaceBeforeBuildStarts() {
        WebElement checkboxLabel = getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(.,'Delete workspace before build starts')]")));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", checkboxLabel);

        checkboxLabel.click();
        return this;
    }

    public boolean isDeleteWorkspaceBeforeBuildStartsSelected() {
        return getWait10().until(ExpectedConditions.presenceOfElementLocated(By.name("hudson-plugins-ws_cleanup-PreBuildCleanup"))).isSelected();
    }

    public FreestyleProjectConfigPage clickOnBuildStep() {
        List<WebElement> dropdownItems = getWait5().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("button.jenkins-dropdown__item")));

        dropdownItems.get(0).click();

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
}
