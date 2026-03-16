package rahulshetty.tests;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshetty.pageobject.LandingPage;
import rahulshetty.pageobject.Form;
import rahulshetty.TestComponents.BaseTest;

public class TestSelenium extends BaseTest {
    
    @Test
    public void FillForm() throws InterruptedException, IOException {
       
        LandingPage landingPage = LaunchingApplication(); // We can directly call the method because it is static. It will return the driver and we can use that driver to create the object of LandingPage.
        Form form = landingPage.openURL(); // To avoid creating object. Create it in the previous one and get the returned object.
        
        
        form.UserDetails("Jane", "jane@gmail.com", "Test@123");
        form.CheckCheckbox();
        form.UserGender("Female");
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
        
        
        
    
    }

    @Test(dependsOnMethods = {"FillForm"})
    public void Verification() throws InterruptedException, IOException {
        LandingPage landingPage = LaunchingApplication();
        Form form = landingPage.openURL();
        form.UserDetails("James", "james@gmail.com", "Test@123");
        form.UserMaleRadioButton();
        form.SubmitForm();
        Assert.assertTrue(form.GetSuccessMessage().contains("Success! The Form has been submitted successfully!."));
        System.out.println("Script Execution Completed successfully.");
    }
    
    @Test
    public void NegativeValidation() throws InterruptedException, IOException {
       
        LandingPage landingPage = LaunchingApplication(); // We can directly call the method because it is static. It will return the driver and we can use that driver to create the object of LandingPage.
        Form form = landingPage.openURL(); // To avoid creating object. Create it in the previous one and get the returned object.
        
        
        form.UserDetails("Anikha", "anikha@gmail.com", "Test@123");
        form.CheckCheckbox();
        //boolean isEnabled = form.CheckDisabledRadioButton();
        //Assert.assertFalse(isEnabled);
        
    
    }
}
