package rahulshetty.pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import rahulshetty.AbstractComponents.AbstractComponent;

//Pom contains only element and actions
//POM should not contain any assertion

public class Form extends AbstractComponent{
    WebDriver driver;

    public Form(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); //@FindBy annotations will be initialized with this driver and this class. 
        // It will create a proxy for the WebElement and when we call any method on that WebElement, it will find the element 
        // using the locator defined in the @FindBy annotation and then perform the action on it. This is how PageFactory works.
        // It helps to initialize the WebElements defined in the class with the driver and the class itself.
    }

    @FindBy(css = ".form-control.ng-untouched.ng-pristine.ng-invalid")
    WebElement nameField;

    @FindBy(css = "input[name=email]")
    WebElement emailField;

    @FindBy(css = "input#exampleInputPassword1")
    WebElement passwordField;

    @FindBy(css = "input#exampleCheck1")
    WebElement checkbox;

    @FindBy(css = "select#exampleFormControlSelect1")
    WebElement genderDropdown;

    @FindBy(css = "input#inlineRadio1")
    WebElement maleRadioButton;

    @FindBy(css = "input#inlineRadio3")
    WebElement disabledRadioButton;

    @FindBy(css = "input[name='bday']")
    WebElement dateOfBirthField;

    @FindBy(css = ".btn.btn-success")
    WebElement submitButton;

    @FindBy(css = ".alert.alert-success.alert-dismissible")
    WebElement successMessage;


    public void UserDetails(String name, String email, String password){
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void UserGender(String gender){
        Select genderddl = new Select(genderDropdown);
        genderddl.selectByVisibleText(gender);
        //maleRadioButton.click();
    }

    public void UserMaleRadioButton(){
        maleRadioButton.click();
    }

    public void CheckCheckbox(){
        checkbox.click();
    }

    public void UserDOB(String dob){
        dateOfBirthField.sendKeys(dob);
    }

    public void SubmitForm(){
        submitButton.click();
    }


    public boolean CheckDisabledRadioButton(){
        return disabledRadioButton.isEnabled();
    }

    public String GetSuccessMessage(){
        return successMessage.getText();
    }   

    

}
