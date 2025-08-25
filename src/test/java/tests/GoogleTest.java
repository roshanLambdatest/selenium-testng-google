package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;   // for local run
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.*;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;

import io.github.bonigarcia.wdm.WebDriverManager; 

public class GoogleTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
   
        String username  = System.getenv("LT_USERNAME");   
        String accessKey = System.getenv("LT_ACCESS_KEY");  

        String hubURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("platformName", "Windows 11");
        ltOptions.put("project", "Google TestNG Project");
        ltOptions.put("name", "GoogleTest");
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        driver.manage().window().maximize();

        /*
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        */
    }

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assert title.contains("Google") : "Title does not contain Google!";
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
