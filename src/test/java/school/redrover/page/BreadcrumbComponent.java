package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.common.BaseConfigPage;
import school.redrover.page.common.BaseModel;
import school.redrover.page.common.BaseProjectPage;

import java.util.List;

public class BreadcrumbComponent extends BaseModel {
    public BreadcrumbComponent(WebDriver driver) {
        super(driver);
    }

    public <ProjectPage extends BaseProjectPage> ProjectPage clickParentItem(String parentName, ProjectPage projectPage) {
        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']//a[text()='%s']".formatted(parentName))).click();
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(@href, 'move')]")));

        return projectPage;
    }

    public BreadcrumbComponent openDropdownForProject(String projectName) {
        getDriver().findElement(By.xpath("//a[text()='%s']/following-sibling::div[@class='dropdown-indicator']".formatted(projectName))).click();

        return this;
    }

    public <ProjectConfigPage extends BaseConfigPage> ProjectConfigPage clickConfigureFromDropdown(ProjectConfigPage configPage) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='jenkins-dropdown__item ' and contains(@href, 'configure')]"))).click();
        getWait5().until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1"), "Configuration"));

        return configPage;
    }

    public List<String> getBreadcrumbItems() {
        List<String> actualBreadcrumbs = getWait5()
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#breadcrumbs .jenkins-breadcrumbs__list-item")))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toList();
        return actualBreadcrumbs;
    }

    public List<String> getDropdownItems() {
        List<String> actualMenuItems = getWait10().until(driver -> {
            try {
                List<WebElement> elements = driver.findElements(By.cssSelector(".jenkins-dropdown__item"));
                List<String> texts = elements.stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .filter(text -> !text.isEmpty())
                        .toList();
                return !texts.isEmpty() ? texts : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
        return actualMenuItems;
    }
}
