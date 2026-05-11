package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.HomePage;
import school.redrover.page.common.BasePage;

public class GlobalViewPage extends BasePage {

    @FindBy
    private static final By jobName= By.xpath("//h1[@class='job-index-headline page-headline']");

    @FindBy
    private static final By descriptionInput = By.name("description");

    @FindBy
    private static final By cancelButton = By.xpath("//button[text()='Cancel']");

    @FindBy
    private static final By saveButton = By.xpath("//button[@name='Submit']");

    @FindBy
    private static final By descriptionMessage = By.id("description-content");

    @FindBy
    private static final By addDescriptionButton = By.id("description-link");

    public GlobalViewPage(WebDriver driver) {
        super(driver);
    }

    public String getJobTitle() {
        return getWait5().until(ExpectedConditions.presenceOfElementLocated(jobName)).getText();
    }

    public GlobalViewPage inputDescription(String textInput) {
        getWait5().until(ExpectedConditions.elementToBeClickable(descriptionInput));
        getDriver().findElement(descriptionInput).sendKeys(textInput);

        return this;
    }

    public GlobalViewPage clearDescription() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(descriptionInput)).clear();

        return this;
    }

    public HomePage clickSave() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(saveButton)).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(addDescriptionButton));

        return new HomePage(getDriver());
    }

    public HomePage cancelButton() {
        getDriver().findElement(cancelButton).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(descriptionMessage));

        return new HomePage(getDriver());
    }

    public String clickPreviewButton() {
        getDriver().findElement(By.className("textarea-show-preview")).click();
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("textarea-preview"))).getText();
    }

    public GlobalViewPage clickHideButton() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("textarea-hide-preview")));
        return this;
    }
}