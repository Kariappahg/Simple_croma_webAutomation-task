package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CromaHomePage {
	WebDriver driver;
	By searchbox = By.xpath("//*[@id='searchV2']");
	By brandSelection = By.xpath("//*[@id=\"panel1bh-header\"]/div[1]/p");
	By brand1 = By.xpath("//*[@id='panel1bh-content']/div/p/div/ul/li[1]/div/label");
	By brand2 = By.xpath("//*[@id='panel1bh-content']/div/p/div/ul/li[2]/div/label");
	By brand3 = By.xpath("//*[@id=\"panel1bh-content\"]/div/p/div/ul/li[3]/div/label");
	By sort = By.className("selected-item");
	By byDiscount = By.xpath("//li[contains(text(), 'Discount (Descending)')]");
	By avgPriceOfProduct = (By.xpath("//*[@data-testid=\"new-price\"]"));
	
	public CromaHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement searchbox()
	{
		return driver.findElement(searchbox);
	}
	
	public WebElement brandSelection()
	{
		return driver.findElement(brandSelection);
	}
	
	public WebElement brand1()
	{
		return driver.findElement(brand1);
	}
	
	public WebElement brand2()
	{
		return driver.findElement(brand2);
	}
	
	public WebElement brand3()
	{
		return driver.findElement(brand3);
	}
	
	public WebElement sort()
	{
		return driver.findElement(sort);
	}
	
	public WebElement byDiscount()
	{
		return driver.findElement(byDiscount);
	}
	
	public double avgPriceOfProduct() {
	    List<WebElement> priceElements = driver.findElements(avgPriceOfProduct);
	    double sum = 0;
	    int count = 0;

	    for (int i = 0; i < Math.min(10, priceElements.size()); i++) {
	        String priceText = priceElements.get(i).getText();
	        try {
	            String actualPrice = priceText.replaceAll("[^\\d.]", "");
	            double price = Double.parseDouble(actualPrice);
	            sum += price;
	            count++;
	        } catch (NumberFormatException e) {
	            System.out.println("Could not parse price: " + priceText);
	        }
	    }

	    if (count > 0) {
	        return sum / count;
	    } else {
	        System.out.println("No valid prices found.");
	        return 0;
	    }
	}
	
}
