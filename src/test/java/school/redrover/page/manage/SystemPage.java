package school.redrover.page.manage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.common.BasePage;

import java.util.List;

public class SystemPage extends BasePage {

    @FindBy(xpath = "//div[@class='jenkins-section__title']")
    private List<WebElement> sectionList;

    public SystemPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getSectionList() {
        return sectionList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .toList();
    }
}
