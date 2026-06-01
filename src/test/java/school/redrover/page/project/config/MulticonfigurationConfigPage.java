package school.redrover.page.project.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseConfigPage;

public class MulticonfigurationConfigPage extends BaseConfigPage<MulticonfigurationConfigPage> {
    public MulticonfigurationConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MulticonfigurationConfigPage self() {
        return this;
    }

    public MulticonfigurationConfigPage disableProjectToggle() {
        getDriver().findElement(By.xpath("//label[@class='jenkins-toggle-switch__label ']")).click();
        return this;
    }
}
