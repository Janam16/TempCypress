package rahulshetty.tests;

import java.io.IOException;
import java.util.HashMap;
//import java.util.List;

import org.testng.Assert;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshetty.pageobject.LandingPage;
import rahulshetty.pageobject.Form;
import rahulshetty.TestComponents.BaseTest;
//import rahulshetty.TestComponents.Retry;

public class TestSeleniumData extends BaseTest {
    
   
    @Test(dataProvider="envDataProvider", groups = { "DataJson" })
    public void DataVerification(HashMap<String,String> data) throws InterruptedException, IOException {

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
        getScreenshot("DataVerification_"+data.get("name"), driver);
        System.out.println("-------------------------------");
        System.out.println("User Details: "+ data.get("name"));
        System.out.println(data.get("gender")+" "+data.get("employmentStatus")+" "+data.get("dateOfBirth"));
        System.out.println("-------------------------------");
    }


}
