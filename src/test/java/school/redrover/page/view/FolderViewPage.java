package school.redrover.page.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class FolderViewPage extends BasePage {

    @FindBy(id = "description-link")
    private WebElement buttonDescription;

    @FindBy(name = "description")
    private WebElement fieldDescription;

    @FindBy(xpath = "//*[@id='description-edit-form']//a[normalize-space()='Preview']")
    private WebElement linkPreview;

    @FindBy(css = "#description-edit-form .textarea-preview")
    private WebElement areaPreview;

    private static final By DESCRIPTION_CONTENT = By.id("description-content");

    public FolderViewPage(WebDriver driver) {
        super(driver);
    }

    public FolderViewPage addDescription(String expectedDescription){
        buttonDescription.click();
        getWait5().until(ExpectedConditions.visibilityOf(fieldDescription)).sendKeys(expectedDescription);
        return this;
    }

    public String getTextPreview(){
        linkPreview.click();
        return getWait10().until(ExpectedConditions.visibilityOf(areaPreview)).getText();
    }

    public FolderViewPage clickSubmitButton(){
        getDriver().findElement(By.name("Submit")).click();
        return this;
    }
    public String getDescriptionText(){
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_CONTENT)).getText();
    }
}
