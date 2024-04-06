package seleniumAssignment;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.common.io.Files;

public class SeleniumModule2 {

	public static void main(String[] args) {
		companyList();
		login();
		alerts();
		frames();
		window();
		actions();

	}

	public static void companyList() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/web-table-element.php");
		
		List<WebElement> company = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td/a"));
		System.out.println("Total number of Companies in the table: " + company.size());
		System.out.println("List of all Companies: ");
		for (WebElement Value : company) {
			System.out.println(Value.getText());
		}
		driver.close();
	}
	
	public static void login() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/login.html");
		driver.findElement(By.id("email")).sendKeys("test");
		driver.findElement(By.id("passwd")).sendKeys("test");
		driver.findElement(By.id("SubmitLogin")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='error-copy']")).getText());
		driver.close();
	}
	
	public static void alerts() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/test/delete_customer.php");
		driver.findElement(By.name("cusid")).sendKeys("123");
		driver.findElement(By.name("submit")).click();
		Alert al1 = driver.switchTo().alert();
		System.out.println(	al1.getText());
		al1.accept();
		Alert al2 = driver.switchTo().alert();
		System.out.println(al2.getText());
		al2.accept();
		driver.close();
	}
	
	public static void frames() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.guru99.com/handling-iframes-selenium.html");	
		driver.findElement(By.xpath("//a[@data-lasso-id='545619']")).click();
		//System.out.println(driver.findElements(By.tagName("iframe")));
		
		driver.close();
		
	}
	
	public static void window(){
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/popup.php");
		driver.manage().window().maximize();
		String pwhandle = driver.getWindowHandle();
		System.out.println("Parent window handle is: "+pwhandle);
		System.out.println("Parent title is: "+driver.getTitle());
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		it.next();
		String cwhandle = it.next();
		System.out.println("Child window handle is: "+cwhandle);
		driver.switchTo().window(cwhandle);
		System.out.println("Child title is: "+driver.getTitle());
		driver.findElement(By.name("emailid")).sendKeys("testWindow@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		String actual = driver.findElement(By.xpath("//tbody/tr/td/h2[text()='Access details to demo site.']")).getText();
		String expected = "Access details to demo site.";
		if (actual.equals(expected)) {
			System.out.println("Successfully completed");
		} else {
			System.out.println("Failed");
		}
		driver.close();
	}
	
	public static void actions() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		driver.findElement(By.linkText("Draggable")).click();
		WebElement f1 = driver.findElement(By.xpath("//div[@id='content']/iframe"));
		driver.switchTo().frame(f1);
		WebElement src = driver.findElement(By.id("draggable"));
		act.dragAndDropBy(src, 200, 0).perform();
		CaptureScreenShot("DragAndDrop TC", driver);
		driver.close();
	}
	
	//Taking Screenshot
		public static void CaptureScreenShot(String fileName, WebDriver driver) {
			String path = "./SS_Test/" + fileName + ".png";

			TakesScreenshot ts = (TakesScreenshot) driver;
			File temp = ts.getScreenshotAs(OutputType.FILE);
			try {
				Files.copy(temp, new File(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
	

}
