package rahulshetty.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
public class GreenKartcopy{

    @Test
    public void productPage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        String[] productItems = {"Carrot", "Brocolli", "Cucumber","Radish","Mushroom"};
        List<String> productList = Arrays.asList(productItems);
        HashSet<String> addedItems = new HashSet<>();

        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        for(int i=0; i<products.size(); i++){
            String productName = products.get(i).getText().split("-")[0].trim();
            if(productList.contains(productName)){
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();  
                System.out.println("Added to cart : " + productName);
                addedItems.add(productName);
            }
        }
        
        System.out.println("\nItems not found in the product list:");
        for(String item : productItems){
            if(!addedItems.contains(item)){
                System.out.println("Not present: " + item);
            }
        }
        driver.quit();
    }

}