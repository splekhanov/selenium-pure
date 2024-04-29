package selenium.pure.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BaseTestClass {

    private static final Logger logger
            = LoggerFactory.getLogger(BaseTestClass.class);

    protected Properties properties;
    private String baseUrl;
    private String env;
    private Boolean selenoid;
    private String selenoidUrl;

    @BeforeEach
    public void setUp() {
        setProperties();
        startDriver();
        openStartPage(baseUrl);
    }

    private void setProperties() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(Objects.requireNonNull(BaseTestClass.class.getClassLoader()
                    .getResourceAsStream("config.properties")), StandardCharsets.UTF_8));
        } catch (IOException e) {
            logger.error("Can't load 'config.properties'");
        }
        baseUrl = properties.getProperty("baseUrl");
        selenoid = "true".equals(properties.getProperty("selenoid"));
        selenoidUrl = properties.getProperty("selenoidUrl");
        env = System.getProperty("env");
    }

    private void startDriver() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();

        if(env == null) {
            driver = new ChromeDriver(getChromeOptions());
            DriverFactory.set(driver);
            logger.info("Starting local WebDriver...");
        } else {
            driver = new RemoteWebDriver(getGridHubUrl(), getChromeOptions());
            DriverFactory.set(driver);
            logger.info("Starting remote WebDriver...");
        }
    }

    private void openStartPage(String url) {
        DriverFactory.get().get(url);
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Disabling 'Save password' popup
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Disabling 'Chrome is being controlled by automated test software' banner
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // Standard arguments
        options.addArguments("--start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-device-discovery-notifications");
        options.addArguments("no-sandbox");
        return options;
    }

    @AfterEach
    public void tearDown(){
        WebDriver driver = DriverFactory.get();
        try {
            driver.quit();
        } finally {
            DriverFactory.remove();
        }
    }

    private URL getGridHubUrl() {
        URL hostURL = null;
        try {
            hostURL = new URL(selenoidUrl);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        }
        return hostURL;
    }

    @Step("{message}")
    public void logStep(String message) {
        //
    }
}
