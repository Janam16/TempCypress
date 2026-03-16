package rahulshetty.pageobject;

import org.openqa.selenium.WebDriver;
import rahulshetty.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    
    public Form openURL(String baseURL){
        //driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.get(baseURL);
        Form form = new Form(driver);
        return form;
    }
    public Form openURL(){
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        Form form = new Form(driver);
        return form;
    }
}
