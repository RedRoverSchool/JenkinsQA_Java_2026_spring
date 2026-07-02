package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import school.redrover.page.common.BaseConfigPage;

import java.time.Duration;

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
                By.xpath("//button[@data-section-id='health-metrics'] | //*[contains(text(), 'Health metrics')]"))).click();

        return this;
    }

    public MultibranchConfigPage clickHealthMetricsButton() {
    getWait10().until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Health metrics')]"))).click();

    return this;
}

    public MultibranchConfigPage clickAddMetricsButton(WebElement healthMetric) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
    wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='main-panel']/form/div[1]/section[6]/div[3]/div/div/div/span/button"))).click();
    healthMetric.click();

    return this;
}

    public boolean statusOfAddMetricButton(){

        return getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/section[6]/div[3]/div/div/div/span/button")).isEnabled();
    }
}
