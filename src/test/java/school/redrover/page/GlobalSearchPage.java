package school.redrover.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GlobalSearchPage extends BasePage {

    private static final By SEARCH_BUTTON = By.id("root-action-SearchAction");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//div[contains(@class,'jenkins-search')]//input");

    public GlobalSearchPage(WebDriver driver) {
        super(driver);
    }

    public GlobalSearchPage findSearchButton(){
        getWait5().until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();

        return this;
    }

    public GlobalSearchPage clickSearchInputField() {
        getWait5().until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT_FIELD)).click();

        return this;
    }

    public GlobalSearchPage typeSearchQuery(String query){
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_FIELD)).sendKeys(query);

        return this;
    }

    public GlobalSearchPage clearSearchField(){
        WebElement input = getWait5().until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_FIELD));

        input.clear();
        return this;
    }

    public String getSearchInputValue() {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_FIELD))
                .getAttribute("value");
    }

    public GlobalSearchPage goHomePageSafely() {
        By logo = By.id("jenkins-head-icon");
        By newJobLink = By.xpath("//a[@href='/view/all/newJob']");

        getWait10().until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(logo))).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(newJobLink));

        return this;
    }

}
