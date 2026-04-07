package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class SearchTest extends BaseTest {


    @Test
    public void testEmptySearch() {
        String text = "Текст для строки поиска";

        getDriver().findElement(By.xpath("//button[@id='root-action-SearchAction']")).click();
        getDriver().findElement(By.xpath("//input[@class='jenkins-input jenkins-search__input']")).sendKeys(text);

        String expectedText = "No results for " + text;
        String actualText = getDriver().findElement(By.xpath("//*[@class='jenkins-command-palette__info']")).getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test
    public void testSuggestedItems() {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        WebElement text = getDriver().findElement(By.xpath("//input[@id='name']"));
        text.click();
        text.sendKeys("Для поиска pipeline");

        getDriver().findElement(By.xpath("//*[@id='items']/div[1]/label")).click();
        getDriver().findElement(By.cssSelector("#ok-button")).click();

        getDriver().findElement(By.xpath("//*[@formnovalidate='formNoValidate']")).click();


        getDriver().findElement(By.xpath("//*[@id='root-action-SearchAction']")).click();
        getDriver().findElement(By.xpath("//input[@id='command-bar']")).sendKeys("Поиск");

        List<WebElement> suggestions = getDriver().findElements(By.xpath("//*[@id='search-results']"));

        for (WebElement suggestion : suggestions) {
            String suggestionText = suggestion.getText().toLowerCase();

            Assert.assertTrue(suggestionText.startsWith("поиск") || suggestionText.contains("поиск"), "Текст предложения не содержит 'поиск");
        }
    }
}
