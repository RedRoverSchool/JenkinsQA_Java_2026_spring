package school.redrover.page.project.config;

import org.openqa.selenium.WebDriver;
import school.redrover.page.common.BaseConfigPage;

public class MultibranchConfigPage extends BaseConfigPage<MultibranchConfigPage> {
    public MultibranchConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MultibranchConfigPage self() {
        return this;
    }

}
