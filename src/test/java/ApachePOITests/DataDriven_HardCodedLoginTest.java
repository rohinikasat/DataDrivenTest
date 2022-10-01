package ApachePOITests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven_HardCodedLoginTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/rishimalani/BrowserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@DataProvider(name = "LoginData")
	public String[][] getData() {
		String loginData[][]= {
				
				{"admin@yourstore.com",	"admin",	"Valid"},
				{"admin@yourstore.com",	"admn",	"Invalid"},
				{"adm@yourstore.com",	"admin",	"Invalid"},
				{"adm@yourstore.com",	"adm",	"Invalid"}
		};
		
		return loginData;
	}

	@Test(dataProvider = "LoginData")
	public void loginTest(String user, String pwd, String exp) {
		driver.get("https://admin-demo.nopcommerce.com/login");
		WebElement txtEmail = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
		txtEmail.clear();
		txtEmail.sendKeys(user);
		
		WebElement txtPwd = driver.findElement(By.xpath("//*[@id=\"Password\"]"));
		txtPwd.clear();
		txtPwd.sendKeys(pwd);
		
		WebElement loginButton = driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button"));
		loginButton.click();
		
		String exp_title = "Dashboard / nopCommerce administration";
		String actual_title = driver.getTitle();
		
		if(exp.equals("Valid")) {
			if(exp_title.equals(actual_title)) {
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(true);
				
			} else {
				Assert.assertTrue(false);
			}
			//negative test scenario
		}else if(exp.equals("Invalid")) {
			if(exp_title.equals(actual_title)) {
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
