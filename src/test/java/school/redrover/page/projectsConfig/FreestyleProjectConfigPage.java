package school.redrover.page.projectsConfig;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.projects.FreestyleProjectPage;

import java.util.List;

public class FreestyleProjectConfigPage extends BaseConfigPage<FreestyleProjectConfigPage> {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
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

    public FreestyleProjectConfigPage clickSave() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                        By.name("Submit")))
                .click();
        return this;
    }

}
