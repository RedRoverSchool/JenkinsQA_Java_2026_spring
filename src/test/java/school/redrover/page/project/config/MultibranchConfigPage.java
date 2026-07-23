package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;

public class MultibranchConfigPage extends BaseConfigPage<MultibranchConfigPage> {
    public MultibranchConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MultibranchConfigPage self() {
        return this;
    }

    @FindBy(xpath = "//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Child item with the given name')]")
    public WebElement healthMetric1;
    @FindBy(xpath = "//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Child item with worst health')]")
    public WebElement healthMetric2;
    @FindBy(xpath = "//button[contains(@class, 'jenkins-dropdown__item') and contains(., 'Health of the primary branch')]")
    public WebElement healthMetric3;

    public MultibranchConfigPage chooseHealthMetricsOnTheSideMenu() {
        getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-section-id='health-metrics']"))).click();
        WebElement target = getDriver().findElement(By.xpath("//h3[text()='Health metrics'] | //*[@id='health-metrics']"));
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({block: 'start', behavior: 'instant'});", target);

        return this;
    }

    public MultibranchConfigPage clickHealthMetricsButton() {
    getWait10().until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(".jenkins-button.advanced-button.advancedButton"))).click();

    return this;
}

    public MultibranchConfigPage clickAddMetricsButton(WebElement healthMetric) {

        WebElement addMetric =getWait10().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add metric')]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", addMetric);
        addMetric.click();
        healthMetric.click();

        return this;
}

    public boolean statusOfAddMetricButton(){

        return getDriver().findElement(By.xpath("//button[contains(text(), 'Add metric')]")).isEnabled();
    }
}
