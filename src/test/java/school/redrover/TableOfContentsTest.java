package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TableOfContentsTest extends BaseTest {

    private final List<String> TableOfContents = List.of("Case insensitive search", "OpenSearch support","Feedback appreciated");

    @Test
    public void testTutorial() {
        getDriver().findElement(By.id("root-action-SearchAction")).click();
        getDriver().findElement(By.xpath("//*[@id='search-results']/a")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toc']//li/a")));

        List<WebElement> links = getDriver().findElements(By.xpath("//*[@id='toc']//li/a"));

        List<String> actualTableOfContents = new ArrayList<>();
        for (WebElement link : links) {
            actualTableOfContents.add(link.getText());
        }
        Assert.assertEquals(actualTableOfContents, TableOfContents);
    }
}

