package rahulshetty.TestComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshetty.pageobject.LandingPage;
import rahulshetty.utils.JsonUtils;

public class BaseTest {

    public WebDriver driver;
    protected String env;
    protected String baseURL;
    protected String testDataFile;

    /*** SETUP ENVIRONMENT BEFORE CLASS ***/
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws IOException {
        this.env = System.getProperty("env", "uat"); // default to uat
        this.testDataFile = System.getProperty("user.dir") + "/src/test/java/rahulshetty/data/Formdata_" + env + ".json";
        this.baseURL = JsonUtils.getBaseURL(testDataFile);
        System.out.println("Running tests on Environment: " + env + " | URL: " + this.baseURL);
    }

    /*** INITIALIZE DRIVER BEFORE EACH TEST METHOD ***/
    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/rahulshetty/resources/GlobalData.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    /*** CLOSE DRIVER AFTER EACH TEST ***/
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    /*** LAUNCH LANDING PAGE ***/
    public LandingPage LaunchingApplication() throws IOException {
        return new LandingPage(driver);
    }

    /*** DATA PROVIDER ***/
    @DataProvider(name = "envDataProvider")
    public Object[][] envDataProvider(Method method) throws IOException {
        String testName = method.getName();
        List<HashMap<String, String>> data = JsonUtils.getJsonDataToMapByEnv(testDataFile, env, testName);
        Object[][] arr = new Object[data.size()][1];
        for(int i = 0; i < data.size(); i++) {
            arr[i][0] = data.get(i);
        }
        return arr;
    }

    /*** JSON HELPER ***/
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    /*** SCREENSHOT HELPER ***/
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }
}