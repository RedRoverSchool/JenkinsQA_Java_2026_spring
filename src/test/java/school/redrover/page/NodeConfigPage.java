package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.ArrayList;
import java.util.List;

public class NodeConfigPage extends BasePage {

    public NodeConfigPage(WebDriver driver) {
        super(driver);
    }

    public NodeConfigPage changeDescription(String desc) {
        getDriver().findElement(By.xpath("//textarea[@name='nodeDescription']")).sendKeys(desc);
        return this;
    }

    public NodeConfigPage changeDir(String dir) {
        getDriver().findElement(By.xpath("//input[@name='_.remoteFS']")).sendKeys(dir);
        return this;
    }

    public NodeConfigPage changeLabel(String label) {
        getDriver().findElement(By.xpath("//input[@name='_.labelString']")).sendKeys(label);
        return this;
    }

    public NodeConfigPage changeUsage(String usage) {
        getDriver().findElement(By.xpath("//select[@name='mode']")).click();
        getDriver().findElement(By.xpath("//option[@value='%s']".formatted(usage))).click();
        return this;
    }

    public NodeConfigPage saveChanges() {
        getDriver().findElement(By.xpath("//button[@value='Save']")).click();
        return this;
    }

    public List<String> getConfigDescriptionList(String label) {

        List<String> actualAttributes = new ArrayList<>();
        actualAttributes.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.id("description-content")))
                .getText());

        actualAttributes.add(getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='/label/%s']".formatted(label)))).getText());
        return actualAttributes;
    }
}
