package base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

public class Base 
{
	WebDriver driver;
	public WebDriver initializeDriver() throws IOException
	{
	Properties prop = new Properties();
	FileInputStream prerequisite = new FileInputStream("C:\\Users\\Poovanna HG\\eclipse-workspace\\Croma_project\\src\\main\\java\\data.properties");
	prop.load(prerequisite);
	String browser = prop.getProperty("browser");
	if (browser.equals("chrome"))
	{
//		ChromeOptions option = new ChromeOptions();
//		option.addArguments("--remote-allow-origin=*");
		driver = new ChromeDriver();
	}
	return driver;
	}

	public String getUrl() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream prerequisite = new FileInputStream("C:\\Users\\Poovanna HG\\eclipse-workspace\\Croma_project\\src\\main\\java\\data.properties");
		prop.load(prerequisite);
		String url = prop.getProperty("url");
		return url;
	}
	
	public void takeScreenShots(WebDriver driver, String path)
	{
		File scrSht =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String destinationFile = System.getProperty("user.dir")+"\\screenshots\\"+path+".png";
			FileUtils.copyFile(scrSht, new File(destinationFile));
		}catch (IOException e1){
	
		e1.printStackTrace();
	}
	
}
}
