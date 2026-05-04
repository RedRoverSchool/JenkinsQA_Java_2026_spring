package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GlobalViewPage extends BasePage {

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

    public GlobalViewPage enterDescription(String description) {
        getDriver().findElement(By.name("description")).sendKeys(description);

        return this;
    }

    public String getPreviewText() {
        return getDriver().findElement(PREIVEW_CONTAINER).getText();
    }

    public HomePage clickSave() {
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")));

        return new HomePage(getDriver());
    }
}