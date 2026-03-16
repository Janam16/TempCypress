package rahulshetty.tests;

import java.io.IOException;
import java.util.HashMap;
//import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshetty.pageobject.LandingPage;
import rahulshetty.pageobject.Form;
import rahulshetty.TestComponents.BaseTest;
//import rahulshetty.TestComponents.Retry;

public class TestSeleniumcopy extends BaseTest {
    
    @Test
    public void FillForm1() throws InterruptedException, IOException {

        LandingPage landingPage = LaunchingApplication(); // We can directly call the method because it is static. It
                                                          // will return the driver and we can use that driver to create
                                                          // the object of LandingPage.
        Form form = landingPage.openURL(); // To avoid creating object. Create it in the previous one and get the
                                           // returned object.

        form.UserDetails("James", "james@gmail.com", "Test@123");
        form.CheckCheckbox();
        form.UserGender("Female");
        form.UserMaleRadioButton();
        boolean isEnabled = form.CheckDisabledRadioButton();
        if (!isEnabled) {
            Assert.assertEquals(isEnabled, false, "It is disabled.");
            System.out.println("It is disabled.");

        } else {
            Assert.assertEquals(isEnabled, true, "It is enabled.");
            System.out.println("It is enabled.");
        }
        form.UserDOB("09/03/2026");
        form.SubmitForm();
        Assert.assertTrue(form.GetSuccessMessage().contains("Success! The Form has been submitted successfully!."));
        
        System.out.println("Script Execution Completed successfully.");

    }

    @BeforeClass
    public void setup() throws IOException {
        setEnvironment(); // sets baseURL
    }

    
    //@Test(dataProvider = "getData", groups = { "Smoke"})
    @Test(dataProvider="envDataProvider", groups = { "DataJson" })
    public void NegativeValidation(HashMap<String,String> data) throws InterruptedException, IOException {

        LandingPage landingPage = LaunchingApplication(); // We can directly call the method because it is static. It
                                                          // will return the driver and we can use that driver to create
                                                          // the object of LandingPage.
        Form form = landingPage.openURL(baseURL); // To avoid creating object. Create it in the previous one and get the
                                           // returned object.

        form.UserDetails(data.get("name"), data.get("email"), data.get("password"));
        System.out.println("Created Name: " + data.get("name"));
        form.CheckCheckbox();
        boolean isEnabled = form.CheckDisabledRadioButton();
        Assert.assertFalse(isEnabled);
        getScreenshot("NegativeValidation_"+data.get("name"), driver);
        System.out.println("-------------------------------");
        System.out.println("User Details: "+ data.get("name"));
        System.out.println("-------------------------------");
    }

    @Test(dataProvider="envDataProvider", groups = { "DataJson" })
    public void PositiveValidation(HashMap<String,String> data) throws InterruptedException, IOException {

        LandingPage landingPage = LaunchingApplication(); // We can directly call the method because it is static. It
                                                          // will return the driver and we can use that driver to create
                                                          // the object of LandingPage.
        Form form = landingPage.openURL(baseURL); // To avoid creating object. Create it in the previous one and get the
                                           // returned object.

        form.UserDetails(data.get("name"), data.get("email"), data.get("password"));
        System.out.println("Created Name: " + data.get("name"));
        form.CheckCheckbox();
        boolean isEnabled = form.CheckDisabledRadioButton();
        Assert.assertFalse(isEnabled);
        getScreenshot("NegativeValidation_"+data.get("name"), driver);
        System.out.println("-------------------------------");
        System.out.println("User Details: "+ data.get("name"));
        System.out.println("-------------------------------");
    }

    

    /*
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshetty//data//Formdata.json");
        return new Object[][] { { data.get(0) }, { data.get(1) } };
        //HashMap<String,String> map = new HashMap<String,String>();
        //map.put("name", "Anikha");
        //map.put("email", "anikha@gmail.com");
        //map.put("password", "Test@123");

        //HashMap<String,String> map1 = new HashMap<String,String>();
        //map1.put("name", "James");
        //map1.put("email", "james@gmail.com");
        //map1.put("password", "Test@123");

        //return new Object[][] { { map }, { map1 } };
        //return new Object[][] { { "Anikha", "anikha@gmail.com", "Test@123" },{ "James", "james@gmail.com", "Test@123" } };
    }
    */

    //@Test(retryAnalyzer = Retry.class)
    @Test(enabled = false)
    public void ErrorRetryForm() throws InterruptedException, IOException {
    
        LandingPage landingPage = LaunchingApplication(); 
        Form form = landingPage.openURL();
        form.UserDetails("Shilpa", "shipa@gmail.com", "Test@123");
        form.CheckCheckbox();
        form.UserMaleRadioButton();
        boolean isEnabled = form.CheckDisabledRadioButton();
        Assert.assertFalse(isEnabled);
        Assert.assertTrue(isEnabled);
    }

}
