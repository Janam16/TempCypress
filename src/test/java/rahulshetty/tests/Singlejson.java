package rahulshetty.tests;
import rahulshetty.pageobject.LandingPage;
import rahulshetty.pageobject.Form;
import rahulshetty.TestComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshetty.utils.JsonUtilsNew;


public class Singlejson extends BaseTest {

@BeforeClass
    public void setup() throws IOException {
        setEnvironment(); // sets baseURL
    }
    
@Test(groups = { "SingleDataJson" })
public void NegativeValidationTest()  throws Exception {

    // Pass test name only
    List<HashMap<String,String>> dataList = JsonUtilsNew.getTestData("NegativeValidation");

    for (HashMap<String,String> data : dataList) {

         LandingPage landingPage = LaunchingApplication();
         Form form = landingPage.openURL(baseURL);

        form.UserDetails(data.get("name"), data.get("email"), data.get("password"));

        System.out.println("Created Name: " + data.get("name"));

        form.CheckCheckbox();
        boolean isEnabled = form.CheckDisabledRadioButton();
        Assert.assertFalse(isEnabled);

        getScreenshot("NegativeValidation_" + data.get("name"), driver);
    }
}


}