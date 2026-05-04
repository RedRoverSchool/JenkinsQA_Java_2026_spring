package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GlobalViewPage extends BasePage{

    private static final String TEXT_DESCRIPTION_BUTTON = "Add description";
    private static final String DESC_MESSAGE = "Some description text here";
    private static final String UPDATED_DESC_MESSAGE = "Updated description";
    private static final By DESCRIPTION_INPUT = By.name("description");
    private static final By PREIVEW_CONTAINER = By.className("textarea-preview-container");
    private static final By SAVE_BUTTON = By.xpath("//button[@name='Submit']");
    private static final By DESCRIPTION_MESSAGE = By.id("description-content");

    public GlobalViewPage(WebDriver driver) {
        super(driver);
    }

    public String getPreviewText() {
        return getDriver().findElement(PREIVEW_CONTAINER).getText();
    }

    public GlobalViewPage enterDescription(String textInput) {
        getDriver().findElement(DESCRIPTION_INPUT).sendKeys(textInput);

        return this;
    }

    public HomePage clickSave() {
        getDriver().findElement(SAVE_BUTTON).click();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_MESSAGE));

        return new HomePage(getDriver());
    }
}