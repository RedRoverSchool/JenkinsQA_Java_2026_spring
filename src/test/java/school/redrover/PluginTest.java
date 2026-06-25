package school.redrover;

import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

public class PluginTest extends BaseTest {

    private final static String PLUGIN_NAME = "ChuckNorris";

    @Test
    public void installPluginTest() {
        String installPlugin = new HomePage(getDriver())
                .clickManageButton()
                .clickPluginsButton()
                .clickAvailablePluginsButton()
                .setSearchPluginName(PLUGIN_NAME)
                .selectSearchResult(PLUGIN_NAME)
                .clickInstallButton()
                .getSuccessInstall(PLUGIN_NAME);

        assert installPlugin.contains(PLUGIN_NAME) && installPlugin.contains("Success");
    }
}
