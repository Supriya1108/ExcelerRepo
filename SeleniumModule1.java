package seleniumAssignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumModule1 {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("...Doing Assignment 1...");
		amazonTitle();
		facebookTitle();
		navigationDemo();
		launchBrowser();
		System.out.println("...Completed Assignment 1...");
		System.out.println("...Doing Assignment 2...");
		options_Checkbox();
		dropDown();
		System.out.println("...Completed Assignment 2...");

	}

	//First Selenium Test: Verify amazon HomaPage
	public static void amazonTitle() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println("Amazon page title ==>> " + driver.getTitle());
		List<WebElement> categories = driver.findElements(By.tagName("a"));
		System.out.println("Available categories on Amazon page are");
		for (WebElement A1 : categories) {
			System.out.println(A1.getText());
		}
		driver.close();
	}

	//Verify the FaceBook HomePage
	public static void facebookTitle() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		System.out.println("Facebook page title ==>> " + driver.getTitle());
		driver.findElement(By.id("email")).sendKeys("test1234");
		driver.findElement(By.name("pass")).sendKeys("test@1234");
		driver.findElement(By.name("login")).click();
		List<WebElement> urls = driver.findElements(By.tagName("a"));
		System.out.println("Number of URLS: " +urls.size());
		for ( WebElement test : urls) {
			System.out.println("URL -> " + test.getAttribute("href"));
			System.out.println("ToolTip -> " + test.getAttribute("title"));
			System.out.println("Text -> " + test.getText());
		}
		driver.close();
	}

	//Using Navigator commands to move home page to other page and comeback to homepage
	public static void navigationDemo() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='_2GaeWJ']/img[@alt='Grocery']")).click();
		driver.navigate().back();
		System.out.println("Navigated back to homepage....");
		driver.close();

	}

	//Invoke : Firefox, Safari, IE
	public static void launchBrowser() {

		WebDriver firefox = new FirefoxDriver();
		WebDriver safari = new SafariDriver();
		WebDriver IE = new InternetExplorerDriver();
	}

	//Find Option 1 and select and Find Checkbox2 and Checkbox3 and select
	public static void options_Checkbox() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/radio.html");
		driver.manage().window().maximize();
		WebElement radioButton = driver.findElement(By.xpath("//*[@id=\"vfb-7-1\"]"));
		radioButton.click();

		if (radioButton.isSelected()) {
			System.out.println("Option 1 is selcted");
		} else {
			System.out.println("Option 1 is not selcted");
		}

		WebElement CB2 = driver.findElement(By.xpath("//input[@value='checkbox2']"));
		WebElement CB3 = driver.findElement(By.xpath("//input[@value='checkbox3']"));
		CB2.click();
		CB3.click();

		if (CB2.isSelected()) {
			if (CB3.isSelected()) {
				System.out.println("Checkbox 2 & Checkbox 3 are selcted");
			}
		} else {
			System.out.println("Checkbox 2 & Checkbox 3 are not selcted");
		}
		driver.close();

	}
	
	//Find Country Dropdown and Select “KUWAIT”
	public static void dropDown() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		driver.manage().window().maximize();
		
		WebElement dropdown = driver.findElement(By.name("country"));
		Select obj = new Select(dropdown);
		
		//Scroll till dropdown option
		Actions act  = new Actions(driver);
		act.scrollToElement(dropdown).perform();
		
		//Selcting country as "KUWAIT"
		obj.selectByVisibleText("KUWAIT");
		driver.close();
	}

}
