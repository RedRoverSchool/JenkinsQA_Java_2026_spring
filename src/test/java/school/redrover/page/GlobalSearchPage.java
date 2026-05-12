package school.redrover.page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

public class GlobalSearchPage extends BasePage {

    @FindBy(id = "root-action-SearchAction")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'jenkins-search')]//input")
    private WebElement searchInputField;

    public GlobalSearchPage(WebDriver driver) {
        super(driver);
    }

    public GlobalSearchPage findSearchButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        return this;
    }

    public GlobalSearchPage clickSearchInputField() {
        getWait5().until(ExpectedConditions.elementToBeClickable(searchInputField)).click();

        return this;
    }

    public GlobalSearchPage typeSearchQuery(String query){
        getWait5().until(ExpectedConditions.visibilityOf(searchInputField)).sendKeys(query);

        return this;
    }

    public GlobalSearchPage clearSearchField(){
        WebElement input = getWait5().until(ExpectedConditions.visibilityOf(searchInputField));

        input.clear();
        return this;
    }

    public String getSearchInputValue() {
        return getWait5()
                .until(ExpectedConditions.visibilityOf(searchInputField))
                .getAttribute("value");
    }
}
