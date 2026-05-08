package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BasePage;

import java.util.List;

public class NodesPage extends BasePage {

    public NodesPage(WebDriver driver) {
        super(driver);
    }

    public NodesPage createNewNode(String name) {
        getDriver().findElement(By.xpath("//a[@href='new']")).click();
        getDriver().findElement(By.id("name")).sendKeys(name);
        getDriver().findElement(By.className("jenkins-radio__label")).click();
        getDriver().findElement(By.xpath("//button[@value='Create']")).click();

        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@value='Save']"))).click();
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-panel']//h1")));

        return new NodesPage(getDriver());
    }

    public List<String> getNodesList() {
        return getDriver().findElements(By
                        .xpath("//a[@class = 'jenkins-table__link model-link inside']"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public NodeManagementPage goToNodeManagementPage(String name){
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='../computer/%s/']"
                .formatted(name.replace(" ", "%20"))))).click();
        return new NodeManagementPage(getDriver());
    }
}

