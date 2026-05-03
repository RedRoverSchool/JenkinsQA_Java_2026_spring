package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GlobalViewPage extends BasePage{

    private static final String TEXT_DESCRIPTION_BUTTON = "Add description";
    private static final String DESC_MESSAGE = "Some description text here";
    private static final String UPDATED_DESC_MESSAGE = "Updated description";
    private static String textInput = "Test";
    private static final By DESCRIPTION_INPUT = By.name("description");
    private static final By PREIVEW_CONTAINER = By.className("textarea-preview-container");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");

    public GlobalViewPage(WebDriver driver) {
        super(driver);
    }


    public String getPreviewText() {
        return getDriver().findElement(PREIVEW_CONTAINER).getText();
    }

    public boolean descriptionInputIsVisible() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_INPUT));
        return !getDriver().findElements(DESCRIPTION_INPUT).isEmpty();
    }

    public WebElement getDescriptionInput() {
        return getDriver().findElement(DESCRIPTION_INPUT);
    }

    public WebElement saveButtonIsVisible() {
        return  getWait5().until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON));
    }

    public String getDescriptionText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("description-content"))).getText();
    }
}