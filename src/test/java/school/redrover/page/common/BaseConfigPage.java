package school.redrover.page.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseConfigPage<T extends BaseConfigPage<T>> extends BasePage {

    @FindBy(name = "Apply")
    private WebElement applyButton;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[contains(@name, 'description')]")
    private WebElement descriptionTextArea;

    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    protected abstract T self();

    public <JobPage extends BasePage> JobPage clickSave(JobPage jobpage) {
        saveButton.click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'task-link')]//span[text()='Status']")));

        return jobpage;
    }

    public T clickApply() {
        applyButton.click();
        return self();
    }

    public T enterDescription(String description) {
        descriptionTextArea.sendKeys(description);
        return self();
    }
}
